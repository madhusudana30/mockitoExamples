package com.junit4.mockito.helper;

import java.util.List;

import com.junit4.mockito.model.Address;
import com.junit4.mockito.model.Customer;

public class CustomerHelper {

	public void setSelectedAddressType(Customer customer, String addressType) {
		customer.setCurrentAddressType(addressType);
	}

	public void setAddressList(Customer customer, List<Address> selectedAddress) {
		customer.setAddresses(selectedAddress);

	}
}
