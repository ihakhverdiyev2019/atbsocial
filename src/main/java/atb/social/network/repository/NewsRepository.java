package atb.social.network.repository;

import atb.social.network.model.NewsModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NewsRepository extends CrudRepository<NewsModel,Integer> {

    List<NewsModel> findAll();

    NewsModel findById(int id);


}
