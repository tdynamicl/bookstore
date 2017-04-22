package cn.lost4found.common;

import java.util.Date;
import java.util.UUID;

public class Util {

	public static String generateUUID(){
		return UUID.randomUUID().toString().replaceAll("-", "");
	}
	
	public static Date nowDate(){
		return new Date(System.currentTimeMillis());
	}
	
}
