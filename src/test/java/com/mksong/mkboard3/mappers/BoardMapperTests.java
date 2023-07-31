package com.mksong.mkboard3.mappers;

import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.transaction.annotation.Transactional;

import com.mksong.mkboard3.dto.BoardDTO;
import com.mksong.mkboard3.dto.PageRequestDTO;

import lombok.extern.log4j.Log4j2;

@SpringBootTest
@Log4j2
public class BoardMapperTests {

  @Autowired
  FileMapper fileMapper;

  @Autowired
  BoardMapper boardMapper;

  @Test
    public void testGetList(){

        PageRequestDTO pageRequestDTO = PageRequestDTO.builder().build();

        List<BoardDTO> result = boardMapper.getList(pageRequestDTO);

        log.info("=====================");

        log.info(result);
        
    }

    @Transactional
    @Test
    @Commit
    public void testRegister(){

    BoardDTO dto = new BoardDTO();
    dto.setTitle("테스트상품");
    dto.setContent("테스트상품1");
    dto.setWriter("테스트 작성자");
    dto.setFileNames(List.of(UUID.randomUUID()+"_f1.jpg",UUID.randomUUID()+"_f2.jpg"));

    List<String> fileNames = dto.getFileNames();

    int count = boardMapper.regist(dto);

    log.info("insert product count: " + count);

    Integer bno = dto.getBno();

    log.info("-----------------------------" + bno);

    AtomicInteger index = new AtomicInteger();

    List<Map<String,String>> list = fileNames.stream().map(str -> {
      String uuid = str.substring(0, 36);
      String fileName = str.substring(37);

      log.info(uuid);

      return Map.of("uuid", uuid, "fileName", fileName,"bno", ""+bno, "ord", "" + index.getAndIncrement());

    }).collect(Collectors.toList());

    log.info(list);

    int countImages = boardMapper.registerImage(list);

    log.info("=====================" + countImages);
    

  }


  
}
