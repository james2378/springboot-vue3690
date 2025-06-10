package com.project.demo.entity;

import java.sql.Date;
import java.sql.Timestamp;
import com.project.demo.entity.base.BaseEntity;
import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


/**
 *公告通知：(AnnouncementNotice)表实体类
 *
 */
@Setter
@Getter
@Entity(name = "AnnouncementNotice")
public class AnnouncementNotice implements Serializable {

    //AnnouncementNotice编号
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "announcement_notice_id")
    private Integer announcement_notice_id;
   // 标题名称
   @Basic
    private String title_name;
   // 发布人
   @Basic
    private Integer publisher;
   // 公告内容
   @Basic
    private String announcement_content;

    // 更新时间
    @Basic
    private Timestamp update_time;

    // 创建时间
    @Basic
    private Timestamp create_time;

}
