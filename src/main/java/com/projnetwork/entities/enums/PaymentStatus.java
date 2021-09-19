package com.projnetwork.entities.enums;

public enum PaymentStatus {
	
	PENDENT(1,"Pendent"),
	SETTLED(2,"Settled"),
	CANCELLED(3,"Cancelled");

	private int code;
	private String status;
	
	PaymentStatus(int i, String string) {
			code=i;
			status=string;
	}

	public Integer getCode() {
		return code;
	}

	public void setCod(int code) {
		this.code = code;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	public static PaymentStatus getPaymentStatus(int i) {
		
		for(PaymentStatus option: PaymentStatus.values()) {
			if(i==option.getCode()) {
				return option;
			}
		}
		throw new  IllegalArgumentException();
		
	}
	
	

}
