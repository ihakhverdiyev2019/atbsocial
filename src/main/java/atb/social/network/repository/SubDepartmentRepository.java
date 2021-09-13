package atb.social.network.repository;

import atb.social.network.model.SubDepartmentModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubDepartmentRepository extends CrudRepository<SubDepartmentModel,Integer> {
}
