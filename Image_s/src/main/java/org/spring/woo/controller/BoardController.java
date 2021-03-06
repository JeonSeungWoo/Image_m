package org.spring.woo.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.woo.domain.BoardVO;
import org.spring.woo.domain.ImgVO;
import org.spring.woo.domain.Paging;
import org.spring.woo.service.BoardService;
import org.spring.woo.service.ImgService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller     //컨트롤러 어노테이션
@RequestMapping("/board/*")   //경로 설정 메소드 (board밑으로 전부 경로 설정된다.)
public class BoardController {
	//logger.info사용 (sysout같은거다.{기본 컨트롤러에 이미 되어 있다.})
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	//service 가져오기
	@Inject
	private BoardService service;
	
	@Inject
	private ImgService iservice;
	
	
	//insertPage설정.(view.board.insertPage로 경로가 설정 되어 있다.)
	//web.xmp에서 확인 가능.
	@RequestMapping(value = "/insertPage", method = RequestMethod.GET)
	public void insertPage() throws Exception {
	}
	
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(Model model, BoardVO vo, @RequestParam("file")List<MultipartFile> file) throws Exception {
		service.insert(vo, file);
		return "redirect:/board/listPage?page=1";
	}
	
	//read기능 bno를 파라미터로 가져와야 한다.
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readPage(Model model, @RequestParam("bno") int bno) throws Exception {
		List<ImgVO> list = iservice.imgList(bno);
		model.addAttribute("list", list);
		model.addAttribute("listSize", list.size());
		model.addAttribute("vo", service.read(bno));
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String update(Model model, BoardVO vo) throws Exception {
		logger.info("update Test ::   ");
		service.update(vo);
		return "redirect:/board/listPage?page=1";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public String delete(Model model, int bno) throws Exception {
		service.delete(bno);
		return "redirect:/board/listPage?page=1";

	}

	@RequestMapping(value = "/listPage", method = RequestMethod.GET)
	public void listPage(Model model,int page,Paging paging) throws Exception {
		                                                                                                                                                        
		model.addAttribute("list",service.list(paging));
		model.addAttribute("Paging", new Paging(page, service.listCount()));
	}

	

		
}
