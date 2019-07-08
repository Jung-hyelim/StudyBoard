package com.jhl.studyboard.exception;

public class DocumentNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 550392463091832948L;

    public DocumentNotFoundException(String mesasge) {
    	super(mesasge);
    }
}
