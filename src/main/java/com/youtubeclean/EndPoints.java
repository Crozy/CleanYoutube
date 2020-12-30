package com.youtubeclean;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.GeneralSecurityException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.youtubeclean.google.GoogleService;
import com.youtubeclean.models.ChannelYoutubeClean;
import com.youtubeclean.service.YoutubeService;

@Controller
public class EndPoints {
	
	@Autowired
	GoogleService googleService;
	
	@Autowired
	YoutubeService youtubeService;
	
	  @GetMapping("/test")
	  @ResponseBody
	  public List<ChannelYoutubeClean> sayHello(
			  @RequestParam(name="name", required=false, defaultValue="Stranger") String name) throws GoogleJsonResponseException, GeneralSecurityException, IOException, URISyntaxException {
		  //this.googleService.getListChannel("");
		  
		  return youtubeService.listChannelSubscribed();
	  }
	  
	  @GetMapping("/confirm")
	  @ResponseBody
	  public String confirmAuth() {
		  return "OK";
	  }
	
}
