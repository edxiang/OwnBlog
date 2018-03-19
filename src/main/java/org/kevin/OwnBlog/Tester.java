package org.kevin.OwnBlog;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.kevin.OwnBlog.model.Album;
import org.kevin.OwnBlog.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
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
        albumService.save(a);
    }

}
