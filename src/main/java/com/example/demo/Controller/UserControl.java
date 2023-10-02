package com.example.demo.Controller;


import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



import javax.naming.NameNotFoundException;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/user")
public class UserControl {

    @Autowired
    UserService userService;

    @PostMapping("/add")
    public User add(@RequestBody  User user) {
        System.out.println(user.getRole());
        return  userService.add(user);}

    @GetMapping("/get") public List<User> getAll(){ return  userService.getAll();}
    @GetMapping("/get/{id}") public Optional<User> getById(@PathVariable("id") Long id){ return userService.getById(id);}
    @DeleteMapping("/delete/{id}") public void deleteById(@PathVariable("id") Long id) { userService.deleteById(id);}

    @PutMapping("/edit/{id}")
    public @ResponseBody
    ResponseEntity<User> editById(@PathVariable("id") Long id, @RequestBody User userEdit) throws NameNotFoundException {
        Optional<User> userOptional = this.userService.getById(id);
        if(userOptional.isPresent()){
            User newUser = userOptional.get();
            newUser.setFirstName(userEdit.getFirstName());
            newUser.setLastName(userEdit.getLastName());
            newUser.setEmail(userEdit.getEmail());
            newUser.setPassword(userEdit.getPassword());
            newUser.setRole(userEdit.getRole());

            this.userService.add(newUser);
            return ResponseEntity.ok(newUser);
        }
        throw new NameNotFoundException("Nie znaleziono z tym id:" + id);
    }

}
