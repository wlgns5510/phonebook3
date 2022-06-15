package com.javaex.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.javaex.service.PhoneService;
import com.javaex.vo.PersonVo;

@Controller
public class PhoneController {

	//필드
	@Autowired
	private PhoneService phoneService;
	
	//생성자
	
	//메소드 gs
	
	//메소드 일반
	
	//전화번호 리스트
	@RequestMapping(value="/list", method= {RequestMethod.GET, RequestMethod.POST})
	public String list(Model model) {
		System.out.println("PhoneController>list()");
		
		//Service를 통해서 personList(주소)를 가져온다
		List<PersonVo> personList = phoneService.getPersonList();
		System.out.println(personList);
		
		//ds 데이터보내기 --> request attribute에 넣는다
		model.addAttribute("personList", personList);
		
		return"list";
	}
	
	//전화 번호 등록 폼
	@RequestMapping(value="/writeForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String writeForm() {
		System.out.println("PhoneController>writeForm()");
		
		return "writeForm";
	}
	
	//전화번호 등록
	@RequestMapping(value="/write", method= {RequestMethod.GET, RequestMethod.POST})
	public String write(@ModelAttribute PersonVo personVo) {
		System.out.println("PhoneController>write()");
		
		//Service를 통해서 저장한다.
		int count = phoneService.personInsert(personVo);
		
		//리다이렉트
		return "redirect:/list";
	}
		
	//전화번호 삭제
	@RequestMapping(value="/delete", method = {RequestMethod.GET, RequestMethod.POST})
	public String delete(@RequestParam("no") int no) {
		System.out.println("PhoneController>delete()");
		
		//service를 통해서 삭제한다
		phoneService.personDelete(no);
		
		//리다이렉트 시키기
		return "redirect:/list";		
	}
	 
	//전화번호 수정 폼
	@RequestMapping(value="/updateForm", method= {RequestMethod.GET, RequestMethod.POST})
	public String updateForm(@RequestParam("no") int no, Model model) {
		System.out.println("PhoneController>updateForm()");
		
		//Service를 통해서 1명 정보가져오기
		PersonVo personVo = phoneService.getPerson(no);
		
		//ds 테이터보내기 --> request, attrivute
		model.addAttribute("personVo", personVo);
		
		return "updateForm";
	}
	
	
	
	
	//전화번호 수정	  
	@RequestMapping(value="/update", method= {RequestMethod.GET, RequestMethod.POST}) 
	public String update(PersonVo personVo) {
		System.out.println("PhoneController>update()");
		
		//Service를 통해 수정하기
		int count = phoneService.personUpdate(personVo);
				
	    return "redirect:/list"; 
	}
	
	
	
	
}	  
	  
	  
	  
	  

