package com.xd.cheekat.util;

import java.util.HashMap;
import java.util.Map;

public class MapUtil {
	private static double EARTH_RADIUS = 6371;
	
	public static Map<String, double[]> returnLLSquarePoint(double longitude,

	        double latitude, double distance) {

	    Map<String, double[]> squareMap = new HashMap<String, double[]>();

	    // 计算经度弧度,从弧度转换为角度

	    double dLongitude = 2 * (Math.asin(Math.sin(distance

	            / (2 * EARTH_RADIUS))

	            / Math.cos(Math.toRadians(latitude))));

	    dLongitude = Math.toDegrees(dLongitude);

	    // 计算纬度角度

	    double dLatitude = distance / EARTH_RADIUS;

	    dLatitude = Math.toDegrees(dLatitude);

	    // 正方形

	    double[] leftTopPoint = { latitude + dLatitude, longitude - dLongitude };

	    double[] rightTopPoint = { latitude + dLatitude, longitude + dLongitude };

	    double[] leftBottomPoint = { latitude - dLatitude,

	            longitude - dLongitude };

	    double[] rightBottomPoint = { latitude - dLatitude,

	            longitude + dLongitude };

	    squareMap.put("leftTopPoint", leftTopPoint);

	    squareMap.put("rightTopPoint", rightTopPoint);

	    squareMap.put("leftBottomPoint", leftBottomPoint);

	    squareMap.put("rightBottomPoint", rightBottomPoint);

	    return squareMap;
	}

}
