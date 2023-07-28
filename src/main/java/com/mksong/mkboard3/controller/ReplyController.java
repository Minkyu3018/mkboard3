package com.mksong.mkboard3.controller;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mksong.mkboard3.dto.PageRequestDTO;
import com.mksong.mkboard3.dto.PageResponseDTO;
import com.mksong.mkboard3.dto.ReplyDTO;
import com.mksong.mkboard3.service.ReplyService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/replies/")
public class ReplyController {

  private final ReplyService replyService;

  @GetMapping(value = "{bno}/list" ,produces = MediaType.APPLICATION_JSON_VALUE )
  public PageResponseDTO<ReplyDTO> getReplyList(
    @PathVariable("bno") Long bno, PageRequestDTO pageRequestDTO){
    
    log.info("bno: " + bno);

    return replyService.getList(bno, pageRequestDTO);
  }

  @PostMapping("{bno}/new")
  public Map<String, Long> register(@PathVariable("bno") Long bno, 
    @Valid  @RequestBody ReplyDTO replyDTO  , BindingResult bindingResult) throws BindException{

    replyDTO.setBno(bno);

    if(bindingResult.hasErrors()){
            throw new BindException(bindingResult);
    }

    Long rno = replyService.replyRegist(replyDTO);

    log.info(replyDTO);
    
    return Map.of("result", rno);
  }

  @GetMapping("read/{rno}")
  public ReplyDTO readOne(@PathVariable("rno") Long rno){

    log.info("get | replyRead-----------------------------");
    log.info(rno);

    return replyService.readOne(rno);
  }

  @PutMapping("modify/{rno}")
  public Map<String, Long> modify(@RequestBody ReplyDTO replyDTO){

  log.info("modify | replyModify-----------------------------");

  replyService.modify(replyDTO);

  return Map.of("result", replyDTO.getRno());
  }

  @DeleteMapping("delete/{rno}")
  public Map<String, Long> delete(@PathVariable("rno") Long rno){

  log.info("delete | replyDelete-----------------------------");

  replyService.delete(rno);

  return Map.of("result", rno);
  
  }



}



