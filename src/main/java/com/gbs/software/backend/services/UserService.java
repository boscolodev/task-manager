package com.gbs.software.backend.services;

import com.gbs.software.backend.repositories.UserRepository;
import com.gbs.software.exceptions.DatabaseException;
import com.gbs.software.exceptions.ResourceNotFoundException;
import com.gbs.software.model.dto.UserDTO;
import com.gbs.software.model.entities.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public List<User> findAll() {
        return repository.findAll();
    }

    public UserDTO findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Registro não encontrado")).toDTO();
    }

    public Boolean findByEmail(String email) {
        return repository.findByEmail(email);
    }

    public UserDTO registrar(final UserDTO dto) {

        if (Boolean.TRUE.equals(findByEmail(dto.getEmail()))) {
            throw new DatabaseException("Email já cadastrado");
        }

        return repository.save(dto.toEntity()).toDTO();

    }

    public void deletar(final Long id){
        repository.deleteById(id);
    }
}
