package com.jhl.StudyBoard.exception;

public class DocumentNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 550392463091832948L;

    public DocumentNotFoundException(String mesasge) {
    	super(mesasge);
    }
}
