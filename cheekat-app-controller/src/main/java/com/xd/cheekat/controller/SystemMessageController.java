package com.xd.cheekat.controller;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xd.cheekat.pojo.Message;
import com.xd.cheekat.pojo.UserInfo;
import com.xd.cheekat.service.MessageService;
import com.xd.cheekat.service.UserInfoService;
import com.xd.cheekat.util.JsonUtils;
@RestController
public class SystemMessageController {

	@Autowired
	private UserInfoService userService;
	
	@Autowired
	private MessageService messageService;
	
	/**
	 * 查询官方公告
	 * 
	 * @param request
	 * @param query
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/open/getNotice")
	public String getAllMission(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(!StringUtils.isBlank(userId)) {
			UserInfo user = userService.selectByPrimaryKey(Long.parseLong(userId));
			if(user != null) {
				Message message = messageService.getLastMessage(0);
				returnStr = JsonUtils.writeJson(1, "获取成功", message, "object");
			}else {
				returnStr = JsonUtils.writeJson(0, 4, "用户不存在");
			}
		}
		return returnStr;
	}
	
	@RequestMapping(value = "/open/getSystemMessage")
	public String getSystemMessage(HttpServletRequest request) {
		String userId = request.getParameter("userId");
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(!StringUtils.isBlank(userId)) {
			UserInfo user = userService.selectByPrimaryKey(Long.parseLong(userId));
			if(user != null) {
				Message message = messageService.getLastMessage(1);
				returnStr = JsonUtils.writeJson(1, "获取成功", message, "object");
			}else {
				returnStr = JsonUtils.writeJson(0, 4, "用户不存在");
			}
		}
		return returnStr;
	}
}
