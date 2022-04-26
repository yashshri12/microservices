package com.example.userservicedemo.repo;

import com.example.userservicedemo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,Long> {
}
