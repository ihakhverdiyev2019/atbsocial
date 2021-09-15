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
    public List<NewsModel> getAllNews(int count,int page) throws Exception {
        List<NewsModel> newsModelsFilter=new ArrayList<>();

        try{
            List<NewsModel> newsModels = newsRepository.findAll();
            int lastnum;
            if(page*count>newsModels.size()){
                lastnum=newsModels.size();
            }else {
                lastnum = page*count;
            }

            for(int i = (page-1)*count ; i<lastnum ; i++){

                newsModelsFilter.add(newsModels.get(i));

            }



        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return newsModelsFilter;
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
