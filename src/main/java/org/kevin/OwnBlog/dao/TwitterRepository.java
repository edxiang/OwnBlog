package org.kevin.OwnBlog.dao;

import org.kevin.OwnBlog.model.Twitter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Kevin.Z on 2018/2/28.
 */
public interface TwitterRepository extends JpaRepository<Twitter,Long>, JpaSpecificationExecutor<Twitter> {
    Twitter findById(Long id);
}
