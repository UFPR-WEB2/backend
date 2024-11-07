package com.grupo2.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.grupo2.demo.model.User.Costumer;
import com.grupo2.demo.repository.CostumerRepository;

@RestController
@RequestMapping("/api/cliente")
public class CostumerController {

    @Autowired
    private CostumerRepository clientRepository;

    @GetMapping
    public List<Costumer> listarClientes() {
        return clientRepository.findAll();
    }

    @PostMapping
    public Costumer criarCliente(@RequestBody Costumer usuario) {
        return clientRepository.save(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Costumer> obterClientePorId(@PathVariable Long id) {
        Optional<Costumer> cliente = clientRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Costumer> atualizarCliente(@PathVariable Long id, @RequestBody Costumer detalhesCliente) {
        Optional<Costumer> clienteOptional = clientRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Costumer cliente = clienteOptional.get();
            cliente.setNome(detalhesCliente.getNome());
            cliente.setEmail(detalhesCliente.getEmail());
            cliente.setTelefone(detalhesCliente.getTelefone());

            Costumer clienteAtualizado = clientRepository.save(cliente);
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
