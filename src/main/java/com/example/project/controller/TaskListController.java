package com.example.project.controller;


import com.example.project.domain.TaskListDomain;
import com.example.project.repository.TaskListRepository;
import com.example.project.repository.TimeInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/TaskList/")
public class TaskListController {
    @Autowired
    private TaskListRepository taskListRepository;

    //Список всех задач
    @RequestMapping(value = "/AllTaskList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskListDomain> findAll() {
        return taskListRepository.findAll();
    }

    //Добавить задачу
    @RequestMapping(value = "/AddTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody TaskListDomain obj) {
        taskListRepository.save(obj);
    }

    //Найти задачу по ID
    @RequestMapping(value = "/SearchTask/{taskListId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<TaskListDomain> read(@PathVariable("taskListId") Integer id) {
        return taskListRepository.findById(id);
    }

    //Обновить задачу
    @RequestMapping(value = "/UpdateTask/{taskListId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody TaskListDomain obj,
                       @PathVariable("taskListId") Integer taskListId) {
        obj.setTaskListId(taskListId);
        taskListRepository.save(obj);
    }

    //Удалить задачу
    @RequestMapping(value = "/DeleteTask/{taskListId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("taskListId") Integer obj) {
        taskListRepository.deleteById(obj);

    }

    //Просмотр списка задач на сегодня/неделю/месяц с фильтрацией по выполнению
    @RequestMapping(value = "/TaskInterval/{TimeInterval}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskListDomain> TaskInterval(@PathVariable("TimeInterval") TimeInterval obj) {

        LocalDate date = null;
        if (TimeInterval.DAY.equals(obj)) {
            date = LocalDate.now();
        } else if (TimeInterval.WEEK.equals(obj)) {
            date = LocalDate.now().minusDays(7);
        } else if (TimeInterval.MONTH.equals(obj)) {
            date = LocalDate.now().minusMonths(1);
        }
        if (date == null){
            return taskListRepository.findAll();
        }else {
            return taskListRepository.intervalSort(date);
        }


    }

}
