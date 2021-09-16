package atb.social.network.service.SubDepartmentService;


import atb.social.network.model.SubDepartmentModel;
import atb.social.network.repository.SubDepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubDepartmentServiceImpl implements SubDepartmentService{

    @Autowired
    private SubDepartmentRepository subDepartmentRepository;

    @Override
    public SubDepartmentModel getSubServiceData(int id) throws Exception {
        SubDepartmentModel subDepartmentModel=null;
        try {
            subDepartmentModel= subDepartmentRepository.findById(id).get();

        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return subDepartmentModel;
    }

    @Override
    public List<SubDepartmentModel> getallSubDepartment(int branchId, int departId) throws Exception {
        List<SubDepartmentModel> subDepartmentModels;
        try {
            subDepartmentModels= subDepartmentRepository.findAllByBranchIdAndDepartId(branchId,departId);

        }catch (Exception e){
            throw  new Exception(e.getMessage());
        }
        return subDepartmentModels;
    }


}
