package com.axb.proxy.exception;


import com.axb.proxy.resp.CodeMsg;

public class ServerException extends RuntimeException {
	
	private static final long serialVersionUID = 596185235739708258L;
	
	final CodeMsg errorCode;

	public ServerException(CodeMsg errorCode) {
		super();
		this.errorCode = errorCode;
	}

	public CodeMsg getErrorCode() {
		return errorCode;
	}

}

