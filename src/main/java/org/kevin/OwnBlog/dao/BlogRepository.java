package org.kevin.OwnBlog.dao;

import org.kevin.OwnBlog.model.Blog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
public interface BlogRepository extends JpaRepository<Blog,Long>{
    Blog findByTitle(String title);
    Blog findById(Long id);
    List<Blog> findByContentLike(String keyWord);
    List<Blog> findAll();
}
