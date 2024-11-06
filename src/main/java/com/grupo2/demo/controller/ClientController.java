package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.grupo2.demo.model.Client;
import com.grupo2.demo.repository.ClientRepository;

@RestController
@RequestMapping("/api/cliente")
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @GetMapping
    public List<Client> listarClientes() {
        List<Client> usuarios = new ArrayList<>();

        return usuarios;
    }

    @PostMapping
    public Client criarCliente(@RequestBody Client usuario) {
        return clientRepository.save(usuario);
    }
}
