package org.kevin.OwnBlog.service;

import org.kevin.OwnBlog.dao.TaskRepository;
import org.kevin.OwnBlog.model.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    public void save(Task task){
        taskRepository.save(task);
    }

    public List<Task> findAll(){
        return taskRepository.findAll();
    }

    public Task findByCreateTime(Date date1, Date date2){
        return taskRepository.findByCreateTimeBetween(date1,date2);
    }

    public Task findById(Long id){
        return taskRepository.findById(id);
    }
}
