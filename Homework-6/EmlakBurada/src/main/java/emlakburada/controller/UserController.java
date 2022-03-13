package main.java.emlakburada.controller;

import java.util.List;

import emlakburada.dto.AdvertRequest;
import emlakburada.dto.response.AdvertResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import emlakburada.dto.UserRequest;
import emlakburada.dto.response.UserResponse;
import emlakburada.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/users")
    public ResponseEntity<UserResponse> saveUser(@RequestBody UserRequest request) {

        return new ResponseEntity<>(userService.saveUser(request), HttpStatus.CREATED);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> findAll() {
        return new ResponseEntity<>(userService.findAll(), HttpStatus.CREATED);

    }

    @PutMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> updateUserById(@RequestBody UserRequest request, @PathVariable int id) {
        return new ResponseEntity<>(userService.updateUserById(request, id), HttpStatus.CREATED);
    }

    @DeleteMapping(value = "/users/{id}")
    public ResponseEntity<String> deleteUserById(@PathVariable int id) {
        return new ResponseEntity<>(userService.deleteAdvertById(id), HttpStatus.CREATED);
    }


    @GetMapping(value = "/users/{id}")
    public ResponseEntity<UserResponse> findByUserId(@PathVariable(required = false) int id) {
        return new ResponseEntity<>(userService.findByUserId(id), HttpStatus.OK);
    }

}
