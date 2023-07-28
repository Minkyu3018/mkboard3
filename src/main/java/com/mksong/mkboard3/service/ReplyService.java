package com.mksong.mkboard3.service;

import org.springframework.transaction.annotation.Transactional;

import com.mksong.mkboard3.dto.PageRequestDTO;
import com.mksong.mkboard3.dto.PageResponseDTO;
import com.mksong.mkboard3.dto.ReplyDTO;

@Transactional
public interface ReplyService {

  PageResponseDTO<ReplyDTO> getList(Long bno, PageRequestDTO pageRequestDTO);

  Long replyRegist(ReplyDTO replyDTO);

  void modify(ReplyDTO replyDTO);

  ReplyDTO readOne(Long rno);
  
  void delete(Long rno);
  
}
