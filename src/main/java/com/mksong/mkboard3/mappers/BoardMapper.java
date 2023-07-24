package com.mksong.mkboard3.mappers;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.mksong.mkboard3.dto.BoardDTO;

@Transactional
public interface BoardMapper {

  List<BoardDTO> getList();

  BoardDTO getRead(Integer bno);

  int regist(BoardDTO boardDTO);

  int modify(BoardDTO boardDTO);

  int delete(Integer bno);



}
