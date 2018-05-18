package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.model.*;
import org.kevin.OwnBlog.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Kevin.Z on 2017/12/18.
 */
@Controller
public class MainController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping("/")
    public String toLogin() {
        return "redirect:login";
    }

    @RequestMapping("/coverPage")
    public String coverPage() {
        return "main2";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, ModelMap map) {
        Object obj = request.getSession().getAttribute("login");
        if (obj != null && (Boolean) obj) {
            return "redirect:coverPage";
        }
        map.addAttribute("errorCode", "0");
        return "login";
    }

    @RequestMapping(value = "/index", method = RequestMethod.POST)
    public String index(HttpServletRequest request, ModelMap map) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (username == null || password == null) {
            map.addAttribute("errorCode", "1");
            request.getSession().setAttribute("login", false);
            return "login";
        } else if (!username.equals("edxiao1993") || !password.equals("0322")) {
            map.addAttribute("errorCode", "2");
            request.getSession().setAttribute("login", false);
            return "login";
        } else {
            request.getSession().setAttribute("login", true);
            return "main2";
        }
    }

    @RequestMapping("/albums")
    public String albums(ModelMap map) {
        List<Album> albums = albumService.findAll();
        map.addAttribute("albums", albums);
        return "albums";
    }

    @RequestMapping("/addblog")
    public String addBlog() {
        return "addBlog";
    }

    @RequestMapping("/testJquery")
    public String testJquery(){
        return "/jquery/test";
    }

}
