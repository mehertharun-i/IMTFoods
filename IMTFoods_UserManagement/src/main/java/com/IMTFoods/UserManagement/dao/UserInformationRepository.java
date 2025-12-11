package com.IMTFoods.UserManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IMTFoods.UserManagement.model.UserInformation;

@Repository
public interface UserInformationRepository extends JpaRepository<UserInformation, Long> {

	
}
