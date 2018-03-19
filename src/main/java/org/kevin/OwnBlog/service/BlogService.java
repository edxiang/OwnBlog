package org.kevin.OwnBlog.service;

import org.kevin.OwnBlog.dao.BlogRepository;
import org.kevin.OwnBlog.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kevin.Z on 2018/1/3.
 */
@Service
public class BlogService {
    @Autowired
    private BlogRepository blogRepository;

    public void save(Blog blog){
         blogRepository.save(blog);
    }

    public List<Blog> likeContent(String keyword){
        return blogRepository.findByContentLike(keyword);
    }

    public List<Blog> findAll(){
        return blogRepository.findAll();
    }

    public Blog findByTitle(String title){
        return blogRepository.findByTitle(title);
    }

    public Blog findById(Long id){
        return blogRepository.findById(id);
    }

    public void delete(Blog blog){
        blogRepository.delete(blog);
    }
}
