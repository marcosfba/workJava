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

import br.edu.unitri.ejb.PessoaDao;
import br.edu.unitri.model.Pessoa;

/**
 * @author marcos.fernando
 *
 */
@Path("/pessoa")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PessoaService implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private PessoaDao pessoaDao;
	
	@GET
	@Path("/todos")
	public List<Pessoa> listAll() {	
		return pessoaDao.listAll();
	}

	@GET
	@Path("/porId")
	public Pessoa getById(Integer id) {
		return pessoaDao.getById(id);
	}
	
	@GET
	@Path("/porExemplo/{entidade}")
	public List<Pessoa> listBypessoa(@PathParam("entidade") String entidade) throws Exception {
		Pessoa pessoa = new ObjectMapper().readValue(entidade, Pessoa.class);
		return pessoaDao.listByEntity(pessoa);
	}
	
	@POST
	@Path("/salvar")
	public Response salvar(Pessoa pessoa) {
		try {
			if (pessoa.getId() != null) {
				pessoaDao.update(pessoa);
			} else {
				pessoaDao.insert(pessoa);
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
			Pessoa pessoa = new ObjectMapper().readValue(entidade, Pessoa.class);
			pessoaDao.deleteByEntity(pessoa);
			return Response.ok().build();
        } catch (Exception e) {
			Map<String, String> responseObj = new HashMap<String, String>();
			responseObj.put("msg", "pessoa não existe");
			return Response.ok().entity(responseObj).build();
        }	
	}

}
