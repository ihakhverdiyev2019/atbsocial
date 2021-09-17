package atb.social.network.controller;


import atb.social.network.dto.BranchDto;
import atb.social.network.model.BankBranchModel;
import atb.social.network.service.BankBranch.BankBranchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BankBranchController {


    @Autowired
    private BankBranchService bankBranchService;


    @CrossOrigin(origins = "*", allowedHeaders = "*")
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


    @CrossOrigin(origins = "*",allowedHeaders = "*")
    @RequestMapping(value = "/branches/save" , method = RequestMethod.POST)
    public ResponseEntity<Object> saveBranch(@RequestBody BranchDto branchDto) throws Exception {

        try{

            bankBranchService.save(branchDto);


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("Done", HttpStatus.OK);



    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @RequestMapping(value = "/branches/edit/{id}" , method = RequestMethod.POST)
    public ResponseEntity<Object> editBranch(@PathVariable String id, @RequestBody BranchDto branchDto) throws Exception {

        try{

            bankBranchService.edit(branchDto,Integer.parseInt(id));


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("Done", HttpStatus.OK);



    }


    @RequestMapping(value = "/branches/remove/{id}" , method = RequestMethod.GET)
    public ResponseEntity<Object> removeBranch(@PathVariable String id) throws Exception {

        try{

            bankBranchService.remove(Integer.parseInt(id));


        }catch (Exception e){
            throw new Exception(e.getMessage());
        }

        return new ResponseEntity("Done", HttpStatus.OK);



    }




}
