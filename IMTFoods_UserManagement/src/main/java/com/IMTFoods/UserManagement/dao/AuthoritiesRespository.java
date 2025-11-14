package com.IMTFoods.UserManagement.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.IMTFoods.UserManagement.model.Authorities;

public interface AuthoritiesRespository extends JpaRepository<Authorities, Integer> {

}
