package org.kevin.OwnBlog;

import com.sun.xml.internal.ws.util.Pool;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kevin.OwnBlog.model.Album;
import org.kevin.OwnBlog.model.Diary;
import org.kevin.OwnBlog.model.Twitter;
import org.kevin.OwnBlog.model.TwitterCriteria;
import org.kevin.OwnBlog.service.AlbumService;
import org.kevin.OwnBlog.service.DiaryService;
import org.kevin.OwnBlog.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
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
        TwitterCriteria tc = new TwitterCriteria();
        String fromDate = "2018-4-8";
//        String toDate = "2018-5-7";
//        String value = "%damn%";
        tc.setFromDate(Utils.StringToDate(fromDate));
//        tc.setToDate(Utils.StringToDate(toDate));
//        tc.setValue(value);

        Page<Twitter> pages = twitterService.findTwitterCriteria(0,10,tc);
        List<Twitter> lists = pages.getContent();
        System.out.println(lists.size());
        System.out.println(pages.getTotalPages());
        for(Twitter t:pages){
            System.out.println(t.getTwitter() + "   -   "+ t.getCreateTime());
        }
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

    @Test
    public void testTime(){
        System.out.println(Utils.getGTM8());
        System.out.println(LocalDateTime.now());
    }

    @Autowired
    private DiaryService diaryService;
    @Test
    public void testDiary(){
        Diary d = new Diary();
        d.setItem("damn it");
        d.setNote("note");
        d.setStatus(true);
        diaryService.save(d);

        Diary diary = diaryService.findById(6L);
        System.out.println(diary.getStatus());
    }

}
