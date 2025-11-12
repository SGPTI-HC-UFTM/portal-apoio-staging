package net.ebserh.hctm.controller.auth;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.security.enterprise.AuthenticationStatus;
import jakarta.security.enterprise.SecurityContext;
import jakarta.security.enterprise.authentication.mechanism.http.AuthenticationParameters;
import jakarta.security.enterprise.credential.Password;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.ebserh.hctm.util.FacesUtils;
import org.apache.commons.lang3.StringUtils;

@Named
@ViewScoped
public class AuthController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private FacesContext facesContext;

    @Inject
    private SecurityContext securityContext;

    private String username;

    private String password;

    public void teste() {
        if (username.trim().equalsIgnoreCase("admin") && password.equals("123456"))
            FacesUtils.showInfo("Seja bem vindo!");
        else
            FacesUtils.showError("Usuário/senha inválidos");
    }

    public void login() {
        LOGGER.severe("DBG: processando autenticação");

        if (StringUtils.isBlank(username)) {
            FacesUtils.showError("É necessário informar o usuário.");
            return;
        }

        String trimmedUsername = username.trim();
        if (StringUtils.isBlank(trimmedUsername) ||  StringUtils.isBlank(password)) {
            FacesUtils.showError("É necessário informar o usuário e a senha.");
            return;
        }

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();

        try {
            AuthenticationStatus authenticationStatus = securityContext.authenticate(request, response,
                    AuthenticationParameters.withParams().credential(
                            new UsernamePasswordCredential(trimmedUsername, new Password(password))));

            switch (authenticationStatus) {
                case SUCCESS -> facesContext.getExternalContext().redirect("index.jsf?faces-redirect=true");
                case SEND_CONTINUE -> facesContext.responseComplete();
                case SEND_FAILURE -> {
                    return;
                }
                default -> LOGGER.severe("Erro ao autenticar usuário. Status = " +
                        String.valueOf(authenticationStatus) + ".");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            FacesUtils.showError("Ocorreu um erro ao fazer o login.");
        }
    }

    public String logout() {
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();

        try {
            request.logout();
            return "/login?faces-redirect=true&logout=true";
        } catch (ServletException e) {
            FacesUtils.showError("Ocorreu um erro ao fazer o logout.");
            return "/login?faces-redirect=true&error=true";
        }
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
