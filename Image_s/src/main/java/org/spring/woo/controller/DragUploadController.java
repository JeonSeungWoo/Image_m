package org.spring.woo.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spring.woo.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/dragDrop/*")
@Controller
public class DragUploadController {
	private static final Logger logger = LoggerFactory.getLogger(DragUploadController.class);
	
	
	@Inject
	BoardService service;
	
	
	@RequestMapping(value = "/insertPage")
	public void insertPage()throws Exception{
		
	}
	
	
	
	
	
    
	
	
}
