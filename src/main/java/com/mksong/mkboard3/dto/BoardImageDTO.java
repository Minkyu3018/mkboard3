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

  private Integer image_tno;

  private String image;

  private int ord;
  
}
