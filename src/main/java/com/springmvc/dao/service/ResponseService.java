package com.springmvc.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springmvc.data.model.IdSecPair;

@Service
public class ResponseService {
	
	@Autowired
	JsonService jsonService;

	public ResponseEntity<String> createErrorResponseEntity(List<String> errors) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<String>(jsonService.createJsonErrorsMessage(errors), responseHeaders, HttpStatus.UNPROCESSABLE_ENTITY);
	}
	
	public ResponseEntity<String> createSuccessResponseEntity(List<IdSecPair> idSecPairs) {
		
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);

		return new ResponseEntity<String>(jsonService.createJsonSuccessMessage(idSecPairs), responseHeaders, HttpStatus.OK);
	}
}
