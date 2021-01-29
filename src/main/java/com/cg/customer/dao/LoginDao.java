package com.cg.customer.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.customer.entity.Login;

@Repository
public interface LoginDao extends JpaRepository<Login, Integer> {

}
