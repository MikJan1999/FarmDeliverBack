package com.example.demo.Controller;

import com.example.demo.model.GeneralAdvertisement;
import com.example.demo.service.GeneralAdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/advert")
public class GeneralAdvertisementControl {
@Autowired
private GeneralAdvertisementService generalAdvertisementService;
    @PostMapping("/add") public GeneralAdvertisement add(@RequestBody GeneralAdvertisement generalAdvertisement){return generalAdvertisementService.add(generalAdvertisement);}
    @GetMapping("/get") public List<GeneralAdvertisement> getAll(){ return  generalAdvertisementService.getAll();}
    @GetMapping("/get/{id}") public Optional<GeneralAdvertisement> getById(@PathVariable("id") Long id){ return generalAdvertisementService.getById(id);}
    @DeleteMapping("/delete/{id}") public void deleteById(@PathVariable("id") Long id) {generalAdvertisementService.deleteById(id);}

    @PutMapping("/edit/{id}") public @ResponseBody
    ResponseEntity<GeneralAdvertisement> editById(@PathVariable("id") Long id, @RequestBody GeneralAdvertisement generalAdvertisement) throws NameNotFoundException {
        Optional<GeneralAdvertisement> generalAdvertisementOptional = this.generalAdvertisementService.getById(id);
        if(generalAdvertisementOptional.isPresent()){
            GeneralAdvertisement newGeneral = generalAdvertisementOptional.get();
            newGeneral.setAdvertisement(generalAdvertisement.getAdvertisement());
//            newGeneral.setDataOfAdding(generalAdvertisement.getDataOfAdding());
            this.generalAdvertisementService.add(newGeneral);
            return ResponseEntity.ok(newGeneral);
        }
        throw new NameNotFoundException("Nie znaleziono z tym id:" + id);
    }


}
