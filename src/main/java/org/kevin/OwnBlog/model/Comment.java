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

    private long linkId;
    /**
     * 1:blog.
     * 2:album.
     * 3:translation.
     */
    private int type;

    private String name;

    @Column(columnDefinition = "TIMESTAMP")
    private Date createTime;

    public Comment(){
        createTime = Utils.getGTM8();
    }
}
