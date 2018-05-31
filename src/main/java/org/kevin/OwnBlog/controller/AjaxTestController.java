package org.kevin.OwnBlog.controller;

import lombok.val;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kevin.Z on 2018/5/30.
 */
@Controller
public class AjaxTestController {
    @RequestMapping("/testJquery")
    public String testJquery() {
        return "/jquery/test";
    }

    @RequestMapping("/ajaxTesting")
    @ResponseBody
    public String testAjax(HttpServletRequest request) {
        String name = request.getParameter("username");
        String psw = request.getParameter("psw");
        return "hello world!";
    }

    @RequestMapping("/ajaxTestProgress")
    @ResponseBody
    public Integer testAjaxProgress(HttpServletRequest request) {
        int value = Integer.parseInt(request.getParameter("progressValue"));
        int increment = (int)(System.nanoTime() % 20);
        if (value == 100) {
            return 0;
        } else {
            value += increment;
            if (value > 100)
                value = 100;
        }
        return value;
    }
}
