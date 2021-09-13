package atb.social.network.controller;


import atb.social.network.model.BankBranchModel;
import atb.social.network.service.BankBranch.BankBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BankBranchController {


    @Autowired
    private BankBranchService bankBranchService;



    @RequestMapping(value = "/branches" , method = RequestMethod.GET)
    public ResponseEntity<Object> getAllBranches() throws Exception {
        List<BankBranchModel> allBranches = new ArrayList<>();
        try{

            allBranches  = bankBranchService.getAllBranches();


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity(allBranches, HttpStatus.OK);



    }




}
