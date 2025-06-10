package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *选课信息：(CourseSelectionInformation)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "CourseSelectionInformation")
public class CourseSelectionInformation implements Serializable {

    //CourseSelectionInformation编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "course_selection_information_id")
    private Integer course_selection_information_id;
   // 课程编号
   @Basic
    private String course_number;
   // 课程名称
   @Basic
    private String course_name;
   // 学生信息
   @Basic
    private Integer student_information;
   // 姓名
   @Basic
    private String full_name;
   // 学号
   @Basic
    private String student_number;
   // 选课学期
   @Basic
    private String elective_semester;
   // 选课备注
   @Basic
    private String notes_on_course_selection;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
