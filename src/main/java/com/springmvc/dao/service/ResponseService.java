package com.springmvc.dao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.springmvc.model.IdPropIdType;
import com.springmvc.model.IdSecPair;

@Service
public class ResponseService {
	
	@Autowired
	private JsonService jsonService;

	public ResponseEntity<String> createErrorResponseEntity(List<String> errors) {
		
		return new ResponseEntity<String>(jsonService.createJsonErrorsMessage(errors), getApplicationJsonHeaders(), HttpStatus.UNPROCESSABLE_ENTITY);
	}

	public ResponseEntity<String> createSuccessResponseEntityForIdSecPairs(List<IdSecPair> idSecPairs) {
		
		return new ResponseEntity<String>(jsonService.createJsonSuccessMessageForIdSecPairs(idSecPairs), getApplicationJsonHeaders(), HttpStatus.OK);
	}
	
	public ResponseEntity<String> createSuccessResponseEntityForIdPropIdPairs(List<IdPropIdType> idPropIdTypes) {

		return new ResponseEntity<String>(jsonService.createJsonSuccessMessageForIdPropIdType(idPropIdTypes), getApplicationJsonHeaders(), HttpStatus.OK);
	}
	
	public ResponseEntity<String> createSuccessResponseEntity() {

		return new ResponseEntity<String>(jsonService.createJsonSuccessMessage(), getApplicationJsonHeaders(), HttpStatus.OK);
	}
	
	private HttpHeaders getApplicationJsonHeaders() {
		HttpHeaders responseHeaders = new HttpHeaders();
		responseHeaders.setContentType(MediaType.APPLICATION_JSON);
		return responseHeaders;
	}
}
