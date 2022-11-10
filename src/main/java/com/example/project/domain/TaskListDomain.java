package com.example.project.domain;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.time.LocalDate;



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
    private LocalDate dateTask;

    @NotNull
    private Boolean isOpen;


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
