package org.spring.woo.service;

import java.util.List;

import org.spring.woo.domain.BoardVO;
import org.spring.woo.domain.Paging;
import org.springframework.web.multipart.MultipartFile;

public interface BoardService {
	public void insert(BoardVO vo,List<MultipartFile>file) throws Exception;
	public BoardVO read(int bno) throws Exception;
	public void update(BoardVO vo) throws Exception;
	public void delete(int bno) throws Exception;
	public List<BoardVO>list(Paging paging)throws Exception;
	public int listCount()throws Exception;
}
