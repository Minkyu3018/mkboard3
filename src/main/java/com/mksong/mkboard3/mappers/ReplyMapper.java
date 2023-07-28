package com.mksong.mkboard3.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mksong.mkboard3.dto.PageRequestDTO;
import com.mksong.mkboard3.dto.ReplyDTO;

public interface ReplyMapper {
  
  //등록
  int replyRegist(ReplyDTO replyDTO);

  List<ReplyDTO> selectList(
        @Param("bno")Long bno, 
        @Param("pr")PageRequestDTO pageRequestDTO);

  //수정
  int modify(ReplyDTO replyDTO);

  //읽기
  ReplyDTO readOne(Long rno);

  //삭제
  int delete(Long rno);

  

  
}
