package apiServices.sharedModels;


public class Response<T> {

	public boolean hasError = false;
	public int httpStatus;
	public ResponseError error;
	public T model;

	public Response(T model) {
		this.model = model;
	}
}
