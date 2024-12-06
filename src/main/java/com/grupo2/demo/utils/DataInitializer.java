package com.grupo2.demo.utils;

import java.time.LocalDate;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.grupo2.demo.config.StatusEnum;
import com.grupo2.demo.model.Maintenance.Status;
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.model.User.Employee;
import com.grupo2.demo.model.Maintenance.Category;
import com.grupo2.demo.repository.CategoryRepository;
import com.grupo2.demo.repository.CustomerRepository;
import com.grupo2.demo.repository.EmployeeRepository;
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

    @Override
    public void run(String... args) throws Exception {
        Arrays.stream(StatusEnum.values()).forEach(statusEnum -> {
            if (statusRepository.findByNomeStatus(statusEnum).isEmpty()) {
                Status status = new Status(statusEnum);
                statusRepository.save(status);
            }
        });

        if (employeeRepository.findByEmail("employee@example.com") == null) {
            Employee employee = new Employee();
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

        if (customerRepository.findByEmail("customer@example.com") == null) {
            Customer customer = new Customer();
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

        // Adicionando as categorias
        addCategoryIfNotExist("Notebook");
        addCategoryIfNotExist("Desktop");
        addCategoryIfNotExist("Impressora");
        addCategoryIfNotExist("Mouse");
        addCategoryIfNotExist("Teclado");
    }

    private void addCategoryIfNotExist(String categoryName) {
        if (categoryRepository.findByNomeCategoria(categoryName) == null) {
            Category category = new Category();
            category.setNome_categoria(categoryName);
            category.setAtivo(true); // Ou false, se preferir desativar inicialmente
            categoryRepository.save(category);
            System.out.println("Categoria criada: " + categoryName);
        }
    }        
}
