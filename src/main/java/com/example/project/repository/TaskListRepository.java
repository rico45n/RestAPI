package com.example.project.repository;

import com.example.project.domain.TaskListDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;


public interface TaskListRepository extends JpaRepository<TaskListDomain, Integer> {
    @Query(value = "select t from TaskListDomain t where t.dateTask between ?1 and CURRENT_DATE and (?2 is null or t.isOpen = ?2) ")
    List<TaskListDomain> interval(LocalDate timeInterval , Boolean bool);


    @Query(value = "select t from TaskListDomain t where ?1  is null or t.isOpen = ?1 ")
    List<TaskListDomain> findAll(Boolean bool);
}
