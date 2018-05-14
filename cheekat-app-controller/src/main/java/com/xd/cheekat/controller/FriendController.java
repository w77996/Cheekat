package com.xd.cheekat.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import RespCode.RespCode;

import com.xd.cheekat.pojo.UserInfo;
import com.xd.cheekat.service.FriendService;
import com.xd.cheekat.service.UserInfoService;
import com.xd.cheekat.util.JsonUtils;

@RestController
public class FriendController {
	
	@Autowired
	private UserInfoService userInfoService;
	
	@Autowired
	private FriendService friendService;
	
	@RequestMapping(value="/open/getFriend")
	public String getFriend(@RequestParam String userId){
		UserInfo userInfo = userInfoService.selectByPrimaryKey(Long.parseLong(userId));
		if(null == userInfo){
			return JsonUtils.writeJson(0, RespCode.ERROR_USER_NOT_EXIST.getCode(), RespCode.ERROR_USER_NOT_EXIST.getMsg());
		}
		List<Map<String,Object>> list = friendService.getUserFriends(Long.parseLong(userId));
		return  JsonUtils.writeJson(1, RespCode.STATU_OK.getMsg(), list, "object");
	}

}
