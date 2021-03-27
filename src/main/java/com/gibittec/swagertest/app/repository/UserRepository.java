package com.gibittec.swagertest.app.repository;

import com.gibittec.swagertest.app.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
