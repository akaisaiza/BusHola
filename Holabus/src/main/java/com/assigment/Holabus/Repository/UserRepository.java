package com.assigment.Holabus.Repository;

import com.assigment.Holabus.Model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Iterator;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(value = "SELECT u from User u inner join u.role where u.email = :email")
    Optional<User> findByEmail(@Param(value = "email") String email);

    // change password
//    User findByEmailchangepassUser(String email);

    Optional<User> findById(@NotNull int id);

    @Query(value = "select u from User u inner join u.department inner join u.role where u.role.id = :role")
    Iterable<User> findAllByRoleId(@Param(value = "role") int roleId);
}
