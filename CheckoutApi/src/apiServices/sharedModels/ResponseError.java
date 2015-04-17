package apiServices.sharedModels;

public class ResponseError {

	public String errorCode;
	public String message;
	public String errors;
	public String eventId;

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorMessage(String errorMessage) {
		this.message = errorMessage;
	}

	public String getErrorMessage() {
		return message;
	}

}
