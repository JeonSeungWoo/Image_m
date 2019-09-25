package org.spring.woo.dao;

import java.util.List;

import org.spring.woo.domain.ImgVO;

public interface ImgDAO {
	public void imgInsert(ImgVO vo)throws Exception;
	public ImgVO imgMain(int bno)throws Exception;
	//전체 이미지 보기
	public List<ImgVO> imgList(int bno)throws Exception;
	//전체 대표이미지 보기
	public void imgDelete(int bno)throws Exception;
	//수정 처리.(수정은 삭제후 인설트)
	public void imgInsertOne(ImgVO vo)throws Exception;
	public void imgDeleteOne(int bno,String filename)throws Exception;
}
