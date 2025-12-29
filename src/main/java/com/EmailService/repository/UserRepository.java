package com.EmailService.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.EmailService.entities.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {


}
