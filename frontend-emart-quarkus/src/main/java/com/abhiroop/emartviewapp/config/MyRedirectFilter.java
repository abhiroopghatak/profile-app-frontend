package com.abhiroop.emartviewapp.config;

import io.vertx.ext.web.RoutingContext;
public class MyRedirectFilter {

	
	
	void myRedirector(RoutingContext rc) {
	  String uri = rc.request().uri();
	 
	  if (uri.startsWith("BASE_PATH/v1/")) {
	    String remain = uri.substring("BASE_PATH/v1/".length());
	   
	    rc.reroute("BASE_PATH/v1.0/" +remain);
	    return; 
	  }
	  rc.next();
	}
}
