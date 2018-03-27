package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.Utils;
import org.kevin.OwnBlog.model.Blog;
import org.kevin.OwnBlog.model.Comment;
import org.kevin.OwnBlog.service.BlogService;
import org.kevin.OwnBlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;

/**
 * Created by Kevin.Z on 2017/12/18.
 */
@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/blogs")
    public String blogs(ModelMap map) {
        List<Blog> blogs = blogService.findAll();
        Collections.reverse(blogs);
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
    public String blog(long id, ModelMap map){
        Blog blog = blogService.findById(id);
        List<Comment> commentList = commentService.findByLinkIdAndType(id,1);
        map.addAttribute("blog",blog);
        map.addAttribute("commentList",commentList);
        return "blog";
    }

    @RequestMapping(value = "/blogs/newComment")
    public String newComment(HttpServletRequest request){
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
}
