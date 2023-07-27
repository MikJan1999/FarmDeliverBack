package com.example.demo.Controller;

import com.example.demo.model.Address;
import com.example.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.relation.RoleNotFoundException;
import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/address")
public class AddressControl {

@Autowired
    AddressService addressService;

    @PostMapping("/add") public Address add(@RequestBody Address address){return addressService.add(address);}
    @GetMapping("/get") public List<Address>  getAll(){ return  addressService.getAll();}
    @GetMapping("/get/{id}") public Optional<Address> getById(@PathVariable("id") Long id){ return addressService.getById(id);}
    @DeleteMapping("/delete/{id}") public void deleteById(@PathVariable("id") Long id) {addressService.deleteById(id);}

    @PutMapping("/edit/{id}") public @ResponseBody ResponseEntity<Address> editById(@PathVariable("id") Long id,@RequestBody Address addressEdit) throws NameNotFoundException {
        Optional<Address> addressOptional = this.addressService.getById(id);
        if(addressOptional.isPresent()){
            Address newAdress = addressOptional.get();
            newAdress.setNameAndSurname(addressEdit.getNameAndSurname());
            newAdress.setStreet(addressEdit.getStreet());
            newAdress.setNumberOfPhone(addressEdit.getNumberOfPhone());
            newAdress.setVillage(addressEdit.getVillage());
            newAdress.setNumberOfPhone(addressEdit.getNumberOfPhone());
            this.addressService.add(newAdress);
            return ResponseEntity.ok(newAdress);
        }
        throw new NameNotFoundException("Nie znaleziono z tym id:" + id);
    }

}




