package org.spring.woo.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.woo.domain.BoardVO;
import org.spring.woo.domain.Paging;
import org.spring.woo.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller     //컨트롤러 어노테이션
@RequestMapping("/board/*")   //경로 설정 메소드 (board밑으로 전부 경로 설정된다.)
public class BoardController {
	//logger.info사용 (sysout같은거다.{기본 컨트롤러에 이미 되어 있다.})
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	//service 가져오기
	@Inject
	private BoardService service;
	
	//insertPage설정.(view.board.insertPage로 경로가 설정 되어 있다.)
	//web.xmp에서 확인 가능.
	@RequestMapping(value = "/insertPage", method = RequestMethod.GET)
	public void insertPage() throws Exception {
	}
	
	//insert 기능 redirect는 이 경로로 이동하게 된다.
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public String insert(Model model, BoardVO vo) throws Exception {
		service.insert(vo);
		return "redirect:/board/listPage?page=1";
	}
	//read기능 bno를 파라미터로 가져와야 한다.
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public void readPage(Model model, @RequestParam("bno") int bno) throws Exception {
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
