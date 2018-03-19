package org.kevin.OwnBlog.dao;

import org.kevin.OwnBlog.model.Translation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
public interface TranslationRepository extends JpaRepository<Translation,Long>{
    Translation findByTitle(String title);
    Translation findById(Long id);
    List<Translation> findByContentLike(String keyWord);
    List<Translation> findAll();
}
