package net.ebserh.hctm.controller.administracao;

import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.auth.Grupo;
import net.ebserh.hctm.model.auth.Usuario;
import net.ebserh.hctm.service.auth.GruposService;
import net.ebserh.hctm.service.auth.UsuariosService;
import net.ebserh.hctm.util.Base64Sha256PasswordHash;
import net.ebserh.hctm.util.FacesUtils;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.model.DualListModel;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@ViewScoped
public class AcessoController implements Serializable {
	
	private final static Logger LOGGER = Logger.getAnonymousLogger();
	
	@Inject
	private UsuariosService usuariosService;

	@Inject
	private GruposService gruposService;

	private String loginRede;
	
	private List<Usuario> usuarios;
	
	private DualListModel<Grupo> grupos;
	
	private Usuario usuario;

	private String senha;

	private String confirmacaoSenha;

	@PostConstruct
	public void init() {
		try {
			List<Grupo> grupos = gruposService.buscaTodos();
			this.grupos = new DualListModel<>(grupos, new ArrayList<>());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Ocorreu um erro ao carregar os grupos disponíveis.", e);
		}
	}
	
	public void pesquisaUsuario() {
		if (loginRede == null || loginRede.isBlank()) {
			FacesUtils.showError("É necessário informar o login de rede.");
			return;
		}
		
		if (loginRede.length() < 5) {
			FacesUtils.showError("Login de rede deve ter pelo menos 5 caracteres.");
			return;
		}
		
		try {
			usuarios = usuariosService.buscaPorLogin(loginRede);
			if (usuarios.isEmpty())
				FacesUtils.showError("Nenhum usuário encontrado com o login informado.");
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao pesquisar os usuários.");
		}
	}

	public void openDialogNovoUsuario() {
		try {
			usuario = new Usuario();
			List<Grupo> grupos = gruposService.buscaTodos();
			this.grupos = new DualListModel<>(grupos, new ArrayList<>());
			PrimeFaces.current().executeScript("PF('dlgUsuario').show()");
		} catch (Exception e) {
			FacesUtils.processaExcecao(e, "Ocorreu um erro ao abrir a janela de cadastro de usuários.");
		}
	}
	
	public void openDialogUsuario(Usuario usuarioSelecionado) {
		if (usuarioSelecionado == null) {
			FacesUtils.showError("É necessário selecionar um usuário.");
			return;
		}

		try {
			usuario = usuarioSelecionado;

			List<Grupo> grupos = gruposService.buscaTodos();
			List<Grupo> gruposDoUsuario = new ArrayList<>(usuario.getGrupos());

			for (Grupo g : gruposDoUsuario)
				grupos.remove(g);

			this.grupos = new DualListModel<>(grupos, gruposDoUsuario);
			PrimeFaces.current().executeScript("PF('dlgUsuario').show()");
		} catch (Exception e) {
			FacesUtils.processaExcecao(e, "Ocorreu um erro ao abrir a janela de edição dos usuários.");
		}
	}
	
	public void salvaGrupos() {
		if (usuario == null) {
			FacesUtils.showError("É necessário selecionar um usuário.");
			return;
		}
		
		if (grupos == null || grupos.getSource() == null || grupos.getTarget() == null) {
			FacesUtils.showError("Ocorreu um erro ao carregar os perfis.");
			return;
		}
		
		if (grupos.getSource().isEmpty() && grupos.getTarget().isEmpty()) {
			FacesUtils.showError("É necessário selecionar pelo menos um perfil.");
			return;
		}
		
		try {
			//usuariosService.atualizaGrupos(usuario, grupos.getSource(), grupos.getTarget());
			PrimeFaces.current().executeScript("PF('dlgPerfis').hide()");
			FacesUtils.showInfo("Perfis salvos com sucesso!");
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao salvar os perfis.");
		}
	}
	
	public void salvaUsuario() {
		if (usuario == null) {
			FacesUtils.showError("É necessário selecionar um usuário.");
			return;
		}

		if (StringUtils.isBlank(usuario.getLogin())) {
			FacesUtils.showError("É necessário informar o login do usuário.");
			return;
		}

		if (grupos.getTarget().isEmpty()) {
			FacesUtils.showError("É necessário selecionar pelo menos um grupo para o usuário.");
			return;
		}

		if (StringUtils.isNotBlank(senha) || StringUtils.isNotBlank(confirmacaoSenha)) {
			if (Objects.nonNull(senha) && (!senha.equals(confirmacaoSenha))) {
				FacesUtils.showError("Senha e confirmação não conferem.");
				return;
			}

			if (Objects.nonNull(confirmacaoSenha) && !confirmacaoSenha.equals(senha)) {
				FacesUtils.showError("Senha e confirmação não conferem.");
				return;
			}

			Base64Sha256PasswordHash hash = new Base64Sha256PasswordHash();
			String hashSenha = hash.generate(senha.toCharArray());
			usuario.setPassword(hashSenha);
		}
		
		try {
			usuario.setGrupos(new ArrayList<>(grupos.getTarget()));
			usuariosService.salva(usuario);
			PrimeFaces.current().executeScript("PF('dlgUsuario').hide()");
			FacesUtils.showInfo("Usuário salvo com sucesso!");
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao salvar o usuário.");
		}
	}

	public String getLoginRede() {
		return loginRede;
	}

	public void setLoginRede(String loginRede) {
		this.loginRede = loginRede;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public DualListModel<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(DualListModel<Grupo> grupos) {
		this.grupos = grupos;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmacaoSenha() {
		return confirmacaoSenha;
	}

	public void setConfirmacaoSenha(String confirmacaoSenha) {
		this.confirmacaoSenha = confirmacaoSenha;
	}

}
