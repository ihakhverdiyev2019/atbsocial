package atb.social.network.service.BranchTimeService;

import atb.social.network.dto.BranchTimeDTO;
import atb.social.network.model.BranchTimeModel;

import java.util.List;

public interface BranchTimeService {

    List<BranchTimeModel> getBranchData() throws Exception;


  void  saveBranchData(BranchTimeDTO branchTimeDTO) throws Exception;


}
