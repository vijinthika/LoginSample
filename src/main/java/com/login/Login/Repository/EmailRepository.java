package com.login.Login.Repository;

import com.login.Login.entities.Email;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmailRepository extends JpaRepository<Email,Long> {
}
