package com.mksong.mkboard3.dto;

import groovy.transform.ToString;
import groovy.transform.builder.Builder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BoardDTO {
  
  private Integer bno;
  private String title;
  private String content;
  private String writer;
  private String duedate;


}
