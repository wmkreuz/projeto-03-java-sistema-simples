package br.com.empresa.exception;

@SuppressWarnings("serial")
public class BOValidationException extends BOException{

	public BOValidationException() {
		super();
	}
	
	public BOValidationException(String message) {
		super(message);
	}
	
	public BOValidationException(Throwable cause) {
		super(cause);
	}
	
}
