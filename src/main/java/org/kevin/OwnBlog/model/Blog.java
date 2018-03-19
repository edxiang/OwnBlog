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
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String foreword;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String content;

    private String title;
    @Column(columnDefinition = "TIMESTAMP")
    private Date createTime;

    public Blog(){
        createTime = Utils.getGTM8();
    }
}
