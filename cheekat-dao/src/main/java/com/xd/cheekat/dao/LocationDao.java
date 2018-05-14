package com.xd.cheekat.dao;

import java.util.List;
import java.util.Map;

import com.xd.cheekat.pojo.Location;

public interface LocationDao {

	int deleteByPrimaryKey(Long locId);

	int insert(Location record);

	int insertSelective(Location record);

	Location selectByPrimaryKey(Long locId);

	int updateByPrimaryKeySelective(Location record);

	int updateByPrimaryKey(Location record);
	
	int updateLocation(Location location);
	
	List<Location> getLocationByLatLng(Map<String, Object> param);
	
	List<Location> getLimitLocationByLatLng(Map<String, Object> param);
}
