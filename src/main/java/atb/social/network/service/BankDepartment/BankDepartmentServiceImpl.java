package atb.social.network.service.BankDepartment;


import atb.social.network.dto.DepartmentDto;
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

    @Override
    public void save(DepartmentDto departmentDto) throws Exception {
        try{
            BankDepartmenModel bankDepartmenModel = new BankDepartmenModel();
            bankDepartmenModel.setDepartmentName(departmentDto.getDepartmenName());
            bankDepartmenModel.setAvailableOnBranchId(departmentDto.getBranchId());

           bankDepartmentRepository.save(bankDepartmenModel);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void edit(DepartmentDto departmentDto, int id) throws Exception {
        try{

            BankDepartmenModel bankDepartmenModel = bankDepartmentRepository.findById(id).get();
            bankDepartmenModel.setDepartmentName(departmentDto.getDepartmenName());
            bankDepartmenModel.setAvailableOnBranchId(departmentDto.getBranchId());

            bankDepartmentRepository.save(bankDepartmenModel);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void remove(int id) throws Exception {
        try{
            BankDepartmenModel bankDepartmenModel = bankDepartmentRepository.findById(id).get();

        bankDepartmentRepository.delete(bankDepartmenModel);
        }catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }
}
