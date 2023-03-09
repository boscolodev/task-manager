package com.gbs.software.backend.repositories;

import com.gbs.software.model.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean findByEmail(String email);
}
