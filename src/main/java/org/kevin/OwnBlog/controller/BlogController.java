package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.Utils;
import org.kevin.OwnBlog.model.Blog;
import org.kevin.OwnBlog.model.Comment;
import org.kevin.OwnBlog.service.BlogService;
import org.kevin.OwnBlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Kevin.Z on 2017/12/18.
 */
@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private RedisTemplate redisTemplate;

    @RequestMapping("/blogs")
    public String blogs(ModelMap map) {
        List<Blog> blogs = blogService.findAll();
        Collections.reverse(blogs);

        List<Long> blogIds = new ArrayList<>();
        Map<Long, Blog> blogMap = new HashMap<>();
        for (Blog b : blogs) {
            blogIds.add(b.getId());
            blogMap.put(b.getId(), b);
        }
        if (redisTemplate.hasKey("blogIds")) {
            redisTemplate.delete("blogIds");
            redisTemplate.delete("blogMap");
        }
        ValueOperations<String, List> idValue = redisTemplate.opsForValue();
        idValue.set("blogIds", blogIds);
        ValueOperations<String, Map> blogValue = redisTemplate.opsForValue();
        blogValue.set("blogMap", blogMap);

        map.addAttribute("blogs", blogs);
        return "blogs";
    }

    @RequestMapping(value = "/blogs/essay", method = RequestMethod.POST)
    public String newBlog(Blog blog, ModelMap model) {
        String content = Utils.replaceLineCharacter(blog.getContent());
        blog.setContent(content);
        String foreword = Utils.replaceLineCharacter(blog.getForeword());
        blog.setForeword(foreword);
        blogService.save(blog);
        return "redirect:../blogs";
    }

    @RequestMapping(value = "/blogs/blog")
    public String blog(Long id, ModelMap map) {
        Map<String,Blog> blogMap = (Map)(redisTemplate.opsForValue().get("blogMap"));
        Blog b = blogMap.get(id+"");
        //Blog blog = blogService.findById(id);
        List<Comment> commentList = commentService.findByLinkIdAndType(id, 1);
        map.addAttribute("blog", b);
        map.addAttribute("commentList", commentList);
        return "blog";
    }

    @RequestMapping(value = "/blogs/newComment")
    public String newComment(HttpServletRequest request) {
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String content = Utils.replaceLineCharacter(request.getParameter("comment"));
        Comment comment = new Comment();
        comment.setType(1);
        comment.setComments(content);
        comment.setName(name);
        comment.setLinkId(id);
        commentService.save(comment);

        return "redirect:/blogs/blog?id=" + id;
    }

    private void redisOperation(){

    }
}
