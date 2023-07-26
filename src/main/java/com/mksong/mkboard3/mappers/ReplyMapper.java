package com.mksong.mkboard3.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.mksong.mkboard3.dto.PageRequestDTO;
import com.mksong.mkboard3.dto.ReplyDTO;

public interface ReplyMapper {
  
  int replyRegist(ReplyDTO replyDTO);

  List<ReplyDTO> selectList(
        @Param("bno")Long bno, 
        @Param("pr")PageRequestDTO pageRequestDTO);
}
