package apiServices.sharedModels;


public class Response<T> {

	public boolean hasError = false;
	private int httpStatus;
	public ResponseError error;
	private T model;

	public Response(T model) {
		this.model = model;
	}

	public T getType() {
		return model;
	}

	public void setHasError(boolean hasError) {
		this.hasError = hasError;

	}

	public void setError(ResponseError error) {
		this.error = error;

	}

	public boolean getHasError() {
		return hasError;

	}

	public void setHttpStatus(int httpStatus) {
		this.httpStatus = httpStatus;
	}

	public int getHttpStatus() {
		return httpStatus;

	}

}
