package atb.social.network.controller;

import atb.social.network.model.BankBranchModel;
import atb.social.network.model.BankDepartmenModel;
import atb.social.network.service.BankBranch.BankBranchService;
import atb.social.network.service.BankDepartment.BankDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class BankDepartmentController {










    @Autowired
    private BankDepartmentService bankDepartmentService;



    @RequestMapping(value = "/departments/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllBranches(@PathVariable("id") String id) throws Exception {
        List<BankDepartmenModel> departmentByBranchId = new ArrayList<>();
        System.out.println(id);
        try{

            departmentByBranchId  = bankDepartmentService.getByBranchId(Integer.parseInt(id));
            if (departmentByBranchId.size()==0){
                BankDepartmenModel bankDepartmenModel = new BankDepartmenModel();
                bankDepartmenModel.setAvailableOnBranchId(0);
                bankDepartmenModel.setDepartmentName(null);
                bankDepartmenModel.setId(0);
                departmentByBranchId.add(bankDepartmenModel);
            }


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(departmentByBranchId, HttpStatus.OK);



    }


}
