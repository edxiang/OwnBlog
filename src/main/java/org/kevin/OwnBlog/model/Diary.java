package org.kevin.OwnBlog.model;

import lombok.Data;
import org.kevin.OwnBlog.Utils;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Kevin.Z on 2018/4/28.
 */
@Entity
@Data
public class Diary {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(columnDefinition = "TIMESTAMP")
    private Date createTime;
    @Column(columnDefinition = "TIMESTAMP")
    private Date forTime;

    @Column(columnDefinition = "TEXT")
    private String item;
    @Column(columnDefinition = "TEXT")
    private String note;

    private Boolean status;

    public Diary() {
        this.createTime = Utils.getGTM8();
    }
}
