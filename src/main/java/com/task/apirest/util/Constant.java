package com.task.apirest.util;

public class Constant {
	
	public static final class ERROR_CODE	{
		public final static String DEVOLVIO_RESULTADO_ERROR	= "A001";
		public final static String SERVICE_NOT_AVAIBLE			= "A002";
		public final static String BAD_REQUEST					= "A003"; 
		public final static String NO_CONTENT		= "A004";
		public final static String USER_NOT_EXIST					= "A005";		
		public final static String REQUIRED_FIELDS						= "A007"; 
		
		
	}	
	
	public static final class ERROR_MESSAGE	{
		public final static String DEVOLVIO_RESULTADO_ERROR		= "A001";
		public final static String SERVICE_NOT_AVAIBLE			= "technicalError";
		public final static String BAD_REQUEST					= "bad request";  
		public final static String NO_CONTENT		= "No results";
		public final static String USER_NOT_EXIST			= "The user is not exist in the system";
		public final static String REQUIRED_FIELDS			= "The field is mandatory: {0}";
		public final static String BAD_REQUEST_FILE					= "The estrucure of the file is not correct"; 
		
		
	}	

}
