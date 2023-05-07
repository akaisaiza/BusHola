package com.assigment.Holabus.Repository;

import com.assigment.Holabus.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query(value = "select r from Role r where r.id = 2")
    Optional<Role> getUserRole();
}
