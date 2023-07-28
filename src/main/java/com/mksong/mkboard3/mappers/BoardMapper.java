package com.mksong.mkboard3.mappers;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mksong.mkboard3.dto.BoardDTO;
import com.mksong.mkboard3.dto.BoardImageDTO;
import com.mksong.mkboard3.dto.PageRequestDTO;

@Transactional
public interface BoardMapper {

  List<BoardDTO> getList(PageRequestDTO pageRequestDTO);

  BoardDTO getRead(Integer bno);

  int regist(BoardDTO boardDTO);

  int modify(BoardDTO boardDTO);

  int delete(Integer bno);

  long listCount(PageRequestDTO pageRequestDTO);


  //파일업로드
  Long setBoard(BoardDTO boardDTO);
  
  void setBoardImage(BoardImageDTO boardImageDTO);
  



}
