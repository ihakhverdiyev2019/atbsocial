package atb.social.network.controller;

import atb.social.network.model.NewsModel;
import atb.social.network.model.QuoteModel;
import atb.social.network.service.QuoteService.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @RequestMapping(value = "/quote" , method = RequestMethod.GET)
    public ResponseEntity<Object> getQuote() throws Exception {
        QuoteModel quoteModel;
        try{

            quoteModel  = quoteService.getQuote();


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(quoteModel, HttpStatus.OK);



    }

}
