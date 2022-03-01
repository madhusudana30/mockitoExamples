package com.junit4.mockito;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import com.junit4.mockito.api.CustomerServiceAPI;
import com.junit4.mockito.helper.CustomerHelper;
import com.junit4.mockito.model.Address;
import com.junit4.mockito.model.Customer;

@RunWith(MockitoJUnitRunner.class)
public class CusomterBusinessImplTest {

	@Mock
	private CustomerServiceAPI api;
	
	@Spy
	private CustomerHelper customerHelper;

	@InjectMocks
	private CustomerBusinessImpl businessImpl;

	@Captor
	private ArgumentCaptor<String> addressTypeCaptor;

	@Captor
	private ArgumentCaptor<List<Address>> selectedAddress;
	
	@Captor
	private ArgumentCaptor<Customer> customerObj;

	@Test
	public void testGetCustomerWithSelAddress() {
		when(api.fetchCustomerInfo()).thenReturn(getCustomer());		
		Customer customer = businessImpl.getCustomerWithSelAddress("Home");		
		verify(customerHelper).setSelectedAddressType(customerObj.capture(), addressTypeCaptor.capture());
		verify(customerHelper).setAddressList(customerObj.capture(), selectedAddress.capture());
		assertEquals("Home", addressTypeCaptor.getValue());
		assertEquals("009", selectedAddress.getValue().get(0).getFlat());
		assertEquals(customer.getCurrentAddressType(), "Home");		
		assertEquals(customer.getAddresses().size(), 1);
		assertEquals(customer.getAddresses().get(0).getAddressType(), "Home");
		assertEquals(customer.getAddresses().get(0).getFlat(), "009");
		assertEquals(customer.getAddresses().get(0).getLocation(), "BLR");
		assertEquals(customer.getAddresses().get(0).getStreet(), "HM");
		assertEquals(customer.getAddresses().get(0).isActive(), true);
		assertEquals(customer.getName(),"Madhusudana");
		assertEquals(customer.getCompany(),"MasterCard");
		assertEquals(true, true);
	}
	
	@Test
	public void testGetCustomerWithSelOfficeAddress() {
		when(api.fetchCustomerInfo()).thenReturn(getCustomer());		
		Customer customer = businessImpl.getCustomerWithSelAddress("Office");		
		verify(customerHelper).setSelectedAddressType(customerObj.capture(), addressTypeCaptor.capture());
		verify(customerHelper).setAddressList(customerObj.capture(), selectedAddress.capture());
		assertEquals("Office", addressTypeCaptor.getValue());
		assertEquals("221", selectedAddress.getValue().get(0).getFlat());
		assertEquals(customer.getCurrentAddressType(), "Office");
		assertEquals(customer.getAddresses().size(), 1);
		assertEquals(customer.getAddresses().get(0).getAddressType(), "Office");
		assertEquals(customer.getAddresses().get(0).getFlat(), "221");
		assertEquals(customer.getAddresses().get(0).getLocation(), "BLR");
		assertEquals(customer.getAddresses().get(0).getStreet(), "OfficeAdd");
		assertEquals(customer.getAddresses().get(0).isActive(), true);
		assertEquals(customer.getName(),"Madhusudana");
		assertEquals(customer.getCompany(),"MasterCard");
		assertEquals(true, true);
	}


	private Customer getCustomer() {
		Address homeAddress = createdAddress("009", "HM", "BLR", "Home", true);
		Address officeAddress = createdAddress("221", "OfficeAdd", "BLR", "Office", true);
		Customer customer = new Customer();
		customer.setName("Madhusudana");
		customer.setCompany("MasterCard");
		customer.setCurrentAddressType("Home");
		List<Address> addressList = new ArrayList<>();
		addressList.add(homeAddress);
		addressList.add(officeAddress);
		customer.setAddresses(addressList);
		return customer;
	}

	private Address createdAddress(String flat, String street, String location, String addressType, boolean active) {
		Address address = new Address();
		address.setActive(active);
		address.setAddressType(addressType);
		address.setLocation(location);
		address.setStreet(street);
		address.setFlat(flat);
		return address;
	}

}
