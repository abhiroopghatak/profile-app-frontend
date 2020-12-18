package com.abhiroop.emartviewapp.controller;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;

import com.abhiroop.emartviewapp.model.Customer;
import com.abhiroop.emartviewapp.model.ResponseObject;
import com.abhiroop.emartviewapp.service.BackendServiceConsumer;

import io.vertx.ext.web.RoutingContext;

@RestController
public class CustomerController {

	
	@RestClient
	BackendServiceConsumer backendServiceConsumer;

//	private String emartUserServiceUrl;

	@ConfigProperty(name = "service.emart.url")
	String emartUserServiceUrl;
//	public void setServiceShoppingUrl(String url) {
//		setEmartUserServiceUrl(url);
//	}

	@GET
	@Path("/")
	// @RequestMapping(value = "/", method = RequestMethod.GET, headers =
	// "Accept=application/json")
	public void goToHomePage(RoutingContext rc) {
		// return "use context /userdata.html";
		rc.reroute("/userdata.html");
//		request ("redirect:" + "/userdata.html");
	}

	@RequestMapping(value = "/getAllCustomers", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<Customer> getAllCustomers() {
		List<Customer> listOfCustomers = new ArrayList<Customer>();
		try {
			// listOfCustomers = restTemplate.getForObject(getEmartUserServiceUrl() +
			// "getAllUsers/", List.class);
			
			listOfCustomers = backendServiceConsumer.getAllUsers();
		} catch (RestClientException e) {
//			model.addAttribute("ERR", "No Connectivity with Back-end System");
			System.out.println("Exception occurred at getAllCustomers =>" + e.getMessage());
		}

//		model.addAttribute("customer", new Customer());
//		model.addAttribute("listOfCustomers", listOfCustomers);
		return listOfCustomers;
	}

	@RequestMapping(value = "/userServiceBackendConnectivity", method = RequestMethod.GET, headers = "Accept=application/json")
	public ResponseObject userServiceBackendConnectivity() {
		ResponseObject r = new ResponseObject();
		String serviceStatus = "DOWN";
		String connectivityStatus = "DOWN";
		try {
			// connectivityStatus = restTemplate.getForObject(getEmartUserServiceUrl(),
			// String.class);
			System.out.println("getConnectivityStatus service call ===> ");
			connectivityStatus = backendServiceConsumer.getConnectivityStatus();
			System.out.println("result after getConnectivityStatus service call = "+connectivityStatus);
			if ("UserData-service App is ready".equals(connectivityStatus)) {
				serviceStatus = "UP";
			}
		} catch (Exception e) {
			System.out.println("Exception occurred at userServiceBackendConnectivity =>" + e.getMessage());
		}
		r.setMessage(connectivityStatus);
		return r;
	}

	@RequestMapping(value = "/getCustomer/{id}", method = RequestMethod.GET)
	@Produces(MediaType.APPLICATION_JSON)
	public Customer getCustomerById(@PathVariable int id) {
		Customer c = null;
		try {
			// c = restTemplate.getForObject(getEmartUserServiceUrl() + "getUser/" + id,
			// Customer.class);
			c=backendServiceConsumer.getCustomerById(id);
		} catch (RestClientException e) {
			System.out.println("Exception occurred at getCustomerById =>" + e.getMessage());
		}

		return c;
	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.POST, headers = "Accept=application/json")
	public Customer addCustomer(@RequestBody Customer c) {

		String url = getEmartUserServiceUrl() + "addUser/";
		try {
			HttpHeaders headers = new HttpHeaders();
//			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<Customer> request = new HttpEntity<>(c, headers);
//			ResponseEntity<Customer> response = restTemplate.exchange(url, HttpMethod.POST, request,	new ParameterizedTypeReference<Customer>() {	});
			c = backendServiceConsumer.addCustomer(c);
			// TODO
		} catch (RestClientException e) {
			System.out.println("Exception occurred at getCustomerById =>" + e.getMessage());
		}
		return c;

	}

	@RequestMapping(value = "/addCustomer", method = RequestMethod.PUT, headers = "Accept=application/json")
	public Customer updateCustomer(@RequestBody Customer customer) {
		customer = addCustomer(customer);
		System.out.println("Customer updated");
		return customer;

	}

	@RequestMapping(value = "/deleteCustomer/{id}", method = RequestMethod.DELETE, headers = "Accept=application/json")
	public ResponseObject deleteCustomer(@PathVariable("id") int id) {
		ResponseObject r = new ResponseObject();
		String result = "FAIL";
		String uri = getEmartUserServiceUrl() + "deleteUser/" + id;
		HttpHeaders headers = new HttpHeaders();
//		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
		HttpEntity<Customer> entity = new HttpEntity<Customer>(headers);
//		result = restTemplate.exchange(uri, HttpMethod.DELETE, entity, String.class).getBody();
		r.setMessage(result);
		System.out.println(result);
		return r;
	}

//	@RequestMapping(value = "/health", method = RequestMethod.GET, headers = "Accept=application/json")
	@GET
	@Path("/health")
	@Consumes(MediaType.APPLICATION_JSON)
	public String heartbeat() {
		return "CustomerController is Ready to server Request";
	}

	public String getEmartUserServiceUrl() {
		return emartUserServiceUrl;
	}

}
