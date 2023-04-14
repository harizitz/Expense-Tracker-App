package com.expensetracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorObject> handleNotFoundException(ResourceNotFoundException ex, WebRequest wr) {
		ErrorObject eObject = new ErrorObject();
		eObject.setStatusCode(HttpStatus.NOT_FOUND.value());
		eObject.setMessage(ex.getMessage());
		eObject.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(eObject, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentException(MethodArgumentTypeMismatchException ex,
			WebRequest wr) {
		ErrorObject eObject = new ErrorObject();
		eObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		eObject.setMessage(ex.getMessage());
		eObject.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(eObject, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(ItemAlreadyExistsException.class)
	public ResponseEntity<ErrorObject> handleItemAlreadyExistsException(ItemAlreadyExistsException ex, WebRequest wr) {
		ErrorObject eObject = new ErrorObject();
		eObject.setStatusCode(HttpStatus.CONFLICT.value());
		eObject.setMessage(ex.getMessage());
		eObject.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(eObject, HttpStatus.CONFLICT);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorObject> handleMethodArgumentException(Exception ex, WebRequest wr) {
		ErrorObject eObject = new ErrorObject();
		eObject.setStatusCode(HttpStatus.BAD_REQUEST.value());
		eObject.setMessage(ex.getMessage());
		eObject.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(eObject, HttpStatus.BAD_REQUEST);
	}

}
