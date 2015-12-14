/**
 * 
 */
package br.edu.unitri.Rest;


import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.codehaus.jackson.map.ObjectMapper;

import br.edu.unitri.ejb.TelefoneDao;
import br.edu.unitri.model.Telefone;

/**
 * @author marcos.fernando
 *
 */
@Path("/telefone")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class TelefoneService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private TelefoneDao telefoneDao;
	
	@GET
	@Path("/todos")
	public List<Telefone> listAll() {	
		return telefoneDao.listAll();
	}

	@GET
	@Path("/porId")
	public Telefone getById(Integer id) {
		return telefoneDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Telefone> listByTelefone(Telefone telefone) {
		return telefoneDao.listByEntity(telefone);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Telefone telefone) {
		try {
			if (telefone.getId() != null) {
				telefoneDao.update(telefone);
			} else {
				telefoneDao.insert(telefone);
			}
			return Response.ok().build();
		} catch (Exception ex) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("erro", ex.getMessage());
			return Response.status(Response.Status.BAD_REQUEST).entity(responseObj).build();
		}
	}
	
	@DELETE
	@Path("/excluir/{entidade}")	
	public Response excluir(@PathParam("entidade") String entidade) throws Exception{
		try {			
			Telefone telefone = new ObjectMapper().readValue(entidade, Telefone.class);
			telefoneDao.deleteByEntity(telefone);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}

}
