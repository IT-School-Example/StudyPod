package com.itschool.study_pod.view.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notice")
public class NoticeController {
    @GetMapping("/management")
    public String getListPage() {
        return "notice/notice-list";
    }
}
