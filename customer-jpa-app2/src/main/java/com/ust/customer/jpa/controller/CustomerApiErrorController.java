package com.ust.customer.jpa.controller;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ust.customer.jpa.dto.ErrorResponse;
import com.ust.customer.jpa.exception.CustomerNotFoundException;
import com.ust.customer.jpa.exception.DuplicateFoundException;

@RestControllerAdvice


public class CustomerApiErrorController {
	
	@ResponseStatus(code=HttpStatus.NOT_FOUND)
	@ExceptionHandler(value=CustomerNotFoundException.class)
	public ErrorResponse handleCustomerNotFoundException(CustomerNotFoundException ex,HttpServletRequest request) {
		
		var status=HttpStatus.NOT_FOUND;
		var statusValue=status.value();
		var error=status.getReasonPhrase();
		var timestamp=LocalDateTime.now();
		var path=request.getRequestURI();
		var message=ex.getMessage();
		
		return new ErrorResponse(timestamp,statusValue,error,message,path);
		
	}
	
	@ResponseStatus(code=HttpStatus.BAD_REQUEST)
	@ExceptionHandler(value=DuplicateFoundException.class)
	public ErrorResponse handleDuplicateFoundException(DuplicateFoundException ex,HttpServletRequest request) {
		
		var status=HttpStatus.NOT_FOUND;
		var statusValue=status.value();
		var error=status.getReasonPhrase();
		var timestamp=LocalDateTime.now();
		var path=request.getRequestURI();
		var message=ex.getMessage();
		
		return new ErrorResponse(timestamp,statusValue,error,message,path);
		
	}
	
//	
//	@ResponseStatus(code=HttpStatus.INTERNAL_SERVER_ERROR)
//	@ExceptionHandler(value=handleOtherException.class)
//	public ErrorResponse handleOtherException(handleOtherException ex,HttpServletRequest) {
//		
//		var status=HttpStatus.INTERNAL_SERVER_ERROR;
//		var statusValue=status.value();
//		var error=status.getReasonPhrase();
//		var timestamp=LocalDateTime.now();
//		var path=Request.getRequestURI();
//		var message=ex.getMessage();
//		
//		return new ErrorResponse(timestamp,statusValue,error,message,path);
//		
//	}

}
