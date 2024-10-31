package com.grupo2.demo.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.grupo2.demo.model.Cliente;

@RestController
@RequestMapping("/api/cliente")
public class ClienteController {

    @Autowired
    private UserRepository acao;

    @GetMapping
    public List<Cliente> listarClientes() {
        List<Cliente> usuarios = new ArrayList<>();
        usuarios.add(new Cliente(1L, "João", "joao@example.com"));
        usuarios.add(new Cliente(2L, "Maria", "maria@example.com"));
        return usuarios;
    }

    @PostMapping
    public Cliente criarCliente(@RequestBody Cliente usuario) {
        return acao.save(usuario);
    }
}
