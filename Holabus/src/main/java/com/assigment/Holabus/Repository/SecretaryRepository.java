package com.assigment.Holabus.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.assigment.Holabus.Model.Secretary;

public interface SecretaryRepository extends JpaRepository<Secretary, Integer> {
    Secretary findBySecId(int secId);
}
