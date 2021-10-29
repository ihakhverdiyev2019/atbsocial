package atb.social.network.repository;

import atb.social.network.model.EmployeeModel;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Set;

@Repository
public interface EmployeeRepository extends CrudRepository<EmployeeModel,Integer> {


    List<EmployeeModel> findAllByBranchIdAndDepartmentId(int bId, int dId);

    EmployeeModel findById(int id);


    Set<EmployeeModel> findAll();

    List<EmployeeModel> findAllByFilterBirth(String birthday);

    EmployeeModel findByEmailIgnoreCase(String email);








}

