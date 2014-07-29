package br.com.virtual.crudsimplesjsf2.managedbeans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

import br.com.virtual.crudsimplesjsf2.dao.GDAO;
import br.com.virtual.crudsimplesjsf2.model.Usuario;

@SessionScoped
@ManagedBean(name="usuarioMB")
public class UsuarioMB implements Serializable
{
	
	private static final long serialVersionUID = -6564596834362711347L;
	public static final String PAGINA_INDEX = "/index.xhtml";
	public static final String PAGINA_ALTERAR = "/alterarDados.xhtml";
	public static final String PAGINA_ADM = "/administracao.xhtml";
	
	private Long matriculaUsuario;
	private Usuario usuario;
	private GDAO<Usuario> genericUsuarioDAO = new GDAO<Usuario>();
	private List<Usuario> listarUsuarios = new ArrayList<Usuario>();
	
	public UsuarioMB(){}
	
	@PostConstruct
	public void init()
	{
		this.usuario = new Usuario();
		this.listarUsuarios = this.genericUsuarioDAO.listarTodos();
	}
	
	public String salvarDadosUsuario()
	{
		this.genericUsuarioDAO.salvarUsuario(this.usuario);
		init();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Usuario salvo com sucesso!", null));
		
		return PAGINA_INDEX;
	}
	
	public String editarDadosUsuario()
	{
		this.genericUsuarioDAO.editarUsuario(this.usuario);
		init();
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Usuario editado com sucesso!", null));
		return PAGINA_ADM;
	}
	
	public String apagarUsuario()
	{
		this.genericUsuarioDAO.deletarUsuario(this.usuario);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, 
				"Usuario apagado com sucesso!", null));
		return PAGINA_INDEX;
	}

	
	public void pegarValor(ValueChangeEvent event)
	{
		this.matriculaUsuario = (Long)event.getNewValue();
	}
	
	public void selecionarUsuario()
	{
		Usuario user = new Usuario();
		user = genericUsuarioDAO.acharUsuario(this.matriculaUsuario);
		System.out.println(user.getId() + "; " + user.getNome());
		this.usuario = user;
	}
	
	public String buscarUsuario()
	{
		this.genericUsuarioDAO.acharUsuario(usuario.getId());
		return PAGINA_INDEX;
	}

	public String alterarDados(Usuario u)
	{
		this.usuario = u;
		return PAGINA_ALTERAR;
	}
	
	public String cancelarParaIndex()
	{
		return PAGINA_INDEX;
	}
	private Long row_id;
	
	public Long getRow_id() {
		return this.row_id;
	}

	public void setRow_id(Long row) {
		this.row_id = row;
	}
	
	public void deletarUsuario(Usuario usuario)
	{
//		Long id_usuario = (Long)FacesContext.getCurrentInstance().getViewRoot().findComponent("excluir").getAttributes().get("accesskey");
		
		Usuario usu = new Usuario();
		usu = genericUsuarioDAO.acharUsuario(usuario.getId());
		System.out.println("Row_id: " + this.row_id);
		System.out.println("usuario.getId: " + usuario.getId().toString());

		this.genericUsuarioDAO.deletarUsuario(usuario);
		System.out.println("usu.getNome(): " + usu.getNome());
		System.out.println("usuario.getNome()" + usuario.getNome());
		init();
	}
	
	//GETTERS AND SETTERS
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getListarUsuarios() {
		return listarUsuarios;
	}

	public void setListarUsuarios(List<Usuario> listarUsuarios) {
		this.listarUsuarios = listarUsuarios;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((listarUsuarios == null) ? 0 : listarUsuarios.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UsuarioMB other = (UsuarioMB) obj;
		if (listarUsuarios == null) {
			if (other.listarUsuarios != null)
				return false;
		} else if (!listarUsuarios.equals(other.listarUsuarios))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	
	
}
