package com.mandisoft.services.member.utility.exception;

public class EncryptionUtil {
//	public static void main(String[] args) {
//		String encpass = encryptData("test1234");
//		System.out.println(encpass);
//	}

	public static String encryptData(String stringToEcrypt) {
		CryptoLibrary cryptoLibrary = new CryptoLibrary();
		return cryptoLibrary.encrypt(stringToEcrypt);
	}
	
	
	public static String decryptData(String stringToEcrypt) {
		CryptoLibrary cryptoLibrary = new CryptoLibrary();
		return cryptoLibrary.decrypt(stringToEcrypt);
	}

}