package com.xd.cheekat.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import RespCode.RespCode;

import com.xd.cheekat.common.Constant;
import com.xd.cheekat.pojo.Location;
import com.xd.cheekat.pojo.UserInfo;
import com.xd.cheekat.service.LocationService;
import com.xd.cheekat.service.UserInfoService;
import com.xd.cheekat.util.JsonUtils;
import com.xd.cheekat.util.MapUtil;

@RestController
public class NearbyController {

	@Autowired
	UserInfoService userInfoService;

	@Autowired
	LocationService locationService;

	@RequestMapping(value = "/getNearBy")
	public String getNearBy(@RequestParam String userId,
			@RequestParam String lat, @RequestParam String lng,
			@RequestParam String distance) {
		UserInfo user = userInfoService.selectByPrimaryKey(Long
				.parseLong(userId));
		String result = JsonUtils.writeJson(0,
				RespCode.ERROR_PARAM_EMTY.getCode(),
				RespCode.ERROR_PARAM_EMTY.getMsg());
		;
		if (null != user) {
			if (Constant.USER_INVISIBLE == user.getInvisible()) {// 如果隐身了
				result = JsonUtils.writeJson(1, RespCode.STATU_OK.getMsg(),
						null, "object");
			} else {
				double latitude = Double.parseDouble(lat);
				double longitude = Double.parseDouble(lng);
				double dis = Double.parseDouble(distance);
				Map<String, double[]> map = MapUtil.returnLLSquarePoint(
						longitude, latitude, dis);
				Map<String, Object> param = new HashMap<String, Object>();
				param.put("lat", lat);
				param.put("lng", lng);
				param.put("userId", userId);
				param.put("rightBottomPoint0", map.get("rightBottomPoint")[0]);
				param.put("rightBottomPoint1", map.get("rightBottomPoint")[1]);
				param.put("leftTopPoint0", map.get("leftTopPoint")[0]);
				param.put("leftTopPoint1", map.get("leftTopPoint")[1]);
				List<Location> list = locationService
						.getLocationByLatLng(param);
				result = JsonUtils.writeJson(1, RespCode.STATU_OK.getMsg(),
						list, "object");
			}
		} else {
			result = JsonUtils.writeJson(0,
					RespCode.ERROR_USER_NOT_EXIST.getCode(),
					RespCode.ERROR_USER_NOT_EXIST.getMsg());
		}
		return result;
	}

	@RequestMapping(value = "/getAllNearBy")
	public String getAllNearBy(@RequestParam String userId,
			@RequestParam String lat, @RequestParam String lng,
			@RequestParam String start, @RequestParam String count) {
		UserInfo user = userInfoService.selectByPrimaryKey(Long
				.parseLong(userId));
		if (null == user) {
			return JsonUtils.writeJson(0,
					RespCode.ERROR_USER_NOT_EXIST.getCode(),
					RespCode.ERROR_USER_NOT_EXIST.getMsg());
		}
		if (Constant.USER_INVISIBLE == user.getInvisible()) {
			return JsonUtils.writeJson(1, RespCode.STATU_OK.getMsg(), null,
					"object");
		}
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("lat", lat);
		param.put("lng", lng);
		param.put("userId", userId);
		param.put("start", Integer.parseInt(start));
		param.put("count",Integer.parseInt(count));
		List<Location> list = locationService
				.getLimitLocationByLatLng(param);
		return JsonUtils.writeJson(1, RespCode.STATU_OK.getMsg(), list,
				"object");
	}

}
