package atb.social.network.controller;


import atb.social.network.dto.CanteenDto;
import atb.social.network.model.BranchTimeModel;
import atb.social.network.service.BranchTimeService.BranchTimeService;
import atb.social.network.service.CanteenService.CanteenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BranchTimeController {
    @Autowired
    private BranchTimeService branchTimeService;

    @RequestMapping(value = "/branchTime" , method = RequestMethod.GET)
    public ResponseEntity<Object> getBranchTime() throws Exception {
        List<BranchTimeModel> branchTimeModels;
        try{

            branchTimeModels  = branchTimeService.getBranchData();


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(branchTimeModels, HttpStatus.OK);



    }
}
