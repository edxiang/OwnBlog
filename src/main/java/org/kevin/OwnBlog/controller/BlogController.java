package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.Utils;
import org.kevin.OwnBlog.model.Blog;
import org.kevin.OwnBlog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * Created by Kevin.Z on 2017/12/18.
 */
@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;

    @RequestMapping(value = "/blogs/essay", method = RequestMethod.POST)
    public String newBlog(Blog blog, ModelMap model) {
        String content = blog.getContent().replaceAll("\\r\\n","<br/>");
        blog.setContent(content);
        String foreword = blog.getForeword().replaceAll("\\r\\n","<br/>");
        blog.setForeword(foreword);
        blogService.save(blog);
        return "redirect:../blogs";
    }

    @RequestMapping(value = "/blogs/blog")
    public String blog(long id, ModelMap map){
        Blog blog = blogService.findById(id);
        map.addAttribute("blog",blog);
        return "blog";
    }
}
