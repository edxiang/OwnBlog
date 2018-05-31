package org.kevin.OwnBlog.controller;

import com.google.gson.Gson;
import org.kevin.OwnBlog.Utils;
import org.kevin.OwnBlog.model.Task;
import org.kevin.OwnBlog.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by Kevin.Z on 2018.5.21
 */
@Controller
public class DiaryTaskController {
    @Autowired
    private TaskService taskService;

    private static final long milliseconds = 24 * 60 * 60 * 1000L;

    @RequestMapping("/diaryTask")
    public String diaryTask(ModelMap map, HttpServletRequest request) {
        Task task = taskService.findByCreateTime(Utils.getBeginTimeOfDay(), Utils.getEndTimeOfDay());
        if(task == null){
            task = new Task();
            task.setContent("");
            task.setSummary("");
        }
        map.addAttribute("diaryTask", task);

        Object obj = request.getSession().getAttribute("login");
        if (obj != null && (Boolean) obj) {
            map.addAttribute("login", true);
        }
        return "/diaryTask";
    }

    @RequestMapping("/diaryTask/update")
    @ResponseBody
    public String update(HttpServletRequest request){
        Task task = taskService.findByCreateTime(Utils.getBeginTimeOfDay(), Utils.getEndTimeOfDay());
        if(task == null)
            task = new Task();

        String content = request.getParameter("content");
        String summary = request.getParameter("summary");
        String value = "";
        if(content != null){
            content = Utils.replaceLineCharacter(content);
            task.setContent(content);
            value = content;
        }else if (summary != null){
            summary = Utils.replaceLineCharacter(summary);
            task.setSummary(summary);
            value = summary;
        }else {
            System.out.println("there are nothing I can put into database");
        }
        taskService.save(task);
        return value;
    }

    @RequestMapping("diaryTask/getTaskByDate")
    @ResponseBody
    public String getTaskByDate(HttpServletRequest request, ModelMap map){
        String date = request.getParameter("date");
        Date date1 = date != null ? Utils.StringToDate(date):Utils.getGTM8();
        Date date2 = new Date(date1.getTime() + milliseconds - 1);
        Task task = taskService.findByCreateTime(date1,date2);
        String json = new Gson().toJson(task);
        return json;
    }

}
