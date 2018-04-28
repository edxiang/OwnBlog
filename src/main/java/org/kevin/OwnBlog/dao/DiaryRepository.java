package org.kevin.OwnBlog.dao;

import org.kevin.OwnBlog.model.Diary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by Kevin.Z on 2018/2/28.
 */
public interface DiaryRepository extends JpaRepository<Diary,Long>, JpaSpecificationExecutor<Diary> {
    Diary findById(Long id);
}
