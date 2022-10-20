package com.encontro.inscricao.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.encontro.inscricao.models.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person,Long> {

}
