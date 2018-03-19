package org.kevin.OwnBlog.model;

import lombok.Data;
import org.kevin.OwnBlog.Utils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kevin.Z on 2017/12/13.
 */
@Entity
@Data
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private Long albumId;

    private String src;
    @Column(columnDefinition = "TIMESTAMP")
    private Date createTime;

    public Media(){
        createTime = Utils.getGTM8();
    }
}
