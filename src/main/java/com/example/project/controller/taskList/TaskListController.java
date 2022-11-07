package com.example.project.controller.taskList;


import com.example.project.domain.taskList.TaskListDomain;
import com.example.project.repository.taskList.TaskListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping(value = "/TaskList/")
public class TaskListController {
    @Autowired
    private TaskListRepository taskListRepository;

    @RequestMapping(value = "/AllTaskList", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskListDomain> findAll() {
        return taskListRepository.findAll();
    }

    @RequestMapping(value = "/AddTask", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public void create (@RequestBody TaskListDomain obj){
        taskListRepository.save(obj);
    }

    @RequestMapping(value = "/SearchUser/{taskListId}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<TaskListDomain> read(@PathVariable("taskListId") Integer id) {
        return taskListRepository.findById(id);
    }

    @RequestMapping(value = "/UpdateTask/{taskListId}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
    public List<TaskListDomain> update(@RequestBody TaskListDomain obj,
                                    @PathVariable("taskListId") Integer taskListId) {
        return taskListRepository.update(obj, taskListId);
    }


    @RequestMapping(value = "/DeleteTask/{taskListId}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
    public void delete(@PathVariable("taskListId") Integer obj) {
        taskListRepository.deleteById(obj);

    }

}
