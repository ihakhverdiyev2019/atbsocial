package atb.social.network.service.PositionService;

import atb.social.network.model.PositionModel;

import java.util.List;

public interface PositionService {

    List<PositionModel> getAllPosition(int branchId,int departId,int subDepartId) throws Exception;

    void savePosition(PositionModel positionModel) throws Exception;

}
