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

  // 대표이미지 하나 가져오기
  private String images;

  // 썸네일 작업
  private String picture;

  // 등록, 수정, 업로드된 파일 데이터를 수집
  @Builder.Default
  private List<MultipartFile> files = new ArrayList<>(); // 업로드 파일

  public String getPicture() {
    if(images != null) {
      picture = images.split("\\.")[0] + "." + "png";

        return picture;
    }

    return "";
  }


}
