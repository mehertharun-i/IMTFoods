package com.IMTFoods.UserManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.IMTFoods.UserManagement.model.UserAddressInformation;

@Repository
public interface UserAddressInformationRepository extends JpaRepository<UserAddressInformation, Long>{

}
