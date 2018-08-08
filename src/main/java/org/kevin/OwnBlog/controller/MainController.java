package org.kevin.OwnBlog.controller;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
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
import java.util.Map;

/**
 * Created by Kevin.Z on 2017/12/18.
 */
@Controller
public class MainController {
    @Autowired
    private AlbumService albumService;

    @RequestMapping({"/", "/index"})
    public String toLogin() {
        return "/main2";
    }

    @RequestMapping("/coverPage")
    public String coverPage() {
        return "/main2";
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, ModelMap map) throws Exception {
        String exception = (String) request.getAttribute("shiroLoginFailure");
        System.out.println("exception=" + exception);
        if (exception != null) {
            if (UnknownAccountException.class.getName().equals(exception)) {
                System.out.println("UnknownAccountException -- > 账号不存在：");
                map.addAttribute("errorCode", 1);
            } else if (IncorrectCredentialsException.class.getName().equals(exception)) {
                System.out.println("IncorrectCredentialsException -- > 密码不正确：");
                map.addAttribute("errorCode", 2);
            } else {
                System.out.println("else -- >" + exception);
            }
        }

        return "/login";
    }

    /*@RequestMapping(value = "/index", method = RequestMethod.POST)
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
    }*/

    @RequestMapping("/albums")
    public String albums(ModelMap map) {
        List<Album> albums = albumService.findAll();
        map.addAttribute("albums", albums);
        return "/albums";
    }

    @RequestMapping("/addblog")
    public String addBlog() {
        return "/addBlog";
    }

}
