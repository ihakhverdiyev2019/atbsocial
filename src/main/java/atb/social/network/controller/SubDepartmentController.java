package atb.social.network.controller;

import atb.social.network.model.SubDepartmentModel;
import atb.social.network.service.SubDepartmentService.SubDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class SubDepartmentController {

    @Autowired
    private SubDepartmentService subDepartmentService;




    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/subDepart/{bid}/{did}" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllBranches(@PathVariable("bid") String bid, @PathVariable("did") String did) throws Exception {
        List<SubDepartmentModel> subDepartmentModelList ;
        try{

            subDepartmentModelList  = subDepartmentService.getallSubDepartment(Integer.parseInt(bid),Integer.parseInt(did));


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(subDepartmentModelList, HttpStatus.OK);



    }
}
