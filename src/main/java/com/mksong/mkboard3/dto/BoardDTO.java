package com.mksong.mkboard3.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BoardDTO {
  
  private Integer bno;
  private String title;
  private String content;
  private String writer;
  private String duedate;

  // 파일 리스트 

  private List<String> fileNames;
  


}
