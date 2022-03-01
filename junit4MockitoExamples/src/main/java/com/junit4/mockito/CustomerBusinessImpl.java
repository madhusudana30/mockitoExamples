package com.junit4.mockito;

import java.util.List;
import java.util.stream.Collectors;

import com.junit4.mockito.api.CustomerServiceAPI;
import com.junit4.mockito.helper.CustomerHelper;
import com.junit4.mockito.model.Address;
import com.junit4.mockito.model.Customer;

public class CustomerBusinessImpl {
	private CustomerServiceAPI customerServiceAPI;
	private CustomerHelper customerHelper;

	public CustomerBusinessImpl(CustomerServiceAPI customerServiceAPI, CustomerHelper customerHelper) {
		this.customerServiceAPI = customerServiceAPI;
		this.customerHelper = customerHelper;
	}

	public Customer getCustomerWithSelAddress(final String addressType) {
		Customer customer = customerServiceAPI.fetchCustomerInfo();
		List<Address> addresses = customer.getAddresses();

		List<Address> selectedAddress = addresses.stream().filter(add -> addressType.equals(add.getAddressType()))
				.collect(Collectors.toList());

		customerHelper.setSelectedAddressType(customer, addressType);
		customerHelper.setAddressList(customer,selectedAddress);
		return customer;
	}
}
