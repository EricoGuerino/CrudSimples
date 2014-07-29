package br.com.virtual.crudsimplesjsf2.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.virtual.crudsimplesjsf2.model.Usuario;
import br.com.virtual.crudsimplesjsf2.util.JPAUtil;

public class GDAO<T> 
{
	private EntityManager emLocal = JPAUtil.getInstance().getEntityManager();
	
	public T salvarUsuario(T t)
	{
		this.emLocal.getTransaction().begin();
		T t2 = this.emLocal.merge(t);
		this.emLocal.getTransaction().commit();
		
		return t2;
	}

	public T editarUsuario(T t) 
	{
		this.emLocal.getTransaction().begin();
		T t2 = this.emLocal.merge(t);
		this.emLocal.getTransaction().commit();
		
		return t2;
	}

	public void deletarUsuario(Usuario t) 
	{
		this.emLocal.getTransaction().begin();
		Usuario t2 = this.emLocal.find(Usuario.class, t.getId());
		this.emLocal.remove(t2);
		this.emLocal.getTransaction().commit();
	}

	public Usuario acharUsuario(Long id) 
	{
		Query query = emLocal.createQuery("SELECT u FROM Usuario u");
		return (Usuario) query.getResultList().get(0);
//		T t2 = (T) this.emLocal.find(t.getClass(), id);
//		return t2;
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> listarTodos() 
	{
		List<Usuario> listaLocal = new ArrayList<Usuario>();
		String sql = "SELECT u.id, u.nome, u.cpf, u.telefone, u.email FROM usuario AS u;";
		Query query = emLocal.createNativeQuery(sql); 
		List<Object[]> listaDeObjetos = new ArrayList<Object[]>();
		listaDeObjetos = query.getResultList();
		for(Object[] row : listaDeObjetos)
		{
			Usuario usuario = new Usuario();
			Long id_long = Long.valueOf(String.valueOf(row[0]));
			String nome_str = String.valueOf(row[1]);
			String cpf_str = String.valueOf(row[2]);
			String telefone_str = String.valueOf(row[3]);
			String email_str = String.valueOf(row[4]);
			
			usuario.setId(id_long);
			usuario.setNome(nome_str);
			usuario.setCpf(cpf_str);
			usuario.setEmail(email_str);
			usuario.setTelefone(telefone_str);
			
			listaLocal.add(usuario);
		}
		
		return listaLocal;
	}
}
