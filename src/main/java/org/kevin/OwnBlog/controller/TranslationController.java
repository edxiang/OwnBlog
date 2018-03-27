package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.Utils;
import org.kevin.OwnBlog.model.Comment;
import org.kevin.OwnBlog.model.Translation;
import org.kevin.OwnBlog.service.CommentService;
import org.kevin.OwnBlog.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by Kevin.Z on 2018/3/16.
 */
@Controller
public class TranslationController {
    @Autowired
    private TranslationService translationService;
    @Autowired
    private CommentService commentService;

    @RequestMapping("/translation")
    public String translation(ModelMap map, HttpServletRequest request) {
        List<Translation> translations = translationService.findAll();
        map.addAttribute("translations", translations);

        String value = request.getParameter("id");
        if (value != null && !(value.trim()).equals("")) {
            long id = Long.parseLong(value);
            Translation translation = translationService.findById(id);
            map.addAttribute("translation", translation);

            List<Comment> commentList = commentService.findByLinkIdAndType(id, 3);
            map.addAttribute("commentList", commentList);
        } else {
            map.addAttribute("translation", translations.get(0));
            List<Comment> commentList = commentService.findByLinkIdAndType(translations.get(0).getId(), 3);
            map.addAttribute("commentList", commentList);
        }
        return "translations";
    }

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
