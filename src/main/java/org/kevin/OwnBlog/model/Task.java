package org.kevin.OwnBlog.model;

import lombok.Data;
import org.kevin.OwnBlog.Utils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kevin.Z on 2018/5/21.
 */
@Entity
@Data
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP")
    private Date createTime;

    private String content;
    private String summary;

    public Task(){
        createTime = Utils.getGTM8();
    }
}
