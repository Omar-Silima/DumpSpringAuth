package com.taka.controller;

import com.taka.domain.Dump;
import com.taka.service.DumpService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/dump")
public class DumpController {
    private DumpService dumpService;

    public DumpController(DumpService dumpService) {
        this.dumpService = dumpService;
    }

    //    list of dumps registered
    @GetMapping("/all")
    public List<Dump> getAllDumps(){
        return  dumpService.getDumpList();
    }

    @GetMapping("/{municipal}")
    public List<Dump> getByMunicipal(@PathVariable String municipal){
        return  dumpService.getByMunicipal(municipal);
    }

//    add new dump
    @PostMapping("/new")
    public Dump addNewDump(@RequestBody Dump dump){
        return dumpService.saveDump(dump);
    }

//    update dump
    @PutMapping("/{id}")
    public ResponseEntity<Dump> editDump(@PathVariable Long id, @RequestBody Dump dump){
        Dump uDump = dumpService.updateDump(id, dump);
        return new ResponseEntity<Dump>(uDump, HttpStatus.OK);
    }

//    delete dump
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDump(@PathVariable Long id){
        dumpService.deleteDump(id);
        return new ResponseEntity<>("Deleted success", HttpStatus.OK);
    }
}
