package org.spring.woo.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.woo.domain.ImgVO;
import org.spring.woo.service.ImgService;
import org.spring.woo.util.UploadFileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@RequestMapping("/upload/*")
@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Inject
	private ImgService imgService;

	// ��ǥ�̹��� �����ֱ�
	@ResponseBody
	@RequestMapping(value = "/show")
	public byte[] show(@RequestParam("bno") int bno) throws Exception {
		String path = "";
		String fileName = "";
		UploadFileUtils upload = new UploadFileUtils();
		if (imgService.imgList(bno) != null) {
			path = imgService.imgList(bno).get(0).getPath();
			fileName = imgService.imgList(bno).get(0).getFilename();
		}
		byte[] result = upload.show(path, fileName);
		return result;
	}

	@ResponseBody
	@RequestMapping(value = "/showAll")
	public byte[] showAll(@RequestParam("bno") int bno, @RequestParam("filename") String filename)
			throws Exception {
		UploadFileUtils upload = new UploadFileUtils();
		ImgVO vo =  new ImgVO();
		vo.setBno(bno);
		vo.setFilename(filename);
		String path  = imgService.imgShow(vo).getPath();
		byte[] result = upload.show(path, filename);
		return result;
	}

	@RequestMapping(value = "/imgDelete")
	public String imgDelete(int bno, String fileName) throws Exception {
		
//		String location = imgService.imgMain(bno).getPath();
//		File file = new File(location + fileName);
//		if (file.exists()) {
//			if (file.delete()) {
//				System.out.println("���ϻ��� ����");
//			} else {
//				System.out.println("���̻��� ����");
//
//			}
//		} else {
//			System.err.println("������ �������� �ƴǴϴ�.");
//		}
//
//		imgService.imgDeleteOne(bno, fileName);

		return "redirect:/board/read?bno=" + bno;

	}

}