package com.login.Login.Repository;

import com.login.Login.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users,Long> {
    public boolean existsByName(String name);
    public boolean existsByEmail(String email);
    public boolean findByEmail(String email);

    public Users getByEmail(String email);
//    public Users getByName(String name);
//    public boolean existsByEmailAndIdNot(String email,Long id);



}
