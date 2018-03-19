package org.kevin.OwnBlog.dao;

import org.kevin.OwnBlog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
public interface CommentRepository extends JpaRepository<Comment,Long>{
}
