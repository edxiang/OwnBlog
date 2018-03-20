package org.kevin.OwnBlog.service;

import org.kevin.OwnBlog.dao.CommentRepository;
import org.kevin.OwnBlog.model.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kevin.Z on 2018/3/20.
 */
@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void save(Comment comment){
        commentRepository.save(comment);
    }

    public List<Comment> findByLinkIdAndType(long id, int type){
        return commentRepository.findByLinkIdAndType(id,type);
    }

    public List<Comment> findAll(){
        return commentRepository.findAll();
    }

    public void delete(Comment comment){
        commentRepository.delete(comment);
    }
}
