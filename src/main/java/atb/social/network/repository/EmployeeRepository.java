package atb.social.network.repository;

import atb.social.network.model.EmployeeModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeModel,Integer> {


    List<EmployeeModel> findAllByBranchIdAndDepartmentId(int bId, int dId);

    EmployeeModel findById(int id);
    List<EmployeeModel> findAll();

    List<EmployeeModel> findAllByFilterBirth(String birthday);








}

