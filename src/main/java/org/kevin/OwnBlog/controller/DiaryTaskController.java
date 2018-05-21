package org.kevin.OwnBlog.controller;

import org.kevin.OwnBlog.Utils;
import org.kevin.OwnBlog.model.Task;
import org.kevin.OwnBlog.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Kevin.Z on 2018.5.21
 */
@Controller
public class DiaryTaskController {
    @Autowired
    private TaskService taskService;

    @RequestMapping("/diaryTask")
    public String diaryTask(ModelMap map) {
        Task task = taskService.findByCreateTime(Utils.getBeginTimeOfDay(), Utils.getEndTimeOfDay());
        map.addAttribute("task", task);
        return "/diaryTask";
    }


}
