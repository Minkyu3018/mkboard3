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


  // @Value("{com.mksong.upload.path}")
  // private String path;

  // public static class UploadException extends RuntimeException {

  //   public UploadException(String msg) {
  //     super(msg);
  //   }
  // }


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

    int count = boardMapper.regist(boardDTO);
    
    // 파일이름 list로 가져오기
    List<String> fileNames = boardDTO.getFileNames();

    // 게시판에서 등록 성공, 파일이 등록 되었으면 실행
    if(count > 0 && boardDTO.getFileNames() != null && boardDTO.getFileNames().isEmpty()) {
    
    //bno 가져오기
    Integer bno = boardDTO.getBno();
    log.info("bno:....." + bno);

    AtomicInteger index = new AtomicInteger();

    // 등록된 파일 fileNames에서 추출
    List<Map<String, String>> list = fileNames.stream().map(str -> {
      // uuid 가져오기
      String uuid = str.substring(0, 36);
      // 실제 파일명 가져오기
      String fileName = str.substring(37);

      // return map에 담기
      return Map.of("uuid", uuid, "file_name", fileName, "bno", "" + bno, "ord",
                     ""+ index.getAndIncrement());}).collect(Collectors.toList());

      log.info(list);

      // 파일 등록 실행
      fileMapper.registerImage(list);
    
   }

  }

  @Override
  public int modify(BoardDTO boardDTO) {
    
    return boardMapper.modify(boardDTO);
  }

  @Override
  public int delete(Integer bno) {
    
    return boardMapper.delete(bno);
  }


  // @Override
  // public Long setBoard(BoardDTO boardDTO, boolean makeThumbnail) {
    
  //   List<MultipartFile> files = boardDTO.getFiles();

  //   // 파일 저장하고 이름만 추출
  //   if(files == null || files.size() == 0) {
  //     throw new UploadException("No file");
  //   }

  //   List<String> uploadFileNames = new ArrayList<>();

  //   for (MultipartFile mFile : files) {

  //     String originalFileName = mFile.getOriginalFilename();
  //     String uuid = UUID.randomUUID().toString();

  //     String mimeType = servletContext.getMimeType(originalFileName);
  //     log.info("mimeType..." + mimeType);

  //     //save할 파일이름
  //     String saveFileName = uuid+"_"+originalFileName;
  //     File saveFile = new File(path, saveFileName);

  //     //예외처리
  //     try ( InputStream in = mFile.getInputStream();
  //           OutputStream out = new FileOutputStream(saveFile);
  //     ) {

  //       // 파일 Copy
  //       FileCopyUtils.copy(in, out);

  //       // 이미지 파일일 경우
  //       if(makeThumbnail && mimeType.contains("image")) {
  //         // 섬네일 생성
  //         File thumOutFile = new File(path, "s_" + saveFileName);
  //         Thumbnailator.createThumbnail(saveFile, thumOutFile, 200, 200);
  //       }

  //       uploadFileNames.add(saveFileName);

  //     } catch(Exception e) {
  //         throw new UploadException("Upload Fail" + e.getMessage());
  //     }

  //   }
  //   //이름을 DB에 저장
  //   log.info("파일 이름 확인됨");
  //   BoardImageDTO boardImageDTO = new BoardImageDTO();
  //   Long bno = boardMapper.setBoard(boardDTO);
  //   boardImageDTO.setImage_tno(boardDTO.getBno());

  //   int ord = 0;
  //   for(String uploadFileName : uploadFileNames) {
  //     boardImageDTO.setImage(uploadFileName);
  //     boardImageDTO.setOrd(ord++);
  //     boardMapper.setBoardImage(boardImageDTO);
  //   }



  //   return bno;

  // }




}
