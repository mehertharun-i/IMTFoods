package com.IMTFoods.UserManagement.dto;

import com.IMTFoods.UserManagement.utils.AuthorityRoles;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthoritiesRequestDto {

	private AuthorityRoles roleRequestDto;
	
}
