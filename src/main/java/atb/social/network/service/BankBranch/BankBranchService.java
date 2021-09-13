package atb.social.network.service.BankBranch;

import atb.social.network.model.BankBranchModel;

import java.util.List;

public interface BankBranchService {

    List<BankBranchModel> getAllBranches() throws Exception;

}
