package com.gbs.software.backend.controllers;

import com.gbs.software.backend.services.UserService;
import com.gbs.software.model.dto.UserDTO;
import com.gbs.software.model.entities.User;
import org.atmosphere.config.service.Get;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @GetMapping
    public List<UserDTO> findAll(){
        List<User> userList =  service.findAll();
        User user = new User();
        return user.toDTOList(userList);
    }

    @GetMapping("{id}")
    public UserDTO findById(@PathVariable final Long id){
        return service.findById(id);
    }

    @PostMapping
    public UserDTO registrar(@RequestBody @Valid final UserDTO dto) {
        return service.registrar(dto);
    }

    @DeleteMapping("{$id}")
    public void deletar(@PathVariable final Long id){
        service.deletar(id);
    }
}
