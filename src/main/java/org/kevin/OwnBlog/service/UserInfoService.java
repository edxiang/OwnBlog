package org.kevin.OwnBlog.service;

import org.kevin.OwnBlog.dao.UserInfoRepository;
import org.kevin.OwnBlog.model.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Kevin.Z on 2018/8/8.
 */
@Service
public class UserInfoService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    public UserInfo findByUsername(String username){
        return userInfoRepository.findByUsername(username);
    }
}
