package com.example.userapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.userapp.UserRepository;
import com.example.userapp.User;

@RestController
@RequestMapping
public class UserController {
    @Autowired
    public UserRepository userRepository;

    // Add New User
    @PostMapping
    public User addUser(@RequestBody User user)
    {
        User user1= new User();
        user1.setUserName(user.getUserName());
        user1.setUserEmail(user.getUserEmail());
        user1.setAge(user.getAge());
        user1.setGender(user.getGender());
        user1.setNationality(user.getNationality());
        return user1;
    }

    // View User
    @GetMapping("/viewAll")
    public @ResponseBody Iterable<User> getAllUsers()
    {
        return userRepository.findAll();

    }

    //Update
    @PutMapping("/update/{id}")
    public String updateUser(@RequestBody User updateUser,@PathVariable("id") Integer id)
    {
        User user = userRepository.findById(id).orElse(null);
        if (user!=null){
            user.setUserName(updateUser.getUserName());
            user.setUserEmail(updateUser.getUserEmail());
            user.setGender(updateUser.getGender());
            user.setAge(updateUser.getAge());
            user.setNationality(updateUser.getNationality());
        }
        return null;
    }



    //Delete All
    @DeleteMapping
    public String deleteUser(@PathVariable("id") Integer id)
    {
        userRepository.deleteById(id);
        return "User has been successfully deleted ." ;
    }



}
