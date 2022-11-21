package br.com.empresa.exception;

@SuppressWarnings("serial")
public class BOException extends Exception{

	public BOException() {
		
	}
	
	public BOException(String message) {
		super(message);
	}
	
	public BOException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public BOException(Throwable cause) {
		super(cause);
	}
}
