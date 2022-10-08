package com.boots.controller;

import com.boots.entity.News;
import com.boots.repository.NewsRepository;
import com.boots.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class NewsController {

    @Autowired
    NewsService newsService;

    @Autowired
    NewsRepository newsRepository;

    //страница orders
    @RequestMapping("/news")
    public String getNews(){
        return "news";
    }

    //страница создания заявки
    @RequestMapping("/createNews")
    public String getCreateNews(){
        return "createNews";
    }

    //список товаров
    @RequestMapping("/listNews")
    public String getListNews(Model model){

        model.addAttribute("allNews", newsService.allNews());

        return "listNews";
    }


    @GetMapping("/createNews")
    public String registrationNews(Model model) {
        model.addAttribute("newsForm", new News());
        return "createNews";
    }

    @PostMapping("/createNews")
    public String addNews(@ModelAttribute("newsForm") News newsForm, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "createNews";
        }
        News news = new News();
        news.setId(newsForm.getId());
        news.setName(newsForm.getName());
        news.setComment(newsForm.getComment());
        model.addAttribute("newsForm", news);
        newsService.saveNews(news);
        return "redirect:/";
    }

    @PostMapping(value = "/createNews/1", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addNews(@RequestBody News news) {

        return ResponseEntity.ok(new News(news.getName(), news.getComment()));
    }
}
