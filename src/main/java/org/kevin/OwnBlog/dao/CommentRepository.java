package org.kevin.OwnBlog.dao;

import org.kevin.OwnBlog.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
public interface CommentRepository extends JpaRepository<Comment,Long>{
    List<Comment> findByLinkIdAndType(long linkId,int type);
}
