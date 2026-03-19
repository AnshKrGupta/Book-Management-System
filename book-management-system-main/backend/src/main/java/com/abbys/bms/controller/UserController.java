package com.abbys.bms.controller;

import com.abbys.bms.data.user.dto.CreateUserRequest;
import com.abbys.bms.data.user.dto.UserResponse;
import com.abbys.bms.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService _service;

    @PostMapping
    @PreAuthorize("hasRole('CEO')")
    public ResponseEntity<UserResponse> createUser(@Valid @RequestBody CreateUserRequest request) {
        UserResponse res = _service.createUser(request);
        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('CEO')")
    public ResponseEntity<UserResponse> updateUser(@Valid @RequestBody CreateUserRequest request, @PathVariable  Long id) {
        UserResponse res = _service.updateUser(id,request);
        return new ResponseEntity<>(res, HttpStatus.OK);
    }


    @GetMapping
    public ResponseEntity<List<UserResponse>> getAllUsersByRole(@RequestParam  String role) {
        List<UserResponse> users = _service.getUsersByRole(role);
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable  Long id) {
        UserResponse user = _service.getUserById(id);
        return new ResponseEntity<>(user,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('CEO')")
    public ResponseEntity deleteUserById(@PathVariable  Long id) {
        if(_service.deleteUserById(id)) {
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
