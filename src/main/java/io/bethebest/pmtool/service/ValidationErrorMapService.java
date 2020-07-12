package io.bethebest.pmtool.service;

import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class ValidationErrorMapService {
	public ResponseEntity<?> validationErrorMap(BindingResult result){
		if(result.hasErrors()){
			Map<String, String> errorMap = 
					 result
					.getFieldErrors()
					.stream()
					.collect(Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage));
			
			return new ResponseEntity<Map<String, String>>(errorMap, HttpStatus.BAD_REQUEST);
		}
		return null;
	}
}
