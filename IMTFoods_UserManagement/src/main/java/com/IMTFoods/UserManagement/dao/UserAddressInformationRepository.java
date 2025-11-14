package com.IMTFoods.UserManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTFoods.UserManagement.model.UserAddressInformation;

public interface UserAddressInformationRepository extends JpaRepository<UserAddressInformation, Long>{

}
