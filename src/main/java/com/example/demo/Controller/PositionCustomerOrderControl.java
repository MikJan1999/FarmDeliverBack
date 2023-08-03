package com.example.demo.Controller;


import com.example.demo.model.PositionCustomerOrder;
import com.example.demo.service.PositionCustomerOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/pco")
public class PositionCustomerOrderControl {
@Autowired
PositionCustomerOrderService positionCustomerOrderService;

    @PostMapping("/add") public PositionCustomerOrder add(@RequestBody PositionCustomerOrder positionCustomerOrder){return positionCustomerOrderService.add(positionCustomerOrder);}
    @GetMapping("/get") public List<PositionCustomerOrder>  getAll(){ return  positionCustomerOrderService.getAll();}
    @GetMapping("/get/{id}") public Optional<PositionCustomerOrder> getById(@PathVariable("id") Long id){ return positionCustomerOrderService.getById(id);}
    @DeleteMapping("/delete/{id}") public void deleteById(@PathVariable("id") Long id) {positionCustomerOrderService.deleteById(id);}

    @PutMapping("/edit/{id}") public @ResponseBody ResponseEntity<PositionCustomerOrder> editById(@PathVariable("id") Long id,@RequestBody PositionCustomerOrder positionCustomerOrderEdit) throws NameNotFoundException {
        Optional<PositionCustomerOrder> pcoOptional = this.positionCustomerOrderService.getById(id);
        if(pcoOptional.isPresent()){
            PositionCustomerOrder newPco = pcoOptional.get();
            newPco.setAmount(positionCustomerOrderEdit.getAmount());
        newPco.setIsAccepted(positionCustomerOrderEdit.getIsAccepted());
        newPco.setIsDelivered(positionCustomerOrderEdit.getIsDelivered());
//        newPco.setProduct(positionCustomerOrderEdit.getProduct());
        newPco.setCustomerOrder(positionCustomerOrderEdit.getCustomerOrder());

            this.positionCustomerOrderService.add(newPco);
            return ResponseEntity.ok(newPco);
        }
        throw new NameNotFoundException("Nie znaleziono z tym id:" + id);

}
@GetMapping("/get_by_product_id/{id}")
    public Optional<List<PositionCustomerOrder>> getByProductId(@PathVariable("id") Long id){
        return Optional.ofNullable(positionCustomerOrderService.findByProductId(id));
}

@GetMapping("/get_by_pco/{id}")
    public Optional<List<PositionCustomerOrder>>getByCustomerOrderId(@PathVariable("id")Long id){
        return Optional.ofNullable(positionCustomerOrderService.getByCustomerOrderId(id));
}
}











