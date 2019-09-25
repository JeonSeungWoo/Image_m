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
	public ImgVO imgMain(int bno) throws Exception {
		return session.selectOne("img.imgMain",bno);
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
	public void imgDeleteOne(int bno, String filename) throws Exception {
		HashMap<String,String> map = new HashMap<String, String>();
		map.put("bno", Integer.toString(bno));
		map.put("fileName", filename);
		session.delete("img.imgDeleteOne",map);
	}


}
