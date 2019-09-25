package org.spring.woo.service;

import java.util.List;

import javax.inject.Inject;

import org.spring.woo.dao.ImgDAO;
import org.spring.woo.domain.ImgVO;
import org.springframework.stereotype.Service;

@Service
public class ImgServiceImpl implements ImgService {

	@Inject
	private ImgDAO dao;

	@Override
	public void imgInsert(ImgVO vo) throws Exception {
		dao.imgInsert(vo);
	}

	

	@Override
	public List<ImgVO> imgList(int bno) throws Exception {
		return dao.imgList(bno);
	}

	@Override
	public void imgDelete(int bno) throws Exception {
		dao.imgDelete(bno);
	}

	@Override
	public void imgInsertOne(ImgVO vo) throws Exception {
		dao.imgInsertOne(vo);
	}

	@Override
	public void imgDeleteOne(int bno, String filename) throws Exception {
		dao.imgDeleteOne(bno, filename);
	}



	@Override
	public ImgVO imgShow(int bno,String filename) throws Exception {
		// TODO Auto-generated method stub
		return dao.imgShow(bno,filename);
	}

}
