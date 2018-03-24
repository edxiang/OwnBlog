package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.Utils;
import org.kevin.OwnBlog.model.Comment;
import org.kevin.OwnBlog.model.Translation;
import org.kevin.OwnBlog.service.CommentService;
import org.kevin.OwnBlog.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Kevin.Z on 2018/3/16.
 */
@Controller
public class TranslationController {
    @Autowired
    private TranslationService translationService;
    @Autowired
    private CommentService commentService;

    @RequestMapping(value="/translations/article", method = RequestMethod.POST)
    public String save(Translation translation){
        String content = Utils.replaceLineCharacter(translation.getContent());
        translation.setContent(content);
        String foreword = Utils.replaceLineCharacter(translation.getForeword());
        translation.setForeword(foreword);

        translationService.save(translation);
        return "redirect:../translation";
    }

    @RequestMapping(value = "/translations/newComment",method = RequestMethod.POST)
    public String newComment(HttpServletRequest request){
        long id = Long.parseLong(request.getParameter("id"));
        String name = request.getParameter("name");
        String content = request.getParameter("comment");

        Comment comment = new Comment();
        comment.setLinkId(id);
        comment.setType(3);
        comment.setName(Utils.replaceLineCharacter(name));
        comment.setComments(Utils.replaceLineCharacter(content));
        commentService.save(comment);

        return "redirect:../translation?id=" + id;
    }
}
