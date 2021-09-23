package atb.social.network.service.QuoteService;


import atb.social.network.dto.QuoteDTO;
import atb.social.network.model.QuoteModel;
import atb.social.network.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QuoteServiceImpl implements QuoteService{

    @Autowired
    private QuoteRepository quoteRepository;


    @Override
    public QuoteModel getQuote() throws Exception {
        QuoteModel quoteModel;
        try{

            quoteModel=quoteRepository.findById(1).get();

        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return quoteModel;
    }

    @Override
    public void save(QuoteDTO quoteDTO) throws Exception {
        try{
            QuoteModel quoteModel = quoteRepository.findById(1).get();
            quoteModel.setAuthor(quoteDTO.getAuthor());
            quoteModel.setText(quoteDTO.getText());
            quoteRepository.save(quoteModel);



        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
    }
}
