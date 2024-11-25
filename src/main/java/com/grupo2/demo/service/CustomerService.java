package com.grupo2.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.grupo2.demo.dto.CustomerRequest;
import com.grupo2.demo.dto.PasswordResetRequest;
import com.grupo2.demo.model.User.Customer;
import com.grupo2.demo.repository.CustomerRepository;
import com.grupo2.demo.utils.PasswordGenerator;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private EmailService emailService;

    public Customer customerCreate(CustomerRequest usuario) {
        try {
            Customer customer = usuario.toCustomer();
            String generatedPassword = PasswordGenerator.generatePassword();

            String salt = PasswordGenerator.generateSalt();
            String hashedPassword = PasswordGenerator.hashPassword(generatedPassword, salt);

            customer.setPassword(hashedPassword);
            customer.setSalt(salt);
            customer.setAtivo(true);

            Customer savedCustomer = customerRepository.save(customer);

            String subject = "Bem-vindo ao nosso sistema!";
            String body = "<!DOCTYPE html>" +
                    "<html>" +
                    "<head>" +
                    "<style>" +
                    "body { font-family: Helvetica, sans-serif; -webkit-font-smoothing: antialiased; font-size: 16px; line-height: 1.3; -ms-text-size-adjust: 100%; -webkit-text-size-adjust: 100%; }" +
                    "table { border-collapse: separate; mso-table-lspace: 0pt; mso-table-rspace: 0pt; width: 100%; }" +
                    "table td { font-family: Helvetica, sans-serif; font-size: 16px; vertical-align: top; }" +
                    "body { background-color: #f4f5f6; margin: 0; padding: 0; }" +
                    ".body { background-color: #f4f5f6; width: 100%; }" +
                    ".container { margin: 0 auto !important; max-width: 600px; padding: 0; padding-top: 24px; width: 600px; }" +
                    ".content { box-sizing: border-box; display: block; margin: 0 auto; max-width: 600px; padding: 0; }" +
                    ".main { background: #ffffff; border: 1px solid #eaebed; border-radius: 16px; width: 100%; }" +
                    ".wrapper { box-sizing: border-box; padding: 24px; }" +
                    ".footer { clear: both; padding-top: 24px; text-align: center; width: 100%; }" +
                    ".footer td, .footer p, .footer span, .footer a { color: #9a9ea6; font-size: 16px; text-align: center; }" +
                    "p { font-family: Helvetica, sans-serif; font-size: 16px; font-weight: normal; margin: 0; margin-bottom: 16px; }" +
                    "a { color: #0867ec; text-decoration: none; margin-bottom: 10px; padding: 8px; font-size: 14px; }" +
                    ".btn { box-sizing: border-box; min-width: 100% !important; width: 100%; }" +
                    ".btn > tbody > tr > td { padding-bottom: 16px; }" +
                    ".btn table { width: auto; }" +
                    ".btn table td { background-color: #ffffff; border-radius: 4px; text-align: center; }" +
                    ".btn a { background-color: #ffffff; border: solid 2px #0867ec; border-radius: 4px; box-sizing: border-box; color: #0867ec; display: inline-block; font-size: 16px; font-weight: bold; margin: 0; padding: 12px 24px; text-decoration: none; text-transform: capitalize; }" +
                    ".btn-primary table td { background-color: #0867ec; }" +
                    ".btn-primary a { background-color: #0867ec; border-color: #0867ec; color: #ffffff; }" +
                    "@media all { .btn-primary table td:hover { background-color: #ec0867 !important; } .btn-primary a:hover { background-color: #ec0867 !important; border-color: #ec0867 !important; } }" +
                    ".last { margin-bottom: 0; }" +
                    ".first { margin-top: 0; }" +
                    ".align-center { text-align: center; }" +
                    ".align-right { text-align: right; }" +
                    "#left { text-align: center; }" +
                    ".text-link { color: #0867ec !important; text-decoration: underline !important; }" +
                    ".clear { clear: both; }" +
                    ".mt0 { margin-top: 0; }" +
                    ".mb0 { margin-bottom: 0; }" +
                    ".preheader { color: transparent; display: none; height: 0; max-height: 0; max-width: 0; opacity: 0; overflow: hidden; mso-hide: all; visibility: hidden; width: 0; }" +
                    ".powered-by a { text-decoration: none; }" +
                    "@media only screen and (max-width: 640px) { .main p, .main td, .main span { font-size: 16px !important; } .wrapper { padding: 8px !important; } .content { padding: 0 !important; } .container { padding: 0 !important; padding-top: 8px !important; width: 100% !important; } .main { border-left-width: 0; border-radius: 0 !important; border-right-width: 0 !important; } .btn table { max-width: 100% !important; width: 100% !important; } .btn a { font-size: 16px !important; max-width: 100% !important; width: 100% !important; } }" +
                    "@media all { .ExternalClass { width: 100%; } .ExternalClass, .ExternalClass p, .ExternalClass span, .ExternalClass font, .ExternalClass td, .ExternalClass div { line-height: 100%; } .apple-link a { color: inherit !important; font-family: inherit !important; font-size: inherit !important; font-weight: inherit !important; line-height: inherit !important; text-decoration: none !important; } #MessageViewBody a { color: inherit; text-decoration: none; font-size: inherit; font-family: inherit; font-weight: inherit; line-height: inherit; } }" +
                    "</style>" +
                    "</head>" +
                    "<body>" +
                    "<table role='presentation' border='0' cellpadding='0' cellspacing='0' class='body'>" +
                    "<tr>" +
                    "<td>&nbsp;</td>" +
                    "<td class='container'>" +
                    "<div class='content'>" +
                    "<table role='presentation' border='0' cellpadding='0' cellspacing='0' class='main'>" +
                    "<tr>" +
                    "<td class='wrapper'>" +
                    "<p>Seja bem-vindo " + customer.getNome() + "</p>" +
                    "<p>Após realizar o cadastro, é necessário informar sua senha que é através de uma sequência de números.</p>" +
                    "<table role='presentation' border='0' cellpadding='0' cellspacing='0' class='btn btn-primary'>" +
                    "<tbody>" +
                    "<tr>" +
                    "<td id='left'>" +
                    "<table role='presentation' border='0' cellpadding='0' cellspacing='0'>" +
                    "<tbody>" +
                    "<tr>" +
                    "<td><a>" + generatedPassword + "</a></td>" +
                    "</tr>" +
                    "</tbody>" +
                    "</table>" +
                    "</td>" +
                    "</tr>" +
                    "</tbody>" +
                    "</table>" +
                    "<p>Digite esse número no seu primeiro login para confirmar a conta.</p>" +
                    "<p>Boa sorte! Espero que funcione :P</p>" +
                    "</td>" +
                    "</tr>" +
                    "</table>" +
                    "</div>" +
                    "</td>" +
                    "<td>&nbsp;</td>" +
                    "</tr>" +
                    "</table>" +
                    "</body>" +
                    "</html>";

            emailService.sendEmail(customer.getEmail(), subject, body);

            return savedCustomer;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean customerPasswordRequest(PasswordResetRequest request) {
        try {
            Customer customer = customerRepository.findByEmail(request.getEmail());
            if (customer == null) {
                return false;
            }

            String generatedPassword = PasswordGenerator.generatePassword();
            String salt = PasswordGenerator.generateSalt();
            String hashedPassword = PasswordGenerator.hashPassword(generatedPassword, salt);

            customer.setPassword(hashedPassword);
            customer.setSalt(salt);
            customerRepository.save(customer);

            String subject = "Recuperação de Senha";
            String body = "<!DOCTYPE html>" +
                          "<html>" +
                          "<head>" +
                          "<meta charset='UTF-8'>" +
                          "<title>Recuperação de Senha</title>" +
                          "</head>" +
                          "<body>" +
                          "<p>Olá " + customer.getNome() + ",</p>" +
                          "<p>Recebemos sua solicitação de recuperação de senha. Sua nova senha é: <strong>" + generatedPassword + "</strong></p>" +
                          "<p>Por favor, faça login e altere sua senha imediatamente.</p>" +
                          "<p>Atenciosamente,<br>Sua Equipe de Suporte</p>" +
                          "</body>" +
                          "</html>";

            emailService.sendEmail(customer.getEmail(), subject, body);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}