package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.model.Diary;
import org.kevin.OwnBlog.model.DiaryCriteria;
import org.kevin.OwnBlog.service.DiaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.Enumeration;

/**
 * Created by Kevin.Z on 2018/4/26.
 */
@Controller
public class DiaryController {
    @Autowired
    private DiaryService diaryService;

    @RequestMapping("/diary")
    public String Diary(HttpServletRequest request, ModelMap map){
        Object obj = request.getSession().getAttribute("login");
        if(obj != null && (Boolean)obj){
            map.addAttribute("login",true);
        }
        Page<Diary> page = diaryService.findDiaryCriteria(0,10,new DiaryCriteria());
        map.addAttribute("diaries",page);
        return "diary";
    }

    @RequestMapping("/diary/items")
    public String NewItems(HttpServletRequest request){
        Enumeration paramNames = request.getParameterNames();

        while(paramNames.hasMoreElements()){
            Diary diary = new Diary();
            String paramName = (String)paramNames.nextElement();
            String value = request.getParameter(paramName);
            diary.setItem(value);
            diaryService.save(diary);
        }
        return "redirect:../diary";
    }
}
