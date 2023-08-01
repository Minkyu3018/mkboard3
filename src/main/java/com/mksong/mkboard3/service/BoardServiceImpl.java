package com.mksong.mkboard3.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

import com.mksong.mkboard3.dto.BoardDTO;
import com.mksong.mkboard3.dto.BoardImageDTO;
import com.mksong.mkboard3.dto.PageRequestDTO;
import com.mksong.mkboard3.dto.PageResponseDTO;
import com.mksong.mkboard3.mappers.BoardMapper;
import com.mksong.mkboard3.mappers.FileMapper;

import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@Service
@RequiredArgsConstructor
@Log4j2
public class BoardServiceImpl implements BoardService{

  private final BoardMapper boardMapper;

  private final FileMapper fileMapper;


  @Override
  public PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO) {

    List<BoardDTO> list = boardMapper.getList(pageRequestDTO);
    
    long total = boardMapper.listCount(pageRequestDTO);
        
        
      return PageResponseDTO.<BoardDTO>withAll()
        .list(list)
        .total(total)                
        .build();

  }


  @Override
  public BoardDTO getRead(Integer bno) {
    
    return boardMapper.getRead(bno);
  }

  @Override
  public void regist(BoardDTO boardDTO) {
    log.info("board regist service----------" + boardDTO);

    int count = boardMapper.regist(boardDTO);
    
    // 파일이름 list로 가져오기
    List<String> fileNames = boardDTO.getFileNames();


    // 게시판에서 등록 성공, 파일이 등록 되었으면 실행
    if(count > 0 && boardDTO.getFileNames() != null && boardDTO.getFileNames().isEmpty() == false) {
    
      //log.info("service ===============");
      //bno 가져오기
      Integer bno = boardDTO.getBno();
      //log.info("bno:....." + bno);

      AtomicInteger index = new AtomicInteger();

      // 등록된 파일 fileNames에서 추출
      List<Map<String, String>> list = fileNames.stream().map(str -> {
        // uuid 가져오기
        String uuid = str.substring(0, 36);
        // 실제 파일명 가져오기
        String fileName = str.substring(37);

        // return map에 담기
        return Map.of("uuid", uuid, "fileName", fileName, "bno", "" + bno, "ord", ""
                      + index.getAndIncrement());}).collect(Collectors.toList());

        //log.info("list:"+list);

        // 파일 등록 실행
        
        boardMapper.registerImage(list);        
      
    }

  }

  @Override
  public void modify(BoardDTO boardDTO) {
    
    //수정 업데이트
    int count = boardMapper.modify(boardDTO);
    //log.info("modify product count: " + count);

    //log.info("getBno: "+boardDTO.getBno());

    //기존파일 삭제
    boardMapper.deleteImage(boardDTO.getBno());

    

    //파일이름 List로 가져오기
    List<String> fileNames = boardDTO.getFileNames();
    //log.info("fileNames:" + fileNames);

    //게시판 등록 성공과 파일이 등록되었다면 실행
    if (count > 0) {
      //bno 가져오기
      Integer bno = boardDTO.getBno();
      //log.info("--------------------------------- bno: " + bno);

      AtomicInteger index = new AtomicInteger();

      //등록된 파일 fileNames에서 추출
      List<Map<String, String>> list = fileNames.stream().map(str -> {

        log.info("str:"+str);
        //uuid 가져오기
        String uuid = str.substring(0, 36);
        //실제 파일명 가져오기
        String fileName = str.substring(37);

        //return map에 담기
        return Map.of("uuid", uuid, "fileName", fileName, "bno", "" + bno, "ord", "" + index.getAndIncrement());
      }).collect(Collectors.toList());

      //log.info("=====================================================================");
      //log.info("modify list");
      //log.info(list);

      //파일 등록 실행
      boardMapper.registerImage(list);
    }

    // boardMapper.modify(boardDTO);
  }

  @Override
  public void delete(Integer bno) {
    
    boardMapper.delete(bno);

    boardMapper.deleteImage(bno);
  }


  // @Override
  // public List<String> getImage(Integer bno) {

  //   return boardMapper.selectImages(bno);
  // }  

}
