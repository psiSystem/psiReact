package com.br.psi.model;

public class ObjectError {

	private String message;
	private String field;
	private Object parameter;

	public ObjectError(String message, String field, Object parameter) {
		this.message = message;
		this.field = field;
		this.parameter = parameter;
	}

	public ObjectError() {
		// TODO Auto-generated constructor stub
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getParameter() {
		return parameter;
	}

	public void setParameter(Object parameter) {
		this.parameter = parameter;
	}

}