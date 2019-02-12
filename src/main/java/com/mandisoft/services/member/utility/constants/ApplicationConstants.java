package com.mandisoft.services.member.utility.constants;

import org.springframework.context.annotation.PropertySource;


@PropertySource(value = "messages_en.properties")
public class ApplicationConstants {
	public static final String EMAIL_ID = "email";

	
	 /**
     * Validity message in case of success.
     */
    
   public static final String SUCCESS = "success";
   /**
    * Validity message in case of error.
    */
   public static final String ERROR = "error";

   /**
    * key for model map representing name of user.
    */
   public static final String USER_NAME = "firstName";

   /**
    * key for model map representing error message.
    */
   public static final String ERROR_MESSAGE = "errorMessage";

   /*
    * messages.properties file key names start
    */
   public static final String SUCCESS_MESSAGE = "successMessage";
   
   public static final String ALREADY_REGISTERED = "user.already.registered";
   
   public static final String COMPANY_ALREADY_REGISTERED = "The company that you are trying to add already exists. Please choose a differnt name";
   
   public static final String ROLE_ALREADY_REGISTERED = "role.already.registered";
   
   public static final String TOKEN_ALREADY_VERIFIED = "token.already.verified"; 
   
   public static final String INVALID_TOKEN = "Invalid token"; 
   
   public static final String TOKEN_EXPIRED = "token.expired";
   
   public static final String EMAIL_SENT = "email.sent";
   
   public static final String SUCCESSFUL_REGISTRATION = "successful.registration";
   /*
    * messages.properties file key names end
    */
   
   public static final String RECAPTCHA_VERIFY_URL = "https://www.google.com/recaptcha/api/siteverify";
   
   public static final String RECAPTCHA_SECRET_KEY = "6Lc5QxoTAAAAAHWqj-DAHjPJ6J03WLKOPfqM66Cv";
   
   public static final String END_USER = "End User";
   
   public static final String END_USER_CODE = "EU";
   
   public static final String SHS_ADMIN = "SHS Admin";
   
   public static final String INVALID_PASSWORD = "Password incorrect, please try again.";
   
   public static final String DOWNLOAD_USER_IMAGE_ERR = "Failed to download Profile Picture";
   
   public static final String COMPANY_NOT_FOUND = "Company does not exist";
   
   public static final String NO_USERS_FOR_COMPANY =  "No users found for your company";
   
   public static final String DOWNLOAD_COMP_LOGO_ERR = "Failed to download company logo";
   
   public static final String MAIL_INSTANCE_ERR = "Failure creating mail instance";
   
   public static final String MAIL_NOT_SENT = "Failure sending mail";
   
   public static final String INVALID_CAPTCHA = "Captcha is invalid. Please try with other captcha.";
   
   public static final String INVALID_USER_ID = "Username is invalid, please try again.";
   
   public static final String ACCOUNT_LOCKED = "Your account has been locked. Please contact system administrator.";
   
   public static final String MISSING_DATA = "Missing parameters";
   
   public static final String PASSWORD_NOT_ALLOWED = "This matches previously used password. Choose a different one.";
   
   public static final String SECURITY_LIMIT_EXCEEDED = "Wrong security answer attempts limit exceeded.";
   
   public static final String ASC = "ASC";
   
   public static final String GLOBAL_ADMIN_CODE = "GA";
   
   public static final String SHS_ADMIN_CODE = "SHSA";
   
   public static final String ENTITY_ADMIN_CODE = "EA";
   
   public static final String COMPANY_DEACTIVED_SUCCESSFULLY = "Company deactivated  or activated successfully.";
   
   public static final String COMPANY_IS_DEACTIVE = "Company is deactivat.";
   
   public static final String USER_COMPANY_DEACTIVATE = "User cant login/update due to company is inactive.";	
   
   public static final String USER_DEACTIVED_OR_ACTIVATED_SUCCESSFULLY = "User deactivated  or activated successfully.";
   
   public static final String USER_NOT_FOUND = "User not found.";
   
   public static final String QUESTION_NOT_FOUND = "Question not Found";
   
   public static final String SECTION_NOT_FOUND = "Section not Found";
   
   public static final String QUESTIONNAIRE_NOT_FOUND = "Questionnaire not Found";

   
   public static final String TOKEN_NOT_VERIFIED = "Token not verified.";
   
