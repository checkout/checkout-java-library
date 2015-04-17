package exception;

public class ValidationFailException extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;
	
	
	public ValidationFailException(String message){
		
		super(message, null);
		
	}
	
	
	
}
