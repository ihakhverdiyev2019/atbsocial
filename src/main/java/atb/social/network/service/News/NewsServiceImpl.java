package atb.social.network.service.News;

import atb.social.network.model.NewsModel;
import atb.social.network.repository.NewsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    @Autowired
    private NewsRepository newsRepository;


    @Override
    public List<NewsModel> getAllNews() throws Exception {
        List<NewsModel> newsModels;
        try{
            newsModels = newsRepository.findAll();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return newsModels;
    }

    @Override
    public NewsModel getNewsById(int id) throws Exception {
        NewsModel newsModels;
        try{
            newsModels = newsRepository.findById(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return newsModels;
    }
}
