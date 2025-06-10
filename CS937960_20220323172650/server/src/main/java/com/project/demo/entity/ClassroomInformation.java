package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *教室信息：(ClassroomInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "ClassroomInformation")
public class ClassroomInformation implements Serializable {

    //ClassroomInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "classroom_information_id")
    private Integer classroom_information_id;
   // 教室名称
   @Basic
    private String classroom_name;
   // 教室图片
   @Basic
    private String classroom_pictures;
   // 教室负责人
   @Basic
    private Integer classroom_leader;
   // 可容纳人数
   @Basic
    private Integer capacity;
   // 设备信息
   @Basic
    private String equipment_information;
   // 教室位置
   @Basic
    private String classroom_location;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
