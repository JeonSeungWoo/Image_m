package org.spring.woo.dao;

import java.util.List;

import org.spring.woo.domain.BoardVO;
import org.spring.woo.domain.Paging;

public interface BoardDAO {
	//BoardVO를 파라미터로 넘긴다.
	public void insert(BoardVO vo) throws Exception;
    //int bno를 파라미터로 받고 VO로 저장한다.
	public BoardVO read(int bno) throws Exception;
	public void update(BoardVO vo) throws Exception;
	public void delete(int bno) throws Exception;
	public List<BoardVO>list(Paging paging)throws Exception;
	public int listCount()throws Exception;
}
