package br.com.alloy.springboot.automationapi.model;

public class TypeRequest {
	
	private String code;
	private TypeMode typeMode;
	private boolean sendEnter;
	
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	
	public TypeMode getTypeMode() {
		return typeMode;
	}

	/**
	 * @return the sendEnter
	 */
	public boolean isSendEnter() {
		return sendEnter;
	}

}
