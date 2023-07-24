package com.mksong.mkboard3.service;

import java.util.List;

import com.mksong.mkboard3.dto.BoardDTO;

public interface BoardService {
  
  List<BoardDTO> getList();

  BoardDTO getRead(Integer bno);

  int regist(BoardDTO boardDTO);

  int modify(BoardDTO boardDTO);

  int delete(Integer bno);

}
