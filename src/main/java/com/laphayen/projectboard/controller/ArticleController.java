package com.laphayen.projectboard.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping("/articles")
@Controller
public class ArticleController {

    @GetMapping
    public String articles(ModelMap map) {
        map.addAttribute("articles", List.of());
        return "articles/index";
    }

    @GetMapping("/{articlesId}")
    public String article(@PathVariable Long articlesId, ModelMap map) {
        map.addAttribute("article", "article"); // TODO: 구현 시 실제 데이터 삽입
        map.addAttribute("articleComments", List.of());
        return "articles/detail";
    }
}
