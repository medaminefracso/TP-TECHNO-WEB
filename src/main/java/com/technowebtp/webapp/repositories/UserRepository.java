package com.technowebtp.webapp.repositories;

import com.technowebtp.webapp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
