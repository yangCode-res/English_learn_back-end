package com.yang.english.controller;

import com.yang.english.entiy.Response;
import com.yang.english.entiy.ResponseData;
import com.yang.english.entiy.User;
import com.yang.english.service.TokenService;
import com.yang.english.service.UserService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class UserController  {
  @Autowired
  private UserService userService;
  @Autowired
  private  TokenService tokenService;

  @PostMapping("/login")
  public Response login(@RequestBody UserLoginRequest loginRequest){
    if (userService.validateUser(loginRequest.getUsername(),loginRequest.getPassword())) {
      String token = tokenService.createToken(loginRequest.getUsername());
      return new Response("ok",new ResponseData(token));
    } else {
      return new Response("error",new ResponseData("Invalid username or password"));

    }
  }
@GetMapping("/getinfo/{username}")
public ResponseEntity<User> getUserInfo(@PathVariable String username){
  User user=userService.LookUserByName(username);
  return  ResponseEntity.ok(user);
}
  @PostMapping("/signup")
  public ResponseEntity<User> registerUser(@RequestBody User user){
    User registereUser=userService.registerUser(user);
    return  ResponseEntity.ok(registereUser);
  }

  @GetMapping("/searchUser/{id}")
  public ResponseEntity<User> searchUser(@PathVariable int id){
    User user=userService.LookUserByID(id);
    return  ResponseEntity.ok(user);
  }

  @PutMapping("/updateUser/{id}")
  public ResponseEntity<User> updateUser(@PathVariable(value = "id") int userId, @RequestBody User userDetails) {
    User updatedUser = userService.updateUser(userId, userDetails);
    return ResponseEntity.ok(updatedUser);
  }
  public static  class UserLoginRequest {
    private String username;
    private String password;

    // 构造器、Getter 和 Setter
    public UserLoginRequest() {}

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

    public String getPassword() {
      return password;
    }

    public void setPassword(String password) {
      this.password = password;
    }
  }

}
