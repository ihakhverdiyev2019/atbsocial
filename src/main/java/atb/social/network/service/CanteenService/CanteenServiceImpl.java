package atb.social.network.service.CanteenService;


import atb.social.network.dto.CanteenDto;
import atb.social.network.model.MealModel;
import atb.social.network.repository.MealCategoryRepository;
import atb.social.network.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Service
public class CanteenServiceImpl implements CanteenService{

    @Autowired
    private MealCategoryRepository mealCategoryRepository;

    @Autowired
    private MealRepository mealRepository;


    @Override
    public List<CanteenDto> getCanteenData() throws Exception {
        List<CanteenDto> canteenDtos = new ArrayList<>();
        List<Integer> categoryId = new ArrayList<>();


        try{

            List<MealModel> mealModels = mealRepository.findAll();

            for(int i =0;i<mealModels.size();i++){

                categoryId.add(mealModels.get(i).getCategoryId());
            }

            HashSet<Integer> hset = new HashSet<>(categoryId);
            System.out.println(hset.size());
            System.out.println(hset);

            for(Integer strNumber : hset) {
                CanteenDto canteenDto = new CanteenDto();
                canteenDto.setCategory(mealCategoryRepository.findById(strNumber).get().getCategory());
                List<MealModel> mealModels1 = mealRepository.findAllByCategoryId(strNumber);
                canteenDto.setMealModels(mealModels1);
                canteenDtos.add(canteenDto);


            }




        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }


        return canteenDtos;
    }
}
