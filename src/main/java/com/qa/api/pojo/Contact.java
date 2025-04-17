package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contact {
	String firstName;
	String lastName;
	String birthdate;
	String email;
	String phone;
	String street1;
	String street2;
	String city;
	String stateProvince;
	String postalCode;
	String country;
}
