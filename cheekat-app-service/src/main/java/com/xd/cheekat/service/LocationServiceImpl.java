package com.xd.cheekat.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xd.cheekat.dao.LocationDao;
import com.xd.cheekat.pojo.Location;
@Service
public class LocationServiceImpl implements LocationService{

	@Autowired
	private LocationDao locationDao;
	
	@Override
	public List<Location> getLocationByLatLng(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return locationDao.getLocationByLatLng(param);
	}

	@Override
	public List<Location> getLimitLocationByLatLng(Map<String, Object> param) {
		// TODO Auto-generated method stub
		return locationDao.getLimitLocationByLatLng(param);
	}

}
