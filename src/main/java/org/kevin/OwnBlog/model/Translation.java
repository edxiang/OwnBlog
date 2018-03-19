package org.kevin.OwnBlog.model;

import lombok.Data;
import org.kevin.OwnBlog.Utils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kevin.Z on 2018/3/16.
 */
@Entity
@Data
public class Translation {
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

    public Translation(){
        createTime = Utils.getGTM8();
    }
}
