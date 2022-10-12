package com.taka.service;

import com.taka.domain.Dump;
import com.taka.repo.DumpRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DumpService {

    private DumpRepo dumpRepo;

    @Autowired
    public DumpService(DumpRepo dumpRepo) {
        this.dumpRepo = dumpRepo;
    }

    //    view dumps
    public List<Dump> getDumpList(){
        return dumpRepo.findAll();
    }

    public List<Dump> getByMunicipal(String municipal){
        return dumpRepo.findByMunicipal(municipal);
    }

//    save dumps
    public Dump saveDump(Dump dump){
        return dumpRepo.save(dump);
    }

//    update dump
    public Dump updateDump(Long id, Dump dump){
        Dump oldDump = dumpRepo.findById(id).get();
        oldDump.setDumpLatitude(dump.getDumpLatitude());
        oldDump.setDumpLongitude(dump.getDumpLongitude());
        oldDump.setDumpName(dump.getDumpName());

        return dumpRepo.save(oldDump);
    }

//    delete dump
    public void deleteDump(Long id){
        dumpRepo.deleteById(id);
    }
}
