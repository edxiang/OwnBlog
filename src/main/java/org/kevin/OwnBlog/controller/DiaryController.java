package org.kevin.OwnBlog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by Kevin.Z on 2018/4/26.
 */
@Controller
public class DiaryController {

    @RequestMapping("/diary")
    public String Diary(HttpServletRequest request, ModelMap map){
        Object obj = request.getSession().getAttribute("login");
        if(obj != null && (Boolean)obj){
            map.addAttribute("login",true);
        }
        return "diary";
    }

    @RequestMapping("/diary/items")
    public String NewItems(HttpServletRequest request){
        Enumeration paramNames = request.getParameterNames();
        while(paramNames.hasMoreElements()){
            String paramName = (String)paramNames.nextElement();
            String value = request.getParameter(paramName);
            System.out.println(paramName + "-" + value);
        }
        return "redirect:../diary";
    }
}
