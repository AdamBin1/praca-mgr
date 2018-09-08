package com.springmvc.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {
	
	@Autowired
	JsonService jsonService;

	public ResponseEntity<String> createResponseEntity(List<String> errors) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		
		if(errors == null) {
			return new ResponseEntity<String>(jsonService.createSuccessMessage(), responseHeaders, HttpStatus.OK);
		}
		return new ResponseEntity<String>(jsonService.createJsonErrorsMessage(errors), responseHeaders, HttpStatus.UNPROCESSABLE_ENTITY);
	}
}
