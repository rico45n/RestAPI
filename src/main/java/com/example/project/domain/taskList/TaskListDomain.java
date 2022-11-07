package com.example.project.domain.taskList;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "taskList")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskListDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskListId;

    @NotNull
    private String nameTask;
    @NotNull
    private String task;

    @NotNull
    private String dateTask;

    @NotNull
    private boolean isOpen;


    public Integer getTaskListId() {
        return taskListId;
    }

    public void setTaskListId(Integer taskListId) {
        this.taskListId = taskListId;
    }

    public String getNameTask() {
        return nameTask;
    }

    public void setNameTask(String nameTask) {
        this.nameTask = nameTask;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDateTask() {
        return dateTask;
    }

    public void setDateTask(String dateTask) {
        this.dateTask = dateTask;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }



    @Override
    public String toString() {
        return "TaskListDomain{" +
                "taskListId=" + taskListId +
                ", nameTask='" + nameTask + '\'' +
                ", task='" + task + '\'' +
                ", dateTask='" + dateTask + '\'' +
                ", isOpen=" + isOpen +
                '}';
    }

}
