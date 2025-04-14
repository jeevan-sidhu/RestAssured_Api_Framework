package com.qa.api.pojo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class User {
	String name;
	String email;
	String gender;
	String status;
}
