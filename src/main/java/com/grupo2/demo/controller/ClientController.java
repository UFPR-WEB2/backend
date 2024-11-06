package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        return clientRepository.findAll();
    }

    @PostMapping
    public Client criarCliente(@RequestBody Client usuario) {
        return clientRepository.save(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> obterClientePorId(@PathVariable Long id) {
        Optional<Client> cliente = clientRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> atualizarCliente(@PathVariable Long id, @RequestBody Client detalhesCliente) {
        Optional<Client> clienteOptional = clientRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Client cliente = clienteOptional.get();
            cliente.setNome(detalhesCliente.getNome());
            cliente.setEmail(detalhesCliente.getEmail());
            cliente.setTelefone(detalhesCliente.getTelefone());

            Client clienteAtualizado = clientRepository.save(cliente);
            return ResponseEntity.ok(clienteAtualizado);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarCliente(@PathVariable Long id) {
        if (clientRepository.existsById(id)) {
            clientRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
