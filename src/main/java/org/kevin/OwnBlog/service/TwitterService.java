package org.kevin.OwnBlog.service;

import org.kevin.OwnBlog.dao.TwitterRepository;
import org.kevin.OwnBlog.model.Twitter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Kevin.Z on 2018/2/28.
 */
@Service
public class TwitterService {
    @Autowired
    private TwitterRepository twitterRepository;

    public void save(Twitter twitter){
         twitterRepository.save(twitter);
    }

    public List<Twitter> findAll(){
        return twitterRepository.findAll();
    }

    public Twitter findById(Long id){
        return twitterRepository.findById(id);
    }

    public void delete(Twitter twitter){
        twitterRepository.delete(twitter);
    }
}
