package checkout.exception;

public  class CKOException extends Exception {
		
	private static final long serialVersionUID = 1L;

	public CKOException(String message) {
		super(message);
	}

	public CKOException(String message, Throwable e) {
		super(message, e);
	}
	public CKOException(String message, String errorCode, Throwable e) {
		super(message, e);
	}
	
	public String getErrorCode(String errorCode){
		return errorCode;
		
	}
}
