package org.kevin.OwnBlog.dao;

import org.kevin.OwnBlog.model.UserInfo;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Kevin.Z on 2018/8/8.
 */
public interface UserInfoRepository extends CrudRepository<UserInfo, Long>{
    public UserInfo findByUsername(String username);
}
