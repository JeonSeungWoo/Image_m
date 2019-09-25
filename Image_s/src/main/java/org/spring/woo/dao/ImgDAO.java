package org.spring.woo.dao;

import java.util.List;

import org.spring.woo.domain.ImgVO;

public interface ImgDAO {
	public void imgInsert(ImgVO vo)throws Exception;
	public ImgVO imgMain(int bno)throws Exception;
	//��ü �̹��� ����
	public List<ImgVO> imgList(int bno)throws Exception;
	//��ü ��ǥ�̹��� ����
	public void imgDelete(int bno)throws Exception;
	//���� ó��.(������ ������ �μ�Ʈ)
	public void imgInsertOne(ImgVO vo)throws Exception;
	public void imgDeleteOne(int bno,String filename)throws Exception;
}
