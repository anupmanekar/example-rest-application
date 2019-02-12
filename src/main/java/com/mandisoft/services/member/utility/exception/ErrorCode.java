package com.mandisoft.services.member.utility.exception;

import java.util.NoSuchElementException;

public enum ErrorCode {
	
	BASE_DB_ERROR(999),
	USER_ALREADY_EXIST(1000),
	COMPANY_ALREADY_EXIST(1001),
	INVALID_TOKEN(1002),
	EXPIRED_TOKEN(1003),
	TOKEN_ALREADY_VERIFIED(1004),
	INVALID_CAPTCHA(1005),
	INVALID_PASSWORD(1006),
    FILE_OPERATION_ERROR(1007),
    UNEXPECTED_ERROR(1008),
    INSUFFICIENT_PARAMETERS(1009),
    USER_NOT_FOUND(1010),
	INVALID_USER_ID(1011),
	ACCOUNT_LOCKED(1012),
	COMPANY_NOT_FOUND(1013),
	PASSWORD_NOT_ALLOWED(1014),
	SECURITY_ATTEMPT_LIMIT_EXCEEDED(1015),
	USER_APPLICATION_NOT_FOUND(1016),
	ROLE_NAME_NOT_FOUND(1017),
	COMPANY_INACTIVE(1018),
	USER_COMPANY_DEACTIVE(1019),
	USER_INEACTIVE(1020),
	TOKEN_NOT_VERIFIED(1021),
	COMPANY_NAME_ALREAY_EXISTS(1022),
	USER_ROLE_NAME_ALREADY_EXISTS(1023),
	FTP_LOGIN_ISSUE(1024),
	FTP_CONNECT_ISSUE(1025),
	FTP_UPLOAD_ISSUE(1026),
	REQUEST_NOT_CREATED(1027),
	FTP_DOWNLOAD_ISSUE(1028),
	FTP_FILE_NAME_EXISTS(1029),
	OBJECT_MAPPER_ERROR(1030),
	USER_DIMENSION_NOT_FOUND(1031),
	NO_RECORDS_FOUND(1032),
	NO_FILTER_CRITERIA_FOUND(1033),
	NO_APPROVED_RECORDS_FOUND(1034),
	INTERNAL_SERVER_ERROR(500), 
	PASSWORD_NOT_MATCH(2001),
	USERNAME_NOT_FOUND(1034),
	QUESTION_NOT_FOUND(1034),
	SECTION_NOT_FOUND(1034),

	QUESTIONNAIRE_NOT_FOUND(1034),
	FILE_NOT_FOUND(1035),
	FILE_FORMAT_NOT_SUPPORTED(1036),
	ACCESS_DENIED(401);
	  
	

    private final int codeId;

    private ErrorCode(final int codeId) {
        this.codeId = codeId;
    }

    public int getCodeId() {
        return this.codeId;
    }

    /**
     * Converts an int value into an ErrorCode
     * 
     * @param errorCode
     * @return {@link ErrorCode}
     */
    public static ErrorCode getExceptionCode(final int errorCode) {

        ErrorCode eErrorCode = null;
        for (final ErrorCode status : ErrorCode.values()) {
            if (status.getCodeId() == errorCode) {
                eErrorCode = status;
                break;
            }
        }
        if (null == eErrorCode) {
            throw new NoSuchElementException("The received code " + errorCode + " is not valid !!!");
        } else {
            return eErrorCode;
        }

    }

}
