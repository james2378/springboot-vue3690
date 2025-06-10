package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *成绩信息：(AchievementInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "AchievementInformation")
public class AchievementInformation implements Serializable {

    //AchievementInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "achievement_information_id")
    private Integer achievement_information_id;
   // 学生信息
   @Basic
    private Integer student_information;
   // 科目名称
   @Basic
    private String account_name;
   // 学生成绩
   @Basic
    private Integer student_achievement;
   // 平均成绩
   @Basic
    private Integer average_score;
   // 备注信息
   @Basic
    private String remark_information;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
