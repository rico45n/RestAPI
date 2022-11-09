package com.example.project.controller;


import com.example.project.domain.TaskListDomain;
import com.example.project.repository.TaskListRepository;
import com.example.project.enums.TimeInterval;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/TaskList/")
public class TaskListController {
    @Autowired
    private TaskListRepository taskListRepository;

    //Список всех задач
    @RequestMapping(value = "/AllTask", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskListDomain> findAll() {
        return taskListRepository.findAll();
    }

    //Добавить задачу
    @RequestMapping(value = "/AddTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create(@RequestBody TaskListDomain obj) {
        taskListRepository.save(obj);
    }

    //Найти задачу по ID
    @RequestMapping(value = "/ReadTask/{taskListId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<TaskListDomain> read(@PathVariable("taskListId") Integer id) {
        return taskListRepository.findById(id);
    }

    //Обновить задачу
    @RequestMapping(value = "/UpdateTask/{taskListId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody TaskListDomain obj,
                       @PathVariable("taskListId") Integer id) {
        obj.setTaskListId(id);
        taskListRepository.save(obj);
    }

    //Удалить задачу
    @RequestMapping(value = "/DeleteTask/{taskListId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("taskListId") Integer obj) {
        taskListRepository.deleteById(obj);

    }

    //Просмотр списка задач на сегодня/неделю/месяц с фильтрацией по выполнению
    @RequestMapping(value = "/TaskInterval", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskListDomain> TaskInterval(@RequestParam("taskInterval") TimeInterval taskInterval, @RequestParam(required = false) Boolean bool) {

        LocalDate date = null;
        if (TimeInterval.DAY.equals(taskInterval)) {
            date = LocalDate.now();
        } else if (TimeInterval.WEEK.equals(taskInterval)) {
            date = LocalDate.now().minusDays(7);
        } else if (TimeInterval.MONTH.equals(taskInterval)) {
            date = LocalDate.now().minusMonths(1);
        }
        if (date == null) {
            return taskListRepository.findAll(bool);
        } else {
            return taskListRepository.interval(date, bool);
        }

    }


}
