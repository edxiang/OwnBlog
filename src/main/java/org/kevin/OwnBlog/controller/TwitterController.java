package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.model.Twitter;
import org.kevin.OwnBlog.model.TwitterCriteria;
import org.kevin.OwnBlog.service.TwitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kevin.Z on 2018/3/1.
 */
@Controller
public class TwitterController {
    @Autowired
    private TwitterService twitterService;
    private static final int SIZE = 10;

    @RequestMapping("/twitters")
    public String twitters(ModelMap map, HttpServletRequest request) {
        long pages = twitterService.count();
        List<Integer> pageList = new ArrayList<>();
        for (int i = 0; i < pages / SIZE; i++) {
            pageList.add(i);
        }
        if (pages % SIZE > 0) {
            pageList.add(pageList.size());
        }

        String toPage = request.getParameter("page");
        int page = 0;
        if (toPage != null)
            page = Integer.parseInt(toPage);
        TwitterCriteria criteria = new TwitterCriteria();
        Page<Twitter> twitterPage = twitterService.findTwitterCriteria(page, SIZE, criteria);

        /*List<Twitter> twitters = twitterService.findAll();
        Collections.reverse(twitters);*/
        map.addAttribute("twitters", twitterPage);
        map.addAttribute("pages", pageList);
        map.addAttribute("currentPage",page);
        map.addAttribute("last",pageList.size());
        return "twitters";
    }

    @RequestMapping(value = "/twitters/newTwitter", method = RequestMethod.POST)
    public String add(HttpServletRequest request) {
        Twitter twitter = new Twitter();
        String content = request.getParameter("twitter").replaceAll("\\r\\n", "<br/>");
        twitter.setTwitter(content);
        twitterService.save(twitter);
        return "redirect:../twitters";
    }
}
