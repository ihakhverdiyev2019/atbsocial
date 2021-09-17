package atb.social.network.controller;

import atb.social.network.model.NewsModel;
import atb.social.network.model.QuoteModel;
import atb.social.network.service.QuoteService.QuoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class QuoteController {

    @Autowired
    private QuoteService quoteService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
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
