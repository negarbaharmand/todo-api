package com.baharmand.todoapi.repository;

import com.baharmand.todoapi.entity.Role;
import com.baharmand.todoapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    @Modifying
    @Query
    void updateExpiredByEmail(@Param("email") String email, @Param("status") boolean status);

    @Modifying
    @Query("update User u set u.password = :password where u.email = :email")
    void updatePasswordByEmail(@Param("email") String email, @Param("password") String newPassword);
}
