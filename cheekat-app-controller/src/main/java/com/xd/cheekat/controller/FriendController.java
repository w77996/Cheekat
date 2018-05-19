package com.xd.cheekat.controller;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import RespCode.RespCode;

import com.xd.cheekat.pojo.Friend;
import com.xd.cheekat.pojo.UserInfo;
import com.xd.cheekat.service.FriendService;
import com.xd.cheekat.service.UserInfoService;
import com.xd.cheekat.util.DateUtil;
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
	
	/**
	 * 添加好友
	 * @param userId
	 * @param friendId
	 * @return
	 */
	@RequestMapping(value = "/open/addFriend")
	public String addFriend(@RequestParam String userId,@RequestParam String friendId){
		String returnStr = JsonUtils.writeJson(0, 0, "参数为空");
		if(StringUtils.isBlank(userId)||StringUtils.isBlank(friendId)){
			return returnStr;
		}

		Friend friend = friendService.findFriends(Long.parseLong(userId),Long.parseLong(friendId));
		if(null != friend){
			return  JsonUtils.writeJson(0, 38, "好友已添加");
		}
		Friend useraddFriend = new Friend();
		useraddFriend.setUserIdFr1(Long.parseLong(userId));
		useraddFriend.setUserIdFr2(Long.parseLong(friendId));
		useraddFriend.setCreateTime(DateUtil.getNowTime());
		useraddFriend.setStatus(2);

		int i = friendService.addFriends(useraddFriend);
		if(0 < i){
			return JsonUtils.writeJson("添加成功", 1);
		}else{
			return JsonUtils.writeJson(0, 39, "参数为空");
		}
	}

}
