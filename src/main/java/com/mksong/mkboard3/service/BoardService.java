package com.mksong.mkboard3.service;

import java.util.List;

import com.mksong.mkboard3.dto.BoardDTO;
import com.mksong.mkboard3.dto.PageRequestDTO;
import com.mksong.mkboard3.dto.PageResponseDTO;

public interface BoardService {
  
  PageResponseDTO<BoardDTO> getList(PageRequestDTO pageRequestDTO);

  BoardDTO getRead(Integer bno);

  void regist(BoardDTO boardDTO);

  void modify(BoardDTO boardDTO);

  void delete(Integer bno);

  // 파일 업로드
  // Long setBoard(BoardDTO boardDTO, boolean makeThumbnail);

  // List<String> getImage(Integer bno);
  

}
