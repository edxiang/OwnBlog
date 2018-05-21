package org.kevin.OwnBlog.dao;

import org.kevin.OwnBlog.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Created by Kevin.Z on 2018/5/21
 */
public interface TaskRepository extends JpaRepository<Task,Long> {
    Task findByCreateTimeBetween(Date date1, Date date2);
    Task findById(Long id);
}
