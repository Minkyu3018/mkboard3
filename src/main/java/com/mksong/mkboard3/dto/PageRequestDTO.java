package com.mksong.mkboard3.dto;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@ToString
public class PageRequestDTO {

  @Builder.Default
  private int page = 1;
  @Builder.Default
  private int size = 10;

  private String type;
  private String keyword;
  private String link;
  

  public void setPage(int page){
    if(page <= 0){
      this.page = 1;
    }else {
      this.page = page;
    }
  }
  public void setSize(int size){
    if(size > 100 || size < 0){
      this.size = 10;
    }else {
      this.size = size;
    }
  }

  public int getSkip(){
    return (this.page -1) * this.size;
  }

  public int getEnd(){
    return this.page * this.size;
  }

  public int getCountEnd() {

    int temp = (int) (Math.ceil(this.page/10.0)) * (10 * size);


    return temp + 1;
  }

  ///// 댓글처리

  //type 배열로 반환 처리
  public String[] getTypes(){
    if(this.type == null || this.type.isEmpty()){
      return null;
    }
    return this.type.split("");
  }

  public String getLink(){

    if(link == null){
      //문자열 합치기
      StringBuilder strBuilder = new StringBuilder();

      //page, size 쿼리스트링 전달
      strBuilder.append("page=" + this.page);
      strBuilder.append("&size=" + this.size);      

      //검색타입
      if(type != null && type.length() > 0){
        strBuilder.append("&type=" + this.type);
      }

      //검색어
      if(keyword != null && keyword.length() > 0){
        try {
          strBuilder.append("&keyword=" + URLEncoder.encode(keyword,"UTF-8"));
        } catch (UnsupportedEncodingException e) {
          e.printStackTrace();
        }
      }

      //toString으로 합친 문자열 String으로 반환
      link = strBuilder.toString();
    }

    return link;
  }
  
}
