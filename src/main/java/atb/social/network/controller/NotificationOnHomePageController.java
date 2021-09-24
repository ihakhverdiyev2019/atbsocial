package atb.social.network.controller;


import atb.social.network.model.NotificationOnHomePageModel;
import atb.social.network.service.NotificitaionOnHomePage.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")

public class NotificationOnHomePageController {
    @Autowired
    private NotificationService notificationService;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/notifications/daily" , method = RequestMethod.GET)
    public ResponseEntity<Object> getDailyNotification() throws Exception {
        List<NotificationOnHomePageModel> notificationOnHomePageModelList;
        try{

            notificationOnHomePageModelList  = notificationService.getNotificationByDate();


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(notificationOnHomePageModelList, HttpStatus.OK);



    }



    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/notifications" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllNotification() throws Exception {
        List<NotificationOnHomePageModel> notificationOnHomePageModelList;
        try{

            notificationOnHomePageModelList  = notificationService.getAllNotification();


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(notificationOnHomePageModelList, HttpStatus.OK);



    }
}
