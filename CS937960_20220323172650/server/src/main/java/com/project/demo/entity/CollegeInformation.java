package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *学院信息：(CollegeInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CollegeInformation")
public class CollegeInformation implements Serializable {

    //CollegeInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "college_information_id")
    private Integer college_information_id;
   // 学院名称
   @Basic
    private String college_name;
   // 建立时间
   @Basic
    private String establishment_time;
   // 学院图片
   @Basic
    private String college_pictures;
   // 学院简介
   @Basic
    private String college_profile;
   // 学院地址
   @Basic
    private String college_address;
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
