package net.ebserh.hctm.controller.auth;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import net.ebserh.hctm.util.FacesUtils;
import org.apache.commons.lang3.StringUtils;

@Named
@ViewScoped
public class AuthController implements Serializable {

    private static final Logger LOGGER = Logger.getAnonymousLogger();

    @Inject
    private FacesContext facesContext;

    private String username;

    private String password;

    public String login() {
        LOGGER.severe("DBG: processando autenticação");

        if (StringUtils.isBlank(username)) {
            FacesUtils.showError("É necessário informar o usuário.");
            return "/login.jsf";
        }

        String trimmedUsername = username.trim();
        if (StringUtils.isBlank(trimmedUsername) ||  StringUtils.isBlank(password)) {
            FacesUtils.showError("É necessário informar o usuário e a senha.");
            return "/login.jsf";
        }

        HttpServletRequest request = (HttpServletRequest) facesContext.getExternalContext().getRequest();

        try {
            request.login(trimmedUsername, password);
            return "/index.jsf?faces-redirect=true";
        } catch(ServletException e) {
            return "/login.jsf?faces-redirect=true&error=true";
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            FacesUtils.showError("Ocorreu um erro ao fazer o login.");
            return "/login.jsf";
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
