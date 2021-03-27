package com.gibittec.swagertest.app.resource;

import com.gibittec.swagertest.app.model.User;
import com.gibittec.swagertest.app.resource.dto.UserDto;
import com.gibittec.swagertest.app.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/demo/user")
@Api(tags = "user") // indica que este modulo es modulo de user
public class UserResource {

    private final Logger log = LoggerFactory.getLogger(UserResource.class);
    private final UserService userService;
    private ModelMapper modelMapper;

    public UserResource(UserService userService, ModelMapper modelMapper) {
        this.userService = userService;
        this.modelMapper = modelMapper;
    }

    @PostMapping
    @ApiOperation(value = "Create User", notes = "Endpoint to create new user")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Created Succesfull"),
            @ApiResponse(code = 400, message = "Invalid Request"),
    })
    public ResponseEntity<User> createUser(@RequestBody UserDto userDto){
        User user = this.modelMapper.map(userDto, User.class);
        return new ResponseEntity<User>(this.userService.createUser(user), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "Update User", notes = "Endpoint to updateuser")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Updated Succesfull"),
            @ApiResponse(code = 404, message = "User Not Found"),
    })
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody UserDto userDto){
        User user = this.userService.findById(id);
        if(user == null){
            log.error("User not Faound");
            return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
        }
        user.setEmail(userDto.getEmail());
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        return new ResponseEntity<User>(this.userService.updateUser(user), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete User", notes = "Endpoint to Delete User")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Deleted Succesfull"),
            @ApiResponse(code = 404, message = "User Not Found"),
    })
    public void removeUser(@PathVariable("id") Integer id){
        User user = this.userService.findById(id);
        if(user != null){
            this.userService.deleteUser(user);
        }
    }

    @GetMapping
    @ApiOperation(value = "List User", notes = "Endpoint to List All Users")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "User Faound Succesfull"),
            @ApiResponse(code = 404, message = "Users Not Found"),
    })
    public  ResponseEntity<List<User>> findAll(){
        return ResponseEntity.ok(this.userService.findAll());
    }


}
