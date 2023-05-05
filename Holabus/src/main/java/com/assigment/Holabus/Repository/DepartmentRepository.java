package com.assigment.Holabus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assigment.Holabus.Model.Department;

public interface DepartmentRepository extends JpaRepository<Department, Integer>{
    
}
