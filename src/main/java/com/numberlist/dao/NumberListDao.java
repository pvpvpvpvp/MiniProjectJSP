package com.numberlist.dao;

import java.util.List;

import com.numberlist.vo.NumberListVo;


public interface NumberListDao { // 인터페이스
	//리스트
	public List<NumberListVo> getList();
	//등록
	public int insert(NumberListVo vo);
	//검색
	public List<NumberListVo> getSearch(String find);
	//삭제
	public int delete(Long no);
	//정렬
	public boolean insert(NumberListVo vo, Long checkIndex); 
	//종료 아마 불필요
	
			

}
