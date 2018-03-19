package org.kevin.OwnBlog.service;

import org.kevin.OwnBlog.dao.TranslationRepository;
import org.kevin.OwnBlog.model.Translation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kevin.Z on 2018/1/3.
 */
@Service
public class TranslationService {
    @Autowired
    private TranslationRepository translationRepository;

    public void save(Translation translation){
         translationRepository.save(translation);
    }

    public List<Translation> likeContent(String keyword){
        return translationRepository.findByContentLike(keyword);
    }

    public List<Translation> findAll(){
        return translationRepository.findAll();
    }

    public Translation findByTitle(String title){
        return translationRepository.findByTitle(title);
    }

    public Translation findById(Long id){
        return translationRepository.findById(id);
    }

    public void delete(Translation translation){
        translationRepository.delete(translation);
    }
}
