package atb.social.network.repository;

import atb.social.network.model.MealCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MealCategoryRepository extends CrudRepository<MealCategory,Integer> {
}
