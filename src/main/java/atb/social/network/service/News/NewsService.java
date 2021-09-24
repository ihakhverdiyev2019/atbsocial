package atb.social.network.service.News;

import atb.social.network.dto.NewsDTO;
import atb.social.network.model.NewsModel;

import java.util.List;

public interface NewsService {

    List<NewsModel> getAllNews(int count,int page) throws Exception;

    NewsModel getNewsById(int id) throws Exception;

    void saveNews(NewsDTO newsDTO) throws Exception;

    void editNews(int id, NewsDTO newsDTO) throws Exception;

    void removeNews(int id) throws Exception;


}
