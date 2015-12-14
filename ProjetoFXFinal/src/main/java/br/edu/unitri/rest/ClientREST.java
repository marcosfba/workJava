/**
 * 
 */
package br.edu.unitri.rest;

import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

public abstract class ClientREST<T> {
	
	private ResteasyClient client;
	
	private static final String MEDIA_TYPE = MediaType.APPLICATION_JSON;
	
	private String target = "";
	
	public ClientREST(){		
		client = new ResteasyClientBuilder().build();	
	}
	
	private ResteasyWebTarget targetWeb(String metodo) throws Exception{		
		try {
			RestUtil.testConnection(getBaseURI());
		} catch (Exception e) {
			throw new Exception(e);
		}		 
		ResteasyWebTarget webTarget = client.target(getBaseURI()+target+"/"+metodo);
		return webTarget;
	}
	
	private String getBaseURI() throws Exception{
		return "http://localhost:8080/Projeto/rest/";
	}
	
	protected Object salvar(T entity) throws Exception { 		
		Response response = targetWeb("salvar").request().post(Entity.entity(entity, MEDIA_TYPE));
		handlerResponseStatus(response);
		return response.readEntity(String.class);	
	}
	
	protected String excluir(T entity) throws Exception{
		Response response = targetWeb("excluir/").path(RestUtil.entityToJson(entity)).request().delete();
		handlerResponseStatus(response);
		return response.readEntity(String.class);		
	}
	
	protected List<T> listarAll(T entity) throws Exception{		
		String jsonStr = targetWeb("todos").request(MEDIA_TYPE).get(new GenericType<String>(){});		
		return RestUtil.mapJsonToObjectList(jsonStr, entity.getClass());		
	}
	
	protected Class<T> buscarPorId(Long id) throws Exception{
		return targetWeb("porId/"+id).request(MEDIA_TYPE).get(new GenericType<Class<T>>(){});	
	}
	
	protected List<T> buscarPorExemplo(T entity) throws Exception{	
		String jsonStr = targetWeb("porExemplo/")				
				.queryParam("entidade", RestUtil.entityToJson(entity))
				.request(MEDIA_TYPE)
		    	.get(new GenericType<String>(){});	
		return 	RestUtil.mapJsonToObjectList(jsonStr, entity.getClass());
	}

	private String handlerResponseStatus(Response response) throws Exception{
		int status = response.getStatus();
		String statusInfo = response.getStatusInfo().getReasonPhrase();
		if ( status >= 500) {			
			try {
			} catch (Exception e) {
				throw new Exception(e);
			} 
		} else if (status == 404) {
			RestUtil.handleError(statusInfo);
		}else if (status >= 300){
			RestUtil.handleError(statusInfo);
		}		
		return "OK";		
	}
	
	public void setTargetClient(String target) {
		this.target = target;
	}		
}