package atb.social.network.controller;

import atb.social.network.dto.NewsDTO;
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
@RequestMapping("/api")

public class NewsController {


    @Autowired
    private NewsService newsService;







    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/news" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllNews(@RequestParam String count,@RequestParam String page) throws Exception {
        List<NewsModel> allNews;
        try{


            allNews  = newsService.getAllNews(Integer.parseInt(count),Integer.parseInt(page));


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(allNews, HttpStatus.OK);



    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
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



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/news/edit/{id}" , method = RequestMethod.POST)
    public ResponseEntity<Object> editNews(@PathVariable("id") String nId, @RequestBody NewsDTO newsDTO) throws Exception {

        try{

            newsService.editNews(Integer.parseInt(nId),newsDTO);


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("DONE", HttpStatus.OK);



    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/news/remove/{id}" , method = RequestMethod.DELETE)
    public ResponseEntity<Object> removeNews(@PathVariable("id") String nId) throws Exception {

        try{

            newsService.removeNews(Integer.parseInt(nId));


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("DONE", HttpStatus.OK);



    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/news/save" , method = RequestMethod.POST)
    public ResponseEntity<Object> saveNews(@RequestBody NewsDTO newsDTO) throws Exception {

        try{

            newsService.saveNews(newsDTO);


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("DONE", HttpStatus.OK);



    }




}
