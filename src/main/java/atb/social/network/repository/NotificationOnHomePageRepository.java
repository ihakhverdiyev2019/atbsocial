package atb.social.network.repository;

import atb.social.network.model.NotificationOnHomePageModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationOnHomePageRepository extends CrudRepository<NotificationOnHomePageModel,Integer> {


List<NotificationOnHomePageModel> findAllByNotDate(String date);








}
