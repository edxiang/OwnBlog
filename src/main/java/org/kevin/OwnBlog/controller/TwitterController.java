package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.model.Twitter;
import org.kevin.OwnBlog.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kevin.Z on 2018/3/1.
 */
@Controller
public class TwitterController {
    @Autowired
    private TwitterService twitterService;

    @RequestMapping(value = "/twitters/newTwitter", method = RequestMethod.POST)
    public String add(HttpServletRequest request){
        Twitter twitter = new Twitter();
        String content = request.getParameter("twitter").replaceAll("\\r\\n","<br/>");
        twitter.setTwitter(content);
        twitterService.save(twitter);
        return "redirect:../twitters";
    }
}
