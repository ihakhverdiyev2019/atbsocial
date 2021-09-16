package atb.social.network.service.SubDepartmentService;

import atb.social.network.model.SubDepartmentModel;

import java.util.List;

public interface SubDepartmentService {

    SubDepartmentModel getSubServiceData(int id) throws Exception;

    List<SubDepartmentModel> getallSubDepartment(int branchId, int departId) throws Exception;



}
