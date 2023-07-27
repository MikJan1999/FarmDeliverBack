package com.example.demo.Controller;

import com.example.demo.Repo.CustomerOrderRepo;
import com.example.demo.model.Address;
import com.example.demo.model.CustomerOrder;
import com.example.demo.service.CustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer_order")
public class CustomerOrderControl {

    @Autowired
    CustomerOrderService customerOrderService;


    @PostMapping("/add") public CustomerOrder add(@RequestBody CustomerOrder customerOrder){return customerOrderService.add(customerOrder);}
    @GetMapping("/get") public List<CustomerOrder> getAll(){ return  customerOrderService.getAll();}
    @GetMapping("/get/{id}") public Optional<CustomerOrder> getById(@PathVariable("id") Long id){ return customerOrderService.getById(id);}
    @DeleteMapping("/delete/{id}") public void deleteById(@PathVariable("id") Long id) {customerOrderService.deleteById(id);}

    @PutMapping("/edit/{id}") public @ResponseBody
    ResponseEntity<CustomerOrder> editById(@PathVariable("id") Long id, @RequestBody CustomerOrder customerOrderEdit) throws NameNotFoundException {
        Optional<CustomerOrder> customerOrderOptional = this.customerOrderService.getById(id);
        if (customerOrderOptional.isPresent()) {
            CustomerOrder newCustomerAddress = customerOrderOptional.get();
            newCustomerAddress.setIsCartShop(customerOrderEdit.getIsCartShop());
            newCustomerAddress.setIsSaled(customerOrderEdit.getIsSaled());
            newCustomerAddress.setAddress(customerOrderEdit.getAddress());
            this.customerOrderService.add(customerOrderEdit);
            return ResponseEntity.ok(customerOrderEdit);
        }
        throw new NameNotFoundException("Nie znaleziono z tym id:" + id);
    }

}




