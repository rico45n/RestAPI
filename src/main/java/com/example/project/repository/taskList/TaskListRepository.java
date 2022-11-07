package com.example.project.repository.taskList;

import com.example.project.domain.taskList.TaskListDomain;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;


public interface TaskListRepository extends JpaRepository<TaskListDomain , Integer> {

    List<TaskListDomain> update(TaskListDomain obj, Integer taskListId);
}
