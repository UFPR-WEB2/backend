package com.grupo2.demo.utils;

import com.grupo2.demo.model.Maintenance.*;
import com.grupo2.demo.config.StatusEnum;
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.model.User.Employee;
import com.grupo2.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private StatusRepository statusRepository;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private MaintenanceRepository maintenanceRepository;
    

    @Override
    public void run(String... args) throws Exception {

        Arrays.stream(StatusEnum.values()).forEach(statusEnum -> {
            if (statusRepository.findByNomeStatus(statusEnum).isEmpty()) {
                Status status = new Status(statusEnum);
                statusRepository.save(status);
            }
        });

        Employee employee = employeeRepository.findByEmail("employee@example.com");
        if (employee == null) {
            employee = new Employee();
            employee.setNome("Funcionário Inicial");
            employee.setEmail("employee@example.com");

            String plainPassword = PasswordGenerator.generatePassword();
            String salt = PasswordGenerator.generateSalt();
            String hashedPassword = PasswordGenerator.hashPassword(plainPassword, salt);

            employee.setPassword(hashedPassword);
            employee.setSalt(salt);
            employee.setAtivo(true);
            employee.setDataNascimento(LocalDate.of(1990, 1, 1));

            employeeRepository.save(employee);

            System.out.println("Funcionário criado com email: " + employee.getEmail());
            System.out.println("Senha do funcionário (não criptografada): " + plainPassword);
        }

        Customer customer = customerRepository.findByEmail("customer@example.com");
        if (customer == null) {
            customer = new Customer();
            customer.setNome("Cliente Inicial");
            customer.setEmail("customer@example.com");

            String plainPassword = PasswordGenerator.generatePassword();
            String salt = PasswordGenerator.generateSalt();
            String hashedPassword = PasswordGenerator.hashPassword(plainPassword, salt);

            customer.setPassword(hashedPassword);
            customer.setSalt(salt);
            customer.setAtivo(true);
            customer.setCpf("12345678901");
            customer.setCep("12345678");
            customer.setPais("Brasil");
            customer.setEstado("SP");
            customer.setCidade("São Paulo");
            customer.setRua("Rua Inicial");
            customer.setNumero("123");
            customer.setComplemento("Complemento");
            customer.setTelefone("11999999999");

            customerRepository.save(customer);

            System.out.println("Cliente criado com email: " + customer.getEmail());
            System.out.println("Senha do cliente (não criptografada): " + plainPassword);
        }

        // Create a Category
        Category category = categoryRepository.findByNomeCategoria("Eletrônicos");
        if (category == null) {
            category = new Category();
            category.setNome_categoria("Eletrônicos");
            category.setAtivo(true);
            categoryRepository.save(category);
        }

        // Create Maintenance
        Maintenance maintenance = new Maintenance();
        maintenance.setDescricao_equipamento("Notebook Dell");
        maintenance.setDescricao_defeito("Tela não liga");
        maintenance.setData_criacao(LocalDateTime.now());
        maintenance.setCliente(customer);
        maintenance.setCategoria(category);
        maintenance.setStatus(statusRepository.findByNomeStatus(StatusEnum.ABERTA).orElseThrow());
        maintenanceRepository.save(maintenance);

        System.out.println("Dados iniciais carregados com sucesso!");
    }
}
