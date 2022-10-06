package br.com.alloy.springboot.automationapi.model;

public class TypeRequest {
	
	private String code;
	private boolean sendEnter;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	/**
	 * @return the sendEnter
	 */
	public boolean isSendEnter() {
		return sendEnter;
	}

}