   public static final String COMPANY_NAME_ALREAY_EXISTS = "Company name already exists with different id.";
   
   public static final String USER_ROLE_NAME_ALREADY_EXISTS = "User role name already exists.";
   
   public static final String FILE_UPLOAD_EXCEPTION = "File upload exception";
   
   public static final String PASSWORD_CHANGED_SUCCESSFULLY = "Password changed successfully";
   
   public static final String USER_ROLE_CHANGED_TO_SHSA = "User role successfully updated to SHS Admin";

   public static final String USER_ROLE_NOT_CHANGED_TO_SHSA = "User cannot be made SHS Admin";   
   
   public static final String SYMPHONY_LOGO = "symphony_logo.jpg";
   
   public static final String DEFAULT_COMPANY_LOGO = "default_company_logo.png";
   
   public static final String IMAGES = "images";
   
   public static final String NO_RECORDS_FOUND = "No records found";
   
   public static final String PROFILE_IMAGE = "profile.png";
   
   public static final String PASSWORD_RESET_MAIL_SENT_MESSAGE = "Password reset mail has been send to ";
   
   public static final String FTP_LOGIN_ISSUE = "FTP login failed";
   
   public static final String FTP_CONNECT_ISSUE = "FTP connect failed";
   
   public static final String FTP_UPLOAD_ISSUE = "FTP upload file failed";
   
   public static final String FTP_FETCH_ISSUE = "FTP fetch list failed";
   
   public static final String FTP_DOWNLOAD_ISSUE = "FTP download failed";
   
   public static final String FTP_FILE_EXISTS = "FTP file name exists.";
   
   public static final String PENDING_STATUS = "pending";
   
   public static final String APPROVED_STATUS = "approved";
   
   public static final String SINGLE_SELECT = "SINGLESELECT_DROPDOWN";
   
   public static final String EQUAL_SIGN = "=";
   
   public static final String PERCENTAGE_SIGN = "%";
   
   public static final String AMPERSAND_SIGN = "&";
   
   public static final String BOI_API_URL = "boi.url";
   
   public static final String BOI_API_URL_CREDENTIALS = "boi.credential";
   
   public static final String INSTANCE = "Instance";
   
   public static final String OVERRIDES = "Overrides";
   
   public static final String ERRORS = "Errors";
   
   public static final String REMOVED_STATUS = "removed";


   public static final String TOKEN_INVALID = "Invalid token";

   public static final String REQUEST_INSTANCE_ERR = "Failure creating request";

   public static final String UNAUTHORIZED = "Unauthorized";

   public static final String FAILURE = "Data already present in Database";

   public static final String ID_NOT_FOUND = "User is not present ";

   public static final String FAILED_FETCHING = "purchase Order is Closed ";

   public static final String FAILED_UPDATE = "Data is not updated ";

   public static final String FAILURE_CANCEL = "Your Purchase Order is not Cancelled";
   
   public static final String FAILED_FATCHING_LIST = "List can not be Fetched";
   
   public static final String UNEXPECTED_ERROR = "Unexpected error in conversion";
   
   public static final String REQUEST_NOT_CREATED = "Request Cannot be created";

   public static final String FILE_NOT_FOUND = "File not found";
   
   public static final String FILE_FORMAT_NOT_SUPPORTED = "File formate not supported";
   
   public static final String QUESTION_LIST_EMPTY = "Question list is empty";
   
   public static final String SECTION_ALREADY_EXISTS = "Section Already exists";
   
   public static final String QUESTIONNAIRE_ALREADY_EXISTS = "Questionnaire already exists";
   
   public static final String SURVEY_CANNOT_BE_EMPTY = "Survey cannot be empty";
   
   public static final String QUESTIONNAIRE_CANNOT_BE_EMPTY = "Questionnaire cannot be empty";
   
   public static final String QUESTION_CANNOT_BE_EMPTY = "Question cannot be empty";
   
   public static final String SECTION_CANNOT_BE_EMPTY = "Section cannot be empty";
   
   public static final String VALUES_CANNOT_BE_EMPTY = "Values cannot be empty";
   
   public static final String EXCEL_ALREADY_EXISTS = "Excel Already Exists";
   
   public static final String REQUEST_CANNOT_BE_EMPTY = "Request Cannot Be Empty";
   
   public static final String CRITERIA_DOSENT_EXISTS = "Criteria Dosent Exists";
}
