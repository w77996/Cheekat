package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import com.xd.cheekat.pojo.Location;

public interface LocationService {

	List<Location> getLocationByLatLng(Map<String, Object> param);
	
	List<Location> getLimitLocationByLatLng(Map<String, Object> param);

}
