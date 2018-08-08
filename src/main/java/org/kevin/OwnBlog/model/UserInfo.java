package org.kevin.OwnBlog.model;

import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Kevin.Z on 2018/5/24.
 */
@Entity
@Data
public class UserInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(columnDefinition = "TIMESTAMP")
    private Date createTime;

    @Column(unique = true)
    private String username;
    private String name;
    private String password;
    private String salt;
    private byte state;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "SysUserRole", joinColumns = {@JoinColumn(name = "uid")}, inverseJoinColumns = {@JoinColumn(name = "roleId")})
    private List<SysRole> roleList;

    public String getCredentialsSalt(){
        return this.username + this.salt;
    }
}
