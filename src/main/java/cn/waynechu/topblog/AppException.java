package cn.waynechu.topblog;

public class AppException extends RuntimeException {
    private static final long serialVersionUID = 1950676851620859551L;
    
    private int errorCode;
	private String errorMessage;
	private Throwable errorCause;

	public AppException(int errorCode, String errorMessage) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public AppException(int errorCode, String errorMessage, Throwable errorCause) {
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
		this.errorCause = errorCause;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public Throwable getErrorCause() {
		return errorCause;
	}

	public String getErrorMessage() {
		return this.errorMessage;
	}

}
