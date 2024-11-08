package com.grupo2.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.grupo2.demo.dto.CustomerRequest;
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.repository.CustomerRepository;
import com.grupo2.demo.service.CustomerService;

@RestController
@RequestMapping("/api/cliente")
public class CustomerController {

    @Autowired
    private CustomerRepository clientRepository;

    @Autowired
    private CustomerService clientService;

    @GetMapping
    public List<Customer> listarClientes() {
        return clientRepository.findAll();
    }

    @PostMapping
    public Customer criarCliente(@RequestBody CustomerRequest usuario) {
        return clientService.customerCreate(usuario);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> obterClientePorId(@PathVariable Long id) {
        Optional<Customer> cliente = clientRepository.findById(id);
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Customer> atualizarCliente(@PathVariable Long id, @RequestBody Customer detalhesCliente) {
        Optional<Customer> clienteOptional = clientRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Customer cliente = clienteOptional.get();
            cliente.setNome(detalhesCliente.getNome());
            cliente.setEmail(detalhesCliente.getEmail());
            cliente.setTelefone(detalhesCliente.getTelefone());

            Customer clienteAtualizado = clientRepository.save(cliente);
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