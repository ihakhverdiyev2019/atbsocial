package atb.social.network.service.News;

import atb.social.network.model.NewsModel;

import java.util.List;

public interface NewsService {

    List<NewsModel> getAllNews(int count,int page) throws Exception;

    NewsModel getNewsById(int id) throws Exception;


}
