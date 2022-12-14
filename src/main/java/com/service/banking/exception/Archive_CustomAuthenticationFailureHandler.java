package com.service.banking.exception;
//package com.service.banking.exception;
//
//import java.io.IOException;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AuthenticationFailureHandler;
//
//import com.fasterxml.jackson.databind.ObjectMapper;
//
//public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {
//	
//	// JSON serialiser instance
//	private ObjectMapper objectMapper = new ObjectMapper();
//
//	@Override
//	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
//			AuthenticationException exception) throws IOException, ServletException {
//
//		HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
//		
//		Map<String, Object> data = new HashMap<>();
//		
//		data.put("timestamp", new Date());
//		data.put("code", httpStatus.value());
//		data.put("Status", httpStatus.name());
//		data.put("message", exception.getLocalizedMessage());
//		response.setStatus(httpStatus.value());
//		
//		// serialising the response body in JSON
//		response.getOutputStream().println(objectMapper.writeValueAsString(data));
//		System.out.println("---------------------- AUTHENTICATION TEST-------------------------");
//		
//	}
//}
