package atb.social.network.service.BankDepartment;

import atb.social.network.model.BankDepartmenModel;

import java.util.List;

public interface BankDepartmentService {

    List<BankDepartmenModel> getAllDepartments() throws Exception;

    List<BankDepartmenModel> getByBranchId(int id) throws Exception;

}
