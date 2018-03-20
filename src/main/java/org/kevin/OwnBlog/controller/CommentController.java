package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Kevin.Z on 2018/3/20.
 */
@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;

}
