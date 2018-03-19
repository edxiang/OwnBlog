package org.kevin.OwnBlog.model;

import lombok.Data;
import org.kevin.OwnBlog.Utils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kevin.Z on 2018/2/28.
 */
@Entity
@Data
public class Twitter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP")
    private Date createTime;

    @Column(columnDefinition = "TEXT")
    private String twitter;

    public Twitter(){
        createTime = Utils.getGTM8();
    }
}
