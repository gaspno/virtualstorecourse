package com.projnetwork.entities.enums;

public enum TypeClient {
	
	PESSOAFISICA(1,"CPF"),
	PESSOAJURIDICA(2,"CNPJ");
	
	private int code;
	private String type;

	private TypeClient(int i, String string) {
		code=i;
		type=string;
	}

	public int getCode() {
		return code;
	}

	public String getType() {
		return type;
	}
	
	public static TypeClient getTypeClient(int i) {
		for(TypeClient option: TypeClient.values()) {
			if(i==option.getCode()) {
				return option;
			}
		}
		throw new  IllegalArgumentException();
		
	}
	

}
