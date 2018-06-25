package org.kevin.OwnBlog.controller;

import org.apache.log4j.Logger;
import org.kevin.OwnBlog.Utils;
import org.kevin.OwnBlog.model.Twitter;
import org.kevin.OwnBlog.model.TwitterCriteria;
import org.kevin.OwnBlog.service.TwitterService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin.Z on 2018/3/1.
 * 短心情
 */
@Controller
public class TwitterController {
    static Logger log = Logger.getLogger(TwitterController.class.getName());

    @Autowired
    private TwitterService twitterService;
    private static final long MILLISECOND_OF_ONE_DAY = 24 * 60 * 60 * 1000L;

    //
    @RequestMapping(value = "/twitters", method = RequestMethod.GET)
    public String twitters(ModelMap map, HttpServletRequest request) {
        TwitterCriteria criteria = new TwitterCriteria();
        int size = 10;

        String fromDateString = request.getParameter("fromDate");
        Date fromDate = null;
        if (fromDateString != null && !fromDateString.equals("")) {
            fromDate = Utils.StringToDate(fromDateString);
            criteria.setFromDate(fromDate);
            size = 1000;
        }
        String toDateString = request.getParameter("toDate");
        if (toDateString != null && !toDateString.equals("")) {
            Date toDate = Utils.StringToDate(toDateString);
            toDate = new Date(toDate.getTime() + MILLISECOND_OF_ONE_DAY - 1);
            if (fromDate != null) {
                if (fromDate.getTime() < toDate.getTime()) {
                    criteria.setToDate(toDate);
                } else if (fromDate.getTime() == toDate.getTime()) {
                    criteria.setToDate(toDate);
                } else {
                    criteria.setToDate(toDate);
                    criteria.setFromDate(null);
                }
            }
            size = 1000;
        }
        String value = request.getParameter("value");
        if (value != null && !value.equals("")) {
            criteria.setValue("%" + value + "%");
            size = 1000;
        }

        String toPage = request.getParameter("page");
        int page = 0;
        if (toPage != null && !toPage.equals(""))
            page = Integer.parseInt(toPage);
        Page<Twitter> twitterPage = twitterService.findTwitterCriteria(page, size, criteria);

        List<Integer> pageList = new ArrayList<>();
        for (int i = 0; i < twitterPage.getTotalPages(); i++) {
            pageList.add(i);
        }

        map.addAttribute("twitters", twitterPage);
        map.addAttribute("pages", pageList);
        map.addAttribute("currentPage", page);

        int previous = -1;
        int next = -1;
        previous = page == 0 ? 0 : page - 1;
        next = page == pageList.size() - 1 ? pageList.size() - 1 : page + 1;
        map.addAttribute("previous", previous);
        map.addAttribute("next", next);

        Object obj = request.getSession().getAttribute("login");
        if (obj != null && (Boolean) obj) {
            map.addAttribute("login", true);
        }
        return "twitters";
    }

    // 新建一条短心情
    @RequestMapping(value = "/twitters/newTwitter", method = RequestMethod.POST)
    public String add(HttpServletRequest request) {
        Twitter twitter = new Twitter();
        String content = request.getParameter("twitter").replaceAll("\\r\\n", "<br/>");
        twitter.setTwitter(content);
        twitterService.save(twitter);
        log.error("******************************************************************" + content);
        return "redirect:../twitters";
    }
}
