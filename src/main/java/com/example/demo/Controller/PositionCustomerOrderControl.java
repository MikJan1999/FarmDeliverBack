package com.example.demo.Controller;


import com.example.demo.Repo.PositionCustomerOrderRepo;
import com.example.demo.model.*;
import com.example.demo.service.CartShopService;
import com.example.demo.service.CustomerOrderService;
import com.example.demo.service.PositionCustomerOrderService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/pco")
public class PositionCustomerOrderControl {
@Autowired
PositionCustomerOrderService positionCustomerOrderService;
@Autowired
CartShopService cartShopService;
@Autowired
    PositionCustomerOrderRepo positionCustomerOrderRepo;
@Autowired
    UserService userService;
@Autowired
    CustomerOrderService customerOrderService;

    @PostMapping("/add") public PositionCustomerOrder add(@RequestBody PositionCustomerOrder positionCustomerOrder){return positionCustomerOrderService.add(positionCustomerOrder);}


    @GetMapping("/get") public List<PositionCustomerOrder>  getAll(){

        return  positionCustomerOrderService.getAll();}



    @GetMapping("/get/{id}") public Optional<PositionCustomerOrder> getById(@PathVariable("id") Long id){ return positionCustomerOrderService.getById(id);}

    @DeleteMapping("/delete/{id}") public void deleteById(@PathVariable("id") Long id) {
        positionCustomerOrderService.deleteById(id);
    positionCustomerOrderService.getAll();
    }

    @PutMapping("/edit/{id}") public @ResponseBody ResponseEntity<PositionCustomerOrder> editById(@PathVariable("id") Long id,@RequestBody PositionCustomerOrder positionCustomerOrderEdit) throws NameNotFoundException {
        Optional<PositionCustomerOrder> pcoOptional = this.positionCustomerOrderService.getById(id);
        if(pcoOptional.isPresent()){
            PositionCustomerOrder newPco = pcoOptional.get();
            newPco.setAmount(positionCustomerOrderEdit.getAmount());
//        newPco.setProduct(positionCustomerOrderEdit.getProduct());
//        newPco.setCustomerOrder(positionCustomerOrderEdit.getCustomerOrder());

            this.positionCustomerOrderService.add(newPco);
            return ResponseEntity.ok(newPco);
        }
        throw new NameNotFoundException("Nie znaleziono z tym id:" + id);

}
@GetMapping("/get_by_product_id/{id}")
    public Optional<List<PositionCustomerOrder>> getByProductId(@PathVariable("id") Long id){
        return Optional.ofNullable(positionCustomerOrderService.findByProductId(id));
}

@GetMapping("/get_by_co/{id}")
    public Optional<List<PositionCustomerOrder>>getByCustomerOrderId(@PathVariable("id")Long id){
        return Optional.ofNullable(positionCustomerOrderService.findByCustomerOrderId(id));
}
//podając użytkownika id  i pozycje zamówienia dodajemhy pozycje do zamówienia określonego użytkownika
@PostMapping("/add_and_add/{userId}")
    public Optional<PositionCustomerOrder> addPositionAndAddToCart(@RequestBody PositionCustomerOrder positionCustomerOrder, @PathVariable("userId")Long userId){
    CartShop cartsByUserId = cartShopService.findCartsByUserId(userId);
    System.out.println(cartsByUserId);
    positionCustomerOrder.setCartShop(cartsByUserId);
        positionCustomerOrderService.add(positionCustomerOrder);
        return Optional.of(positionCustomerOrder);
    }

    @GetMapping("/get-with-product-name")
    public List<Object[]> getAllWithProductName() {
        return positionCustomerOrderService.getAllWithProductName();
    }

    @GetMapping("/get-with-product-name-by-orderId/{customer_order_id}")
    public List<Object[]> getAllWithProductNameByCartShop(Long customer_order_id) {
        return positionCustomerOrderService.getAllWithProductNameByOrderId(customer_order_id);
    }

    @GetMapping("/{positionId}")
    public ResponseEntity<PositionCustomerOrderWithProductNameDTO> getPositionCustomerOrderWithProductName(@PathVariable Long positionId) {
        PositionCustomerOrder positionCustomerOrder = positionCustomerOrderService.getPositionCustomerOrderById(positionId);

        if (positionCustomerOrder == null) {
            return ResponseEntity.notFound().build();
        }

        PositionCustomerOrderWithProductNameDTO dto = new PositionCustomerOrderWithProductNameDTO(positionCustomerOrder);
        return ResponseEntity.ok(dto);
    }
    @GetMapping("/alll/{userId}")
    public ResponseEntity<List<PositionCustomerOrderWithProductNameDTO>> getAllPositionCustomerOrdersWithProductNamesByUserId(@PathVariable Long userId) {
       CartShop findCartsByUserId = cartShopService.findCartsByUserId(userId);
        Long cartId = findCartsByUserId.getId();
        List<PositionCustomerOrder> positionCustomerOrders = positionCustomerOrderService.findPCOByCartShopId(cartId);
        if (positionCustomerOrders == null || positionCustomerOrders.isEmpty()) {
            return ResponseEntity.notFound().build();}
        List<PositionCustomerOrderWithProductNameDTO> dtos = positionCustomerOrders.stream()
                .map(PositionCustomerOrderWithProductNameDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
    /* tworzę nowe zamówienie dla tego użytkownika i przenoszę wszystkie pozycje
    które były przypisane dla koszyka danego użytkowniak do zamówienia a koszyk opróżniam */

    @PostMapping("/create_order/{userId}")
    public  List<PositionCustomerOrder> createOrder(@RequestBody CustomerOrder customerOrder ,@PathVariable Long userId){
        User findByIdUser = userService.getById(userId).orElseThrow();
        CartShop findCartsByUserId = cartShopService.findCartsByUserId(userId);
        Long cartId = findCartsByUserId.getId();
        List<PositionCustomerOrder> positionCustomerOrders = positionCustomerOrderService.findPCOByCartShopId(cartId);
customerOrder.setUser(findByIdUser);
customerOrderService.add(customerOrder);
        for (PositionCustomerOrder cartItem : positionCustomerOrders) {
            cartItem.setCustomerOrder(customerOrder);
        }
        positionCustomerOrderRepo.saveAll(positionCustomerOrders);
        for(PositionCustomerOrder cartItem:positionCustomerOrders){
            cartItem.setCartShop(null);
        }
        positionCustomerOrderRepo.saveAll(positionCustomerOrders);
return positionCustomerOrders;
    }



    //wyświetla pozycje które należą do określonego koszyka
@GetMapping("/get_all_by_cart_shop_id/{cartShopId}")
    public List<PositionCustomerOrder> getByCartShopId(@PathVariable Long cartShopId){
        return  positionCustomerOrderService.findPCOByCartShopId(cartShopId);
    }


//użyłem do wyświetlenia listy pozycji po naciśnięciu zamówienia
    @GetMapping("/history_order_by_customer_order/{customer_orderId}")
    public ResponseEntity<List<PositionCustomerOrderWithProductNameDTO>> getAllPositionCustomerOrdersWithProductNamesByOrderId(@PathVariable Long customer_orderId) {
        List<PositionCustomerOrder> positionCustomerOrders = positionCustomerOrderService.findByCustomerOrderId(customer_orderId);
        if (positionCustomerOrders == null || positionCustomerOrders.isEmpty()) {
            return ResponseEntity.notFound().build();}
        List<PositionCustomerOrderWithProductNameDTO> dtos = positionCustomerOrders.stream()
                .map(PositionCustomerOrderWithProductNameDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }



}











