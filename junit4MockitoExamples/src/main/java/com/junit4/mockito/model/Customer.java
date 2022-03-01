package com.junit4.mockito.model;

import java.util.List;

public class Customer {
	private String name;
	private String company;
	private List<Address> addresses;
	private String currentAddressType;

	public String getCurrentAddressType() {
		return currentAddressType;
	}

	public void setCurrentAddressType(String currentAddressType) {
		this.currentAddressType = currentAddressType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

}
