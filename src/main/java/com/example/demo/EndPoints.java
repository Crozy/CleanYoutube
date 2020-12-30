package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.google.GoogleService;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;

@Controller
public class EndPoints {
	
	@Autowired
	GoogleService googleService;
	
	  @GetMapping("/test")
	  @ResponseBody
	  public String sayHello(
			  @RequestParam(name="name", required=false, defaultValue="Stranger") String name) throws GoogleJsonResponseException, GeneralSecurityException, IOException, URISyntaxException {
		  this.googleService.getListChannel("");
		  return "OK";
	  }
	  
	  @GetMapping("/confirm")
	  @ResponseBody
	  public String confirmAuth() {
		  return "OK";
	  }
	
}
