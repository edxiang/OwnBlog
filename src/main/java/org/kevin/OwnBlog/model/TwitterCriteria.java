package org.kevin.OwnBlog.model;

import lombok.Data;

import java.util.Date;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
@Data
public class TwitterCriteria {
    private Date fromDate;
    private Date toDate;
    private String value;
}
