package com.example.project.repository;

import com.example.project.domain.TaskListDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface TaskListRepository extends JpaRepository<TaskListDomain, Integer> {
    @Query(value = "select t from TaskListDomain t where t.dateTask between ?1 and CURRENT_DATE and t.isOpen = true")
    List<TaskListDomain> intervalSort(LocalDate timeInterval);
}
