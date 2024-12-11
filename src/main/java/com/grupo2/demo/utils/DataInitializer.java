package com.grupo2.demo.utils;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.grupo2.demo.config.StatusEnum;
import com.grupo2.demo.model.Maintenance.Category;
import com.grupo2.demo.model.Maintenance.Status;
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.model.User.Employee;
import com.grupo2.demo.repository.BudgetRepository;
import com.grupo2.demo.repository.CategoryRepository;
import com.grupo2.demo.repository.CustomerRepository;
import com.grupo2.demo.repository.EmployeeRepository;
import com.grupo2.demo.repository.MaintenanceRepository;
import com.grupo2.demo.repository.StatusRepository;

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

    @Autowired
    private BudgetRepository budgetRepository;

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
        /* 
        // Create Maintenance
        Maintenance maintenance = new Maintenance();
        maintenance.setDescricao_equipamento("Notebook Dell");
        maintenance.setDescricao_defeito("Tela não liga");
        maintenance.setData_criacao(LocalDateTime.now());
        maintenance.setCliente(customer);
        maintenance.setCategoria(category);
        maintenance.setStatus(statusRepository.findByNomeStatus(StatusEnum.APROVADA).orElseThrow());
        maintenanceRepository.save(maintenance);

        // Create Budget for Maintenance
        Budget budget = new Budget();
        budget.setPrecoOrcado(new BigDecimal("100.20"));
        budget.setDataOrcamento(LocalDateTime.now());
        budget.setStatus(true);
        budgetRepository.save(budget);
        maintenance.setOrcamento(budget);
        maintenanceRepository.save(maintenance);

        Maintenance maintenance2 = new Maintenance();
        maintenance2.setDescricao_equipamento("Notebook HP");
        maintenance2.setDescricao_defeito("Tela quebrada");
        maintenance2.setData_criacao(LocalDateTime.now());
        maintenance2.setCliente(customer);
        maintenance2.setCategoria(category);
        maintenance2.setStatus(statusRepository.findByNomeStatus(StatusEnum.ABERTA).orElseThrow());
        maintenanceRepository.save(maintenance2);

        Maintenance maintenance3 = new Maintenance();
        maintenance3.setDescricao_equipamento("Notebook Lenovo");
        maintenance3.setDescricao_defeito("Teclado não funciona");
        maintenance3.setData_criacao(LocalDateTime.now());
        maintenance3.setCliente(customer);
        maintenance3.setCategoria(category);
        maintenance3.setStatus(statusRepository.findByNomeStatus(StatusEnum.ABERTA).orElseThrow());
        maintenanceRepository.save(maintenance3);

        System.out.println("Dados iniciais carregados com sucesso!");
        */
    }
}
