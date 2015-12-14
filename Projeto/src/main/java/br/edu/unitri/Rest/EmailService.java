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

import br.edu.unitri.ejb.EmailDao;
import br.edu.unitri.model.Email;

/**
 * @author marcos.fernando
 *
 */
@Path("/email")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmailService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private EmailDao emailDao;
	
	@GET
	@Path("/todos")
	public List<Email> listAll() {	
		return emailDao.listAll();
	}

	@GET
	@Path("/porId")
	public Email getById(Integer id) {
		return emailDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo")
	public List<Email> listByEmail(Email email) {
		return emailDao.listByEntity(email);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Email email) {
		try {
			if (email.getId() != null) {
				emailDao.update(email);
			} else {
				emailDao.insert(email);
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
			Email email = new ObjectMapper().readValue(entidade, Email.class);
			emailDao.deleteByEntity(email);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "endereço não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}

}
