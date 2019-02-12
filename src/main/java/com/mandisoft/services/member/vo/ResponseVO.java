package com.mandisoft.services.member.vo;

import org.hibernate.validator.constraints.NotEmpty;

public class ResponseVO {

	/**
	 * Status of Code
	 **/
	@NotEmpty
	private String statusCode;
	/**
	 * message in response
	 **/
	@NotEmpty
	private String message;

	/**
	 * Default constructor
	 */
	public ResponseVO() {

	}

	/**
	 * @param statusCode
	 * @param message
	 */
	public ResponseVO(String statusCode, String message) {
		super();
		this.statusCode = statusCode;
		this.message = message;
	}

	/**
	 * @return status code
	 */
	public String getStatusCode() {
		return statusCode;
	}

	/**
	 * @param statusCode
	 */
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}

	/**
	 * @return message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((statusCode == null) ? 0 : statusCode.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ResponseVO other = (ResponseVO) obj;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (statusCode == null) {
			if (other.statusCode != null)
				return false;
		} else if (!statusCode.equals(other.statusCode))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ResponseVO [statusCode=" + statusCode + ", message=" + message + "]";
	}

}
