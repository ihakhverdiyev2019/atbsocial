package atb.social.network.service.QuoteService;


import atb.social.network.dto.CounterDTO;
import atb.social.network.dto.QuoteDTO;
import atb.social.network.model.CounterModel;
import atb.social.network.model.QuoteModel;
import atb.social.network.repository.CounterRepository;
import atb.social.network.repository.QuoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class QuoteServiceImpl implements QuoteService{

    @Autowired
    private QuoteRepository quoteRepository;

    @Autowired
    private CounterRepository counterRepository;


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

    @Override
    public void saveIp(String ip) {
        CounterModel counterModel = new CounterModel();


        counterModel.setIp(ip);
        String pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());
        counterModel.setDate(date);

        counterRepository.save(counterModel);


    }

    @Override
    public CounterDTO getUniqueIp() {
        CounterDTO counterDTO = new CounterDTO();
        String pattern = "yyyyMMdd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);

        String date = simpleDateFormat.format(new Date());

        List<CounterModel> counterModels = counterRepository.findAllByDate(date);

        Set<String> ips = new HashSet<>();
        for(final CounterModel co: counterModels) {
            ips.add(co.getIp());
        }
        counterDTO.setHits(counterModels.size());
        counterDTO.setHosts(ips.size());

        return counterDTO;
    }
}
