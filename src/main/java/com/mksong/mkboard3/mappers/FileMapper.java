package com.mksong.mkboard3.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Select;

import com.mksong.mkboard3.dto.BoardDTO;

public interface FileMapper {

  // file register

  int registerImage(List<Map<String, String>> imageList);

  int deleteImage(Integer bno);

  @Select("select * from tbl_board b where b.bno = #{bno}")
    BoardDTO getRead(Integer bno);

    @Select("select concat(uuid, '_' , fileName) fileName from tbl_board_image where bno = #{bno} order by ord")
    List<String> selectImages(Integer bno);
  
}
