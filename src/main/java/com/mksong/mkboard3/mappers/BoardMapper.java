package com.mksong.mkboard3.mappers;

import java.util.List;
import java.util.Map;

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

  

  int registerImage(List<Map<String, String>> imageList);

  int deleteImage(Integer bno);
  



}
