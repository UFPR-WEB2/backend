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

<<<<<<< HEAD:src/main/java/com/grupo2/demo/controller/CostumerController.java
import com.grupo2.demo.model.User.Costumer;
import com.grupo2.demo.repository.CostumerRepository;
=======
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.repository.ClientRepository;
>>>>>>> e24476d9dc39a43b8d0b4952cfa065957f67639d:src/main/java/com/grupo2/demo/controller/ClientController.java

@RestController
@RequestMapping("/api/cliente")
public class CostumerController {

    @Autowired
    private CostumerRepository clientRepository;

    @GetMapping
<<<<<<< HEAD:src/main/java/com/grupo2/demo/controller/CostumerController.java
    public List<Costumer> listarClientes() {
=======
    public List<Customer> listarClientes() {
>>>>>>> e24476d9dc39a43b8d0b4952cfa065957f67639d:src/main/java/com/grupo2/demo/controller/ClientController.java
        return clientRepository.findAll();
    }

    @PostMapping
<<<<<<< HEAD:src/main/java/com/grupo2/demo/controller/CostumerController.java
    public Costumer criarCliente(@RequestBody Costumer usuario) {
=======
    public Customer criarCliente(@RequestBody Customer usuario) {
>>>>>>> e24476d9dc39a43b8d0b4952cfa065957f67639d:src/main/java/com/grupo2/demo/controller/ClientController.java
        return clientRepository.save(usuario);
    }

    @GetMapping("/{id}")
<<<<<<< HEAD:src/main/java/com/grupo2/demo/controller/CostumerController.java
    public ResponseEntity<Costumer> obterClientePorId(@PathVariable Long id) {
        Optional<Costumer> cliente = clientRepository.findById(id);
=======
    public ResponseEntity<Customer> obterClientePorId(@PathVariable Long id) {
        Optional<Customer> cliente = clientRepository.findById(id);
>>>>>>> e24476d9dc39a43b8d0b4952cfa065957f67639d:src/main/java/com/grupo2/demo/controller/ClientController.java
        return cliente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
<<<<<<< HEAD:src/main/java/com/grupo2/demo/controller/CostumerController.java
    public ResponseEntity<Costumer> atualizarCliente(@PathVariable Long id, @RequestBody Costumer detalhesCliente) {
        Optional<Costumer> clienteOptional = clientRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Costumer cliente = clienteOptional.get();
=======
    public ResponseEntity<Customer> atualizarCliente(@PathVariable Long id, @RequestBody Customer detalhesCliente) {
        Optional<Customer> clienteOptional = clientRepository.findById(id);

        if (clienteOptional.isPresent()) {
            Customer cliente = clienteOptional.get();
>>>>>>> e24476d9dc39a43b8d0b4952cfa065957f67639d:src/main/java/com/grupo2/demo/controller/ClientController.java
            cliente.setNome(detalhesCliente.getNome());
            cliente.setEmail(detalhesCliente.getEmail());
            cliente.setTelefone(detalhesCliente.getTelefone());

<<<<<<< HEAD:src/main/java/com/grupo2/demo/controller/CostumerController.java
            Costumer clienteAtualizado = clientRepository.save(cliente);
=======
            Customer clienteAtualizado = clientRepository.save(cliente);
>>>>>>> e24476d9dc39a43b8d0b4952cfa065957f67639d:src/main/java/com/grupo2/demo/controller/ClientController.java
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
