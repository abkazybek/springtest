package com.boots.service;

import com.boots.entity.News;
import com.boots.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsService{

    @Autowired
    public NewsRepository newsRepository;

    public List<News> allNews() {
        return newsRepository.findAll();
    }

    public void saveNews(News news) {
        newsRepository.save(news);
    }


}
