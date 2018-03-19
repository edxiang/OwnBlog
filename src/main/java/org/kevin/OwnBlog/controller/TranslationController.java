package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.model.Translation;
import org.kevin.OwnBlog.service.TranslationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Kevin.Z on 2018/3/16.
 */
@Controller
public class TranslationController {
    @Autowired
    private TranslationService translationService;

    @RequestMapping(value="/translations/article", method = RequestMethod.POST)
    public String save(Translation translation){
        String content = translation.getContent().replaceAll("\\r\\n","<br/>");
        translation.setContent(content);
        String foreword = translation.getForeword().replaceAll("\\r\\n","<br/>");
        translation.setForeword(foreword);

        translationService.save(translation);
        return "redirect:../translation";
    }
}
