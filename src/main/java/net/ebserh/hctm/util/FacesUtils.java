package net.ebserh.hctm.util;

import java.io.IOException;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.servlet.http.HttpServletRequest;

public class FacesUtils {

	public static String getCurrentUserLogin() {
		return FacesContext
				.getCurrentInstance()
				.getExternalContext()
				.getRemoteUser();
	}

	public static void showError(String message) {
		FacesContext
			.getCurrentInstance()
			.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public static void showInfo(String message) {
		FacesContext
			.getCurrentInstance()
			.addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public static void showError(String id, String message) {
		FacesContext
			.getCurrentInstance()
			.addMessage(id, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
	}

	public static void showInfo(String id, String message) {
		FacesContext
			.getCurrentInstance()
			.addMessage(id, 
					new FacesMessage(FacesMessage.SEVERITY_INFO, message, null));
	}

	public static void redirect(String url) throws IOException {
		FacesContext
			.getCurrentInstance()
			.getExternalContext()
			.redirect(url);
	}

    public static String getIpAddress() {
        HttpServletRequest httpServletRequest = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();  
        return httpServletRequest.getRemoteAddr();
    }

	public static String formataCpf(Long cpf) {
		if (cpf == null)
			return "";

		String cpfStr = String.format("%011d", cpf);
		return String.format("%s.%s.%s-%s",
			cpfStr.substring(0, 3),
			cpfStr.substring(3, 6),
			cpfStr.substring(6, 9),
			cpfStr.substring(9));
	}

	public static String formataSigtap(Long sigtap) {
		if (sigtap == null)
			return "";

		String sigtapStr = String.format("%010d", sigtap);
		return String.format("%s.%s.%s.%s-%s",
			sigtapStr.substring(0, 2),
			sigtapStr.substring(2, 4),
			sigtapStr.substring(4, 6),
			sigtapStr.substring(6, 9),
			sigtapStr.substring(9));
	}

	public static String formataCartaoSus(Long cartaoSus) {
		if (cartaoSus == null)
			return "";

		String cartaoSusStr = String.format("%015d", cartaoSus);
		return String.format("%s %s %s %s",
			cartaoSusStr.substring(0, 3),
			cartaoSusStr.substring(3, 7),
			cartaoSusStr.substring(7, 11),
			cartaoSusStr.substring(11));
	}

}
