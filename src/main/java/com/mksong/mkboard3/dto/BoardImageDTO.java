package com.mksong.mkboard3.dto;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class BoardImageDTO {

  private String uuid;
  private String fileName;
  private boolean img;

  public String getLink(){

    if(img) {
      return "s_" + uuid + "_" + fileName;
    }else {
      return "default.jpg";
    }

  }

  
}
