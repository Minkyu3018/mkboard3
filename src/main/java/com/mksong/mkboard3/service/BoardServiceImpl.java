package com.mksong.mkboard3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.mksong.mkboard3.dto.BoardDTO;
import com.mksong.mkboard3.mappers.BoardMapper;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService{

  private final BoardMapper boardMapper;

  @Override
  public List<BoardDTO> getList() {
  
    return boardMapper.getList();
  }

  @Override
  public BoardDTO getRead(Integer bno) {
    
    return boardMapper.getRead(bno);
  }

  @Override
  public int regist(BoardDTO boardDTO) {
    
    return boardMapper.regist(boardDTO);
  }

  @Override
  public int modify(BoardDTO boardDTO) {
    
    return boardMapper.modify(boardDTO);
  }

  @Override
  public int delete(Integer bno) {
    
    return boardMapper.delete(bno);
  }


}
