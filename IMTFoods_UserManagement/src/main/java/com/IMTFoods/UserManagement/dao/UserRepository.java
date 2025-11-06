package com.IMTFoods.UserManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTFoods.UserManagement.model.UserInformation;

public interface UserRepository extends JpaRepository<UserInformation, Long> {

	
}
