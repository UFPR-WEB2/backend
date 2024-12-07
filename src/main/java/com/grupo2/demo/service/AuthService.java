package com.grupo2.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.dto.AuthResponse;
import com.grupo2.demo.exception.ForbiddenException;
import com.grupo2.demo.exception.UnauthorizedException;
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.model.User.Employee;
import com.grupo2.demo.repository.CustomerRepository;
import com.grupo2.demo.repository.EmployeeRepository;
import com.grupo2.demo.utils.PasswordGenerator;

import jakarta.servlet.http.HttpSession;

@Service
public class AuthService {
    @Autowired
    private CustomerRepository clientRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private HttpSession session;

    public AuthResponse authenticate(String email, String password) {
        Optional<Customer> customerOpt = Optional.ofNullable(clientRepository.findByEmail(email));
        AuthResponse authResponse = new AuthResponse();

        if (customerOpt.isPresent()) {
            Customer customer = customerOpt.get();
            if (checkPassword(password, customer.getPassword(), customer.getSalt())) {
                setUserSession(customer, "CUSTOMER");
                authResponse.setUser(customer);
                authResponse.setRole("CUSTOMER");
                return authResponse;
            }
        }

        Optional<Employee> employeeOpt = Optional.ofNullable(employeeRepository.findByEmail(email));
        if (employeeOpt.isPresent()) {
            Employee employee = employeeOpt.get();
            if (checkPassword(password, employee.getPassword(), employee.getSalt())) {
                setUserSession(employee, "EMPLOYEE");
                authResponse.setUser(employee);
                authResponse.setRole("EMPLOYEE");
                System.out.println(authResponse);
                return authResponse;
            }
        }

        return null;
    }

    private void setUserSession(Object user, String role) {
        session.setAttribute("user", user);
        session.setAttribute("role", role);
    }

    private boolean checkPassword(String inputPassword, String storedPassword, String salt) {
        String hashedInputPassword = PasswordGenerator.hashPassword(inputPassword, salt);
        return storedPassword.equals(hashedInputPassword);
    }

    public AuthResponse getSession() {
        if (session.getAttribute("user") == null) {
            return null;
        }
    
        // Obtém a role da sessão
        String role = (String) session.getAttribute("role");
        AuthResponse authResponse = new AuthResponse();
    
        if ("CUSTOMER".equals(role)) {
            Customer user = (Customer) session.getAttribute("user");
            authResponse.setUser(user);
            authResponse.setId(user.getId());
        } else if ("EMPLOYEE".equals(role)) {
            Employee user = (Employee) session.getAttribute("user");
            authResponse.setUser(user);
            authResponse.setId(user.getId());
        } else {
            return null;
        }
    
        authResponse.setRole(role);
        return authResponse;
    }
    

    // QUANDO ARRUMAR O ERRO DE AUTHORIZATION, DESCOMENTAR ESSES MÉTODOS!!

    public void checkEmployeeAuth() {
        checkAuth();
        if(getSession().getRole().equals("CUSTOMER") ) {
            throw new ForbiddenException("Usuário não autorizado");
        }
    }

    public void checkCustomerAuth() {
        checkAuth();
        if(getSession().getRole().equals("EMPLOYEE")) {
            throw new ForbiddenException("Usuário não autorizado");
        }
    }

    public void checkAuth() {
        if (getSession() == null) {
            throw new UnauthorizedException("Usuário não autenticado, faça login para continuar");
        }
    }

    public Customer getCustomer() {
        return (Customer) session.getAttribute("user");
    }

    public Employee getEmployee() {
        return (Employee) session.getAttribute("user");
    }

    public void logout() {
        session.invalidate();
    }

}