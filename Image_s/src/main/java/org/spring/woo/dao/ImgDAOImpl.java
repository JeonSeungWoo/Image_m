package org.spring.woo.dao;

import java.util.HashMap;
import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.spring.woo.domain.ImgVO;
import org.springframework.stereotype.Repository;

@Repository
public class ImgDAOImpl implements ImgDAO{

	@Inject
	private SqlSession session;
	
	@Override
	public void imgInsert(ImgVO vo) throws Exception {
		session.insert("img.imgInsert",vo);
	}
	
	@Override
	public List<ImgVO> imgList(int bno) throws Exception {
		return session.selectList("img.imgList",bno);
	}
	@Override
	public void imgDelete(int bno) throws Exception {
		session.delete("img.imgDelete",bno);
	}
	@Override
	public void imgInsertOne(ImgVO vo) throws Exception {
		session.insert("img.imgInsertOne",vo);
	}
	@Override
	public void imgDeleteOne(ImgVO vo) throws Exception {
		session.delete("img.imgDeleteOne",vo);
	}

	@Override
	public ImgVO imgShow(ImgVO vo) throws Exception {
		return session.selectOne("img.imgShow",vo);
	}


}
