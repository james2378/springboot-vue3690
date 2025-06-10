package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *教师用户：(TeacherUser)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "TeacherUser")
public class TeacherUser implements Serializable {

    //TeacherUser编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "teacher_user_id")
    private Integer teacher_user_id;
   // 姓名
   @Basic
    private String full_name;
   // 性别
   @Basic
    private String gender;
   // 工号
   @Basic
    private String job_number;
   // 职称
   @Basic
    private String title;
    // 用户编号
    @Id
    @Column(name = "user_id")
    private Integer userId;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
