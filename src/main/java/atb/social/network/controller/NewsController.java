package atb.social.network.controller;

import atb.social.network.model.BankBranchModel;
import atb.social.network.model.NewsModel;
import atb.social.network.service.News.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NewsController {


    @Autowired
    private NewsService newsService;







    @RequestMapping(value = "/news" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllNews(@RequestParam String count,@RequestParam String page) throws Exception {
        List<NewsModel> allNews;
        try{
            System.out.println(count);
            System.out.println(page);

            allNews  = newsService.getAllNews(Integer.parseInt(count),Integer.parseInt(page));


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(allNews, HttpStatus.OK);



    }


    @RequestMapping(value = "/news/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllNewsById(@PathVariable("id") String nId) throws Exception {
        NewsModel newsModel;
        try{

            newsModel  = newsService.getNewsById(Integer.parseInt(nId));


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(newsModel, HttpStatus.OK);



    }




}
