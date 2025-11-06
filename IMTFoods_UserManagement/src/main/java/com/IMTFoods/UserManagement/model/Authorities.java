package com.IMTFoods.UserManagement.model;

import com.IMTFoods.UserManagement.utils.AuthorityRoles;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorites")
public class Authorities {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private int authorityId;
	
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AuthorityRoles roles;

}
