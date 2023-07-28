package com.mksong.mkboard3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mksong.mkboard3.dto.PageRequestDTO;
import com.mksong.mkboard3.dto.PageResponseDTO;
import com.mksong.mkboard3.dto.ReplyDTO;
import com.mksong.mkboard3.mappers.ReplyMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReplyServiceImpl implements ReplyService {

  private final ReplyMapper replyMapper;

  @Override
  public PageResponseDTO<ReplyDTO> getList(Long bno, PageRequestDTO pageRequestDTO) {

    List<ReplyDTO> list = replyMapper.selectList(bno, pageRequestDTO);

    return PageResponseDTO.<ReplyDTO>withAll()
       .list(list)       
       .build();
  }

  @Override
  public Long replyRegist(ReplyDTO replyDTO) {
    replyMapper.replyRegist(replyDTO);

		//result  1
		Long newRno = replyDTO.getRno();

		return newRno;
  }

  @Override
  public ReplyDTO readOne(Long rno) {

    return replyMapper.readOne(rno);
  }

  @Override
  public void modify(ReplyDTO replyDTO) {
    
    replyMapper.modify(replyDTO);
  }

  @Override
  public void delete(Long rno) {

    replyMapper.delete(rno);
  }

  

  
  
}
