package atb.social.network.service.BranchTimeService;


import atb.social.network.model.BranchTimeModel;
import atb.social.network.repository.BranchTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchTimeServiceImpl implements BranchTimeService{

    @Autowired
    private BranchTimeRepository branchTimeRepository;


    @Override
    public List<BranchTimeModel> getBranchData() throws Exception {
        List<BranchTimeModel> branchTimeModels;
        try{

            branchTimeModels = branchTimeRepository.findAll();

        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return branchTimeModels;
    }
}
