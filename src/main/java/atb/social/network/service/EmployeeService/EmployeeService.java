package atb.social.network.service.EmployeeService;

import atb.social.network.dto.EmployeeDto;
import atb.social.network.model.EmployeeModel;

import java.util.List;

public interface EmployeeService {

    List<EmployeeModel> getEmployeeBrief(int branchId, int departmentId) throws Exception;

    EmployeeDto getEmployeeDetails(int employeeId) throws Exception;

}
