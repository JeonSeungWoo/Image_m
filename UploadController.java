package org.spring.woo.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.woo.domain.ImgVO;
import org.spring.woo.service.ImgService;
import org.spring.woo.util.UploadFileUtils;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/upload/*")
@Controller
public class UploadController {

	private static final Logger logger = LoggerFactory.getLogger(UploadController.class);

	@Inject
	private ImgService imgService;

	// 대표이미지 보여주기
	@ResponseBody
	@RequestMapping(value = "/show")
	public ResponseEntity<Resource> show(
			@RequestHeader("User-Agent")String userAgent,@RequestParam("bno") int bno) throws Exception {
		String path = "";
		String fileName = "";
		UploadFileUtils upload = new UploadFileUtils();
		if (imgService.imgList(bno) != null) {
			path = imgService.imgList(bno).get(0).getPath();
			fileName = imgService.imgList(bno).get(0).getFilename();
		}
		ResponseEntity<Resource> result = upload.fileShow(userAgent, path, fileName);
		return result;
	}

	// Resource = core.io;
	//ResponseEntity 타입은 byte로 대채 가능하지만 ResponseEntity좀더 간편하다.
	@ResponseBody
	@RequestMapping(value = "/file", method = RequestMethod.GET, produces = MediaType.APPLICATION_ATOM_XML_VALUE)
	public ResponseEntity<Resource> file(@RequestHeader("User-Agent")String userAgent,
			@RequestParam("bno") int bno, @RequestParam("filename") String filename)
			throws Exception {
		UploadFileUtils upload = new UploadFileUtils();
		ImgVO vo = new ImgVO();
		vo.setBno(bno);
		vo.setFilename(filename);
		String path = imgService.imgShow(vo).getPath();
		ResponseEntity<Resource> result = upload.fileShow(userAgent, path, filename);
		return result;
	}

	@RequestMapping(value = "/imgDelete")
	public String imgDelete(@RequestParam("bno")int bno,  @RequestParam("filename")String filename) throws Exception {
		ImgVO vo = new ImgVO();
		UploadFileUtils upload = new UploadFileUtils();
		vo.setBno(bno);
		vo.setFilename(filename);
		String location = imgService.imgShow(vo).getPath();
		upload.deleteFile(location, filename);
		imgService.imgDeleteOne(vo);
		return "redirect:/board/read?bno=" + bno;
	}
	
	// insertImage
		@RequestMapping(value = "/insertImage")
		public String insertImage(int bno, List<MultipartFile> file) throws Exception {
			System.out.println("뭐여?");
			ImgVO ivo = new ImgVO();
			ivo.setBno(bno);
			System.out.println(ivo);

			for (int i = 0; i < file.size(); i++) {
				String originalName = file.get(i).getOriginalFilename();
				byte[] fileData = file.get(i).getBytes();
				// 유틸시작
				String uploadedFileName = UploadFileUtils.saveFile("C:\\Temp", originalName, fileData);
				String path = "C:\\Temp" + uploadedFileName.substring(0, 12);
				String saveFileName = uploadedFileName.substring(uploadedFileName.lastIndexOf("/") + 1);
				String formatName = originalName.substring(originalName.lastIndexOf(".") + 1);
				//확장자 null 체크
				System.out.println(formatName);
				if (formatName == null || formatName.equals("")) {
				}else{
					ivo.setFilename(saveFileName);
					ivo.setPath(path);
					imgService.imgInsertOne(ivo);
				}
			}
			return "redirect:/board/read?page=1&bno=" + bno;

		}


}
