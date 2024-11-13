package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.model.User.Employee;
import com.grupo2.demo.repository.CustomerRepository;
import com.grupo2.demo.repository.EmployeeRepository;
import com.grupo2.demo.utils.PasswordGenerator;

import jakarta.servlet.http.HttpSession;
import java.util.Optional;

@Service
public class AuthService {
    @Autowired
    private CustomerRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HttpSession session;

    public boolean authenticate(String email, String password) {
        Optional<Customer> customerOpt = Optional.ofNullable(clientRepository.findByEmail(email));
        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (checkPassword(password, customer.getPassword(), customer.getSalt())) {
                setUserSession(customer, "CLIENT");
                return true;
            }
        }

        Optional<Employee> employeeOpt = Optional.ofNullable(employeeRepository.findByEmail(email));
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (checkPassword(password, employee.getPassword(), employee.getSalt())) {
                setUserSession(employee, "EMPLOYEE");
                return true;
            }
        }

        return false;
    }

    private void setUserSession(Object user, String role) {
        session.setAttribute("user", user);
        session.setAttribute("role", role);
    }

    private boolean checkPassword(String inputPassword, String storedPassword, String salt) {
        String hashedInputPassword = PasswordGenerator.hashPassword(inputPassword, salt);
        System.out.println(hashedInputPassword);
        return storedPassword.equals(hashedInputPassword);
    }

    
}