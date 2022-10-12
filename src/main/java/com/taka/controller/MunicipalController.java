package com.taka.controller;

import com.taka.domain.Municipal;
import com.taka.service.MunicipalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/municipal")
public class MunicipalController {

    @Autowired
    private MunicipalService municipalService;

//    @PostMapping("/")
//    public Municipal newMunicipal(@RequestBody Municipal municipal){
//        return municipalService.saveMunicipal(municipal);
//    }

    @GetMapping("/all")
    public List<Municipal> viewMunicipal(){
        return municipalService.getAllMunicipals();
    }

    @PutMapping("/{name}")
    public Municipal updateMunicipal(@PathVariable String name, @RequestBody Municipal muni){
        return municipalService.update(name, muni);
    }
}
