package com.project.demo.controller;

import com.project.demo.entity.AnnouncementNotice;
import com.project.demo.service.AnnouncementNoticeService;
import com.project.demo.controller.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 *公告通知：(AnnouncementNotice)表控制层
 *
 */
@RestController
@RequestMapping("/announcement_notice")
public class AnnouncementNoticeController extends BaseController<AnnouncementNotice,AnnouncementNoticeService> {

    /**
     *公告通知对象
     */
    @Autowired
    public AnnouncementNoticeController(AnnouncementNoticeService service) {
        setService(service);
    }

    @PostMapping("/add")
    @Transactional
    public Map<String, Object> add(HttpServletRequest request) throws IOException {
        Map<String,Object> paramMap = service.readBody(request.getReader());
        this.addMap(paramMap);
        return success(1);
    }

}
