package com.mksong.mkboard3.service;

import com.mksong.mkboard3.dto.PageRequestDTO;
import com.mksong.mkboard3.dto.PageResponseDTO;
import com.mksong.mkboard3.dto.ReplyDTO;

public interface ReplyService {

  PageResponseDTO<ReplyDTO> getList(Long bno, PageRequestDTO pageRequestDTO);

  Long replyRegist(ReplyDTO replyDTO);
  
}
