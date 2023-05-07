package com.assigment.Holabus.Service;

import com.assigment.Holabus.Model.Department;
import com.assigment.Holabus.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DepartmentService {
    private final DepartmentRepository _departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        _departmentRepository = departmentRepository;
    }

    public Department getDepartmentById(int id) {
        Optional<Department> department = _departmentRepository.getDepartmentById(id);
        return department.orElse(null);
    }
}
