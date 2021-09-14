package atb.social.network.controller;

import atb.social.network.dto.CanteenDto;
import atb.social.network.model.QuoteModel;
import atb.social.network.service.CanteenService.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
public class CanteenController {

    @Autowired
    private CanteenService canteenService;

    @RequestMapping(value = "/canteen" , method = RequestMethod.GET)
    public ResponseEntity<Object> getQuote() throws Exception {
        List<CanteenDto> canteenDtoList;
        try{

            canteenDtoList  = canteenService.getCanteenData();


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(canteenDtoList, HttpStatus.OK);



    }
}
