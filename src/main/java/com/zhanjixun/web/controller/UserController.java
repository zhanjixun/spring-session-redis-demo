package com.zhanjixun.web.controller;

import com.zhanjixun.web.dto.UserDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author :zhanjixun
 * @date : 2018/12/24 18:36
 */
@Controller
public class UserController {

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String login() {
		return "login";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String login(HttpServletRequest request, @RequestParam("userName") String loginname, @RequestParam("password") String password) {
		UserDTO userDTO = authUser(loginname, password);
		if (userDTO == null) {
			return "login";
		}
		request.getSession().setAttribute("user", userDTO);
		return "redirect:/home";
	}


	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String welcome(HttpSession session) {
		//简单的拦截，实际上应该使用Filter实现
		UserDTO user = (UserDTO) session.getAttribute("user");
		if (user == null) {
			return "login";
		}
		return "home";
	}

	private UserDTO authUser(String loginname, String md5) {
		if (StringUtils.equals(loginname, "admin") && StringUtils.equals(md5, "admin")) {
			UserDTO userDTO = new UserDTO();
			userDTO.setId(1);
			userDTO.setName("admin");
			userDTO.setUserName("admin");
			userDTO.setPassword("admin");
			return userDTO;
		}
		return null;
	}

}
