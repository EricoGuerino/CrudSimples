import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.virtual.crudsimplesjsf2.dao.GDAO;
import br.com.virtual.crudsimplesjsf2.model.Usuario;

@FacesConverter(forClass=Usuario.class, value="usuarioConverter")
public class UsuarioConverter implements Converter
{
	GDAO<Usuario> genericDAO = new GDAO<Usuario>();
	@Override
	public Object getAsObject(FacesContext context, UIComponent arg1, String value) 
	{
		if(value == null || value.isEmpty()) 
		{
			return null;
		}
		
		else
		{
			Long id = Long.valueOf(value);
			Usuario usuario = genericDAO.acharUsuario(id);
			return usuario;
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent arg1, Object value) 
	{
		Usuario usuario = (Usuario) value;
		
    	if(usuario == null || usuario.getId() == null) 
			return null;
		else
			return String.valueOf(usuario.getId());
	}

}
