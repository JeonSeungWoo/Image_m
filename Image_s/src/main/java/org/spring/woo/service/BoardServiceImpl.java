package org.spring.woo.service;

import java.io.File;
import java.util.List;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.spring.woo.dao.BoardDAO;
import org.spring.woo.dao.ImgDAO;
import org.spring.woo.domain.BoardVO;
import org.spring.woo.domain.ImgVO;
import org.spring.woo.domain.Paging;
import org.spring.woo.util.UploadFileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
public class BoardServiceImpl implements BoardService {
	@Inject
	private BoardDAO dao;

	@Inject
	private ImgDAO idao;

	@Resource(name = "uploadPath")
	private String uploadPath;

	@Transactional
	@Override
	public void insert(BoardVO vo, List<MultipartFile> file) throws Exception {
		// 이미지를 추가.

		dao.insert(vo);

		for (int i = 0; i < file.size(); i++) {
			String originalName = file.get(i).getOriginalFilename();
			byte[] fileData = file.get(i).getBytes();
			// 유틸시작
			String uploadedFileName = UploadFileUtils.uploadFile(uploadPath, originalName, fileData);
			String path = uploadPath + uploadedFileName.substring(0, 12);
			String saveFileName = uploadedFileName.substring(uploadedFileName.lastIndexOf("/") + 1);
			String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
			//확장자 null 체크
			if (formatName == null || formatName.equals("")) {
			}else{
				ImgVO ivo = new ImgVO();
				ivo.setFilename(saveFileName);
				ivo.setPath(path);
				idao.imgInsert(ivo);
			}
		}

	}

	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	@Override
	public void update(BoardVO vo) throws Exception {
		dao.update(vo);
	}

	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}

	@Override
	public List<BoardVO> list(Paging paging) throws Exception {
		// TODO Auto-generated method stub
		return dao.list(paging);
	}

	@Override
	public int listCount() throws Exception {
		// TODO Auto-generated method stub
		return dao.listCount();
	}
}
