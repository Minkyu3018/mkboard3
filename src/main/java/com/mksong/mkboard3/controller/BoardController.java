package com.mksong.mkboard3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mksong.mkboard3.dto.BoardDTO;
import com.mksong.mkboard3.dto.PageRequestDTO;
import com.mksong.mkboard3.dto.PageResponseDTO;
import com.mksong.mkboard3.service.BoardService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
@Log4j2
public class BoardController {

  private final BoardService boardService;

  @GetMapping("/list")
  public void getList(PageRequestDTO pageRequestDTO, Model model) {

    PageResponseDTO<BoardDTO> pageResponseDTO
        = boardService.getList(pageRequestDTO);

    model.addAttribute("pageResponseDTO", pageResponseDTO);

    //log.info("getList: "+pageResponseDTO.getList());
    

  }

  @GetMapping("/read/{bno}")
  public String getRead(@PathVariable("bno") Integer bno, Model model) {

    model.addAttribute("dto", boardService.getRead(bno));

    //log.info("get read: ..."+boardService.getRead(bno));

    return "/board/read";

  }

  @GetMapping("/regist")
  public void getRegist(BoardDTO boardDTO){

    //log.info("Get Regist.....");

  }

  @PostMapping("/regist")
  public String postRegist(BoardDTO boardDTO){

    boardService.regist(boardDTO);

    //log.info("Post Regist......");
    log.info("Controller postRegist: " + boardDTO);

    return "redirect:/board/list";

  }

  @GetMapping("/modify/{bno}")
  public String modify(@PathVariable("bno") Integer bno, Model model) {

    model.addAttribute("dto", boardService.getRead(bno));

    return "/board/modify";
  }

  @PostMapping("/modify/{bno}")
  public String postModify(@PathVariable("bno")Integer bno, BoardDTO boardDTO) {

    boardService.modify(boardDTO);

    return "redirect:/board/read/" + bno;
  }

  @PostMapping("/delete/{bno}")
  public String delete(@PathVariable("bno")Integer bno){

    boardService.delete(bno);

    return "redirect:/board/list";
    
  }  



  
}
