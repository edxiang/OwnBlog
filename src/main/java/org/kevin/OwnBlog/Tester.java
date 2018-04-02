package org.kevin.OwnBlog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kevin.OwnBlog.model.Album;
import org.kevin.OwnBlog.model.Twitter;
import org.kevin.OwnBlog.model.TwitterCriteria;
import org.kevin.OwnBlog.service.AlbumService;
import org.kevin.OwnBlog.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Iterator;
import java.util.TimeZone;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Tester {
    @Autowired
    private AlbumService albumService;

    @Test
    public void album(){
        Album a = new Album();
        a.setTag("version 1");
        a.setTitle("test1");
        //albumService.save(a);
    }

    @Autowired
    private TwitterService twitterService;
    @Test
    public void pageInTwitter(){
        /*int page = 2;
        int size = 15;
        TwitterCriteria criteria = new TwitterCriteria();
        Page<Twitter> pageable = twitterService.findTwitterCriteria(page,size,criteria);
        Iterator<Twitter> ts = pageable.iterator();
        while(ts.hasNext()){
            Twitter t = ts.next();
            System.out.println(t.getId());
        }*/
        System.out.println(twitterService.count());
    }

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void t1(){
        stringRedisTemplate.opsForValue().set("aaa","111");
        System.out.println(stringRedisTemplate.opsForValue().get("aaa"));
    }

}
