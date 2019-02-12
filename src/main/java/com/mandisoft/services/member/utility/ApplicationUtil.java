package com.mandisoft.services.member.utility;

public class ApplicationUtil {
	
	public static boolean isStringNullOrEmpty (String value) {
		boolean isStringNullOrEmpty =true;
		if (value != null && value.trim().length() > 0) {
			isStringNullOrEmpty = false;
		}
		return isStringNullOrEmpty;
	}

}
