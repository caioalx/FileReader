package javadev.filereader.parsers.exceptions;
public class FieldParsingException extends Exception{
	private static final long serialVersionUID = 1L;
	
	public FieldParsingException() {
	}
	
	public FieldParsingException(String message) {
		super(message);
	}
	
	public FieldParsingException(String message, Throwable cause) {
		super(message, cause);
	}

}
