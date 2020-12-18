package com.abhiroop.emartviewapp.service;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import com.abhiroop.emartviewapp.model.Customer;

@RegisterRestClient
public interface BackendServiceConsumer {

	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public String getConnectivityStatus();
	
	
	@GET
	@Path("/getAllUsers")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Customer> getAllUsers();

	
	@GET
	@Path("/getUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerById(int id);
	
	@POST
	@Path("/addUser")
	@Produces(MediaType.APPLICATION_JSON)
	public Customer addCustomer(Customer c);
	

}
