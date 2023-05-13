package com.test.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Person;

public interface PersonRepo extends JpaRepository<Person, Integer> {

}
