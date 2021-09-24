package atb.social.network.service.CanteenService;

import atb.social.network.dto.CanteenDto;
import atb.social.network.dto.CanteenRequestDTO;
import atb.social.network.dto.CategoryDTO;
import atb.social.network.model.MealCategory;

import java.util.List;

public interface CanteenService {


    List<CanteenDto> getCanteenData() throws Exception;

    List<MealCategory> getCategory( ) throws  Exception;

    void saveMeal(CanteenRequestDTO canteenRequestDTO) throws Exception;

    void editMeal(int id,CanteenRequestDTO canteenRequestDTO) throws Exception;

    void removeMeal(int id) throws  Exception;


    void saveCategory(CategoryDTO categoryDTO) throws Exception;

    void removeCategory(int id) throws Exception;

    void editCategory(CategoryDTO categoryDTO, int id) throws Exception;





}
