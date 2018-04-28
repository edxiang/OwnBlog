package org.kevin.OwnBlog.model;

import lombok.Data;
import org.kevin.OwnBlog.Utils;
import org.kevin.OwnBlog.service.DiaryService;

import java.util.Date;

/**
 * Created by Kevin.Z on 2018/4/28.
 */
@Data
public class DiaryCriteria {
    private Date beginTimeOfDay;
    private Date endTimeOfDay;

    public DiaryCriteria(){
        this.beginTimeOfDay = Utils.getBeginTimeOfDay();
        this.endTimeOfDay = Utils.getEndTimeOfDay();
    }
}
