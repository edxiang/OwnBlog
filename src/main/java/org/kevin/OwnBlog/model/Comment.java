package org.kevin.OwnBlog.model;

import lombok.Data;
import org.kevin.OwnBlog.Utils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
@Entity
@Data
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String comments;

    private String connection;
    @Column(columnDefinition = "TIMESTAMP")
    private Date createTime;

    public Comment(){
        createTime = Utils.getGTM8();
    }
}
