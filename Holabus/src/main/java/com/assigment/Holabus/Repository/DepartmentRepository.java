package com.assigment.Holabus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assigment.Holabus.Model.Department;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> getDepartmentById(int id);
}
