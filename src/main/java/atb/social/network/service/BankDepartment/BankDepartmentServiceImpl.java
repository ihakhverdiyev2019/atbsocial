package atb.social.network.service.BankDepartment;


import atb.social.network.model.BankDepartmenModel;
import atb.social.network.repository.BankDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BankDepartmentServiceImpl implements BankDepartmentService {

    @Autowired
    private BankDepartmentRepository bankDepartmentRepository;


    @Override
    public List<BankDepartmenModel> getAllDepartments() throws Exception {
        List<BankDepartmenModel> bankDepartmenModels = new ArrayList<>();
        try{
        bankDepartmenModels = bankDepartmentRepository.findAll();
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return bankDepartmenModels;
    }

    @Override
    public List<BankDepartmenModel> getByBranchId(int id) throws Exception {
        List<BankDepartmenModel> bankDepartmenModels = new ArrayList<>();
        try{
            bankDepartmenModels = bankDepartmentRepository.findAllByAvailableOnBranchId(id);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
        return bankDepartmenModels;
    }
}
