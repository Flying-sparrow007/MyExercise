package com.gk.spring02;

public class Address {
	private String addressName;

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	@Override
	public String toString() {
		return "Address [addressName=" + addressName + "]";
	}
	
}
