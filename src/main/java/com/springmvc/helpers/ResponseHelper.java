package com.springmvc.helpers;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.springmvc.dao.service.JsonService;

public class ResponseHelper {

	public static ResponseEntity<String> createResponseEntity(List<String> errors) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		if(errors == null) {
			return new ResponseEntity<String>(JsonService.createSuccessMessage(), responseHeaders, HttpStatus.OK);
		}
		return new ResponseEntity<String>(JsonService.createJsonErrorsMessage(errors), responseHeaders, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
