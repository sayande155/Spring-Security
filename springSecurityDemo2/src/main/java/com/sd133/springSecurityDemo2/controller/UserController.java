
package com.sd133.springSecurityDemo2.controller;

import com.sd133.springSecurityDemo2.dto.UserDto;
import com.sd133.springSecurityDemo2.model.User;
import com.sd133.springSecurityDemo2.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("register")
    public ResponseEntity<String> doRegister(@Valid @RequestBody UserDto userDto) {
        User user = new User(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getContact(),
                userDto.getRole()
        );

        return userService.doRegister(user);
    }

    @PutMapping("updateUser")
    public ResponseEntity<String> doLogin(@RequestBody UserDto userDto){
        User user = new User(
                userDto.getUsername(),
                userDto.getPassword(),
                userDto.getEmail(),
                userDto.getContact(),
                userDto.getRole()
        );

        return userService.doUpdate(user);
    }

//    @PostMapping("login")
//    public ResponseEntity<String> doLogin(){
//        return userService.doLogin();
//    }
}
