package com.mksong.mkboard3.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.mksong.mkboard3.dto.BoardImageDTO;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;

@RestController
@Log4j2
public class FileUploadController {

  public static class DataNotFoundException extends RuntimeException{
    public DataNotFoundException(String msg){
      super(msg);
    }
  }
  
  @Value("${com.mksong.upload.path}")
  private String uploadPath;

  @PostMapping("/upload")
  public List<BoardImageDTO> upload(MultipartFile[] files) {
    //파일이 없을 경우
    if(files == null || files.length == 0) {
      return null;
    }

    List<BoardImageDTO> fileList = new ArrayList<>();
    
    //파일 여러개 등록 for each 사용
    for (MultipartFile file : files) {
      // 초기값 null
      BoardImageDTO result = null;
      // 실제 파일명
      String fileName = file.getOriginalFilename();
      // 파일 크기
      long size = file.getSize();

      // uuid(PK) 생성
      String uuidStr = UUID.randomUUID().toString();
      // 파일명 저장
      String saveFileName = uuidStr + "_" + fileName;
      // 실제 파일 저장
      File saveFile = new File(uploadPath, saveFileName);

      log.info("saveFileName:" + saveFileName);

      // 파일 복사시 예외처리
      try {
        // FileCopyUtils 사용, InputStream으로 받고 OutPutStream으로 보내줌
        // getBytes() 파일을 바이트 배열로 받아줌
        FileCopyUtils.copy(file.getBytes(), saveFile);

        //dto에 넣어준다
        result = BoardImageDTO.builder()
            .uuid(uuidStr)
            .fileName(fileName)
            .build();

        // 파일 확장자 이미지 체크
        String mimeType = Files.probeContentType(saveFile.toPath());
        log.info("mimeType: ..... " + mimeType);

        //mimeType이 image인지 체크
        if(mimeType.startsWith("image")){
          // 섬네일 파일 생성
          File thumbFile = new File(uploadPath, "s_" + saveFileName);
          Thumbnailator.createThumbnail(saveFile, thumbFile, 80, 80);
          //img true로 반환 getLink사용 위함
          result.setImg(true);
        }        

      } catch (IOException e) {
        e.printStackTrace();

      }

      fileList.add(result);

    } 

    return fileList;


  }

  @GetMapping("/view/{fileName}")
    public ResponseEntity<Resource> viewFileGET(@PathVariable String fileName){

        Resource resource = new FileSystemResource(uploadPath+File.separator + fileName);
        String resourceName = resource.getFilename();
        HttpHeaders headers = new HttpHeaders();

        try{
            headers.add("Content-Type", Files.probeContentType( resource.getFile().toPath() ));
        } catch(Exception e){
            return ResponseEntity.internalServerError().build();
        }
        return ResponseEntity.ok().headers(headers).body(resource);
    }

    @DeleteMapping("/removeFile/{fileName}")
    public Map<String, String> removeFile(@PathVariable("fileName") String fileName){

      
      log.info("delete file.....");
      log.info(fileName);
      
      File originFile = new File(uploadPath, fileName);

      try {
        String mimeType = Files.probeContentType(originFile.toPath());

        if(mimeType.startsWith("image")){
          File thumbFile = new File(uploadPath, "s_"+ fileName);
          thumbFile.delete();
        }
        originFile.delete();
        

      } catch (IOException e) {
        
        e.printStackTrace();
      }

      return Map.of("result","success");
    }
  

}
