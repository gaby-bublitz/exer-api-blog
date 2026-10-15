package com.bublitz.api_blog.exception;

public class PostNotFoundException extends ResourceNotFoundException {
	private static final long serialVersionUID = 1L;
	
	public PostNotFoundException(String message) {
		super(message);
	}
}
