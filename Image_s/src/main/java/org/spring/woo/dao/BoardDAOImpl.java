package org.spring.woo.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.spring.woo.domain.BoardVO;
import org.spring.woo.domain.Paging;
import org.springframework.stereotype.Repository;

@Repository
public class BoardDAOImpl implements BoardDAO{
    
	//sql factory 가져오기 inject awtowired전부 가능
	@Inject
	private SqlSession session;
	String name = "board.";	
	
	@Override
	public void insert(BoardVO vo) throws Exception {
		//등록기능 mapper의 경로를 써준다.
		session.insert(name+"insert",vo);
	}
	@Override
	public BoardVO read(int bno) throws Exception {
		//select 구문중 1개의 값만 있을때 사용.
		return session.selectOne(name+"read",bno);
	}
	@Override
	public void update(BoardVO vo) throws Exception {
		session.update(name+"update",vo);
	}
	@Override
	public void delete(int bno) throws Exception {
		session.delete(name+"delete",bno);
	}
	@Override
	public List<BoardVO> list(Paging paging) throws Exception {
		// TODO Auto-generated method stub
		return session.selectList(name + "list",paging);
	}
	@Override
	public int listCount() throws Exception {
		// TODO Auto-generated method stub
		return session.selectOne(name+"listCount");
	}

}
