package atb.social.network.service.BankBranch;

import atb.social.network.model.BankBranchModel;
import atb.social.network.model.BankDepartmenModel;
import atb.social.network.repository.BankBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankBranchServiceImpl implements BankBranchService {

    @Autowired
    private BankBranchRepository bankBranchRepository;

    @Override
    public List<BankBranchModel> getAllBranches() throws Exception {
        List<BankBranchModel> bankBranchModels = new ArrayList<>();
        try{
            bankBranchModels = bankBranchRepository.findAll();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return bankBranchModels;
    }

    }

