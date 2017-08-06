package com.springcloud.configserver.eurekaclient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class SentenceController {
	
	@Autowired LoadBalancerClient client;
	
	 @RequestMapping("/sentence")
	  public @ResponseBody String getSentence() {
		 String sentence = "There was a problem assembling the sentence!";
		 
		 try{
				sentence = getWord("noun");
		 }
		 catch(Exception e ){
			 System.out.println(e);
		 }
		 
	    return sentence;
	     /* getWord("LAB-5-SUBJECT") + " "
	      + getWord("LAB-5-VERB") + " "
	      + getW	ord("LAB-5-ARTICLE") + " "
	      + getWord("LAB-5-ADJECTIVE") + " "
	      + getWord("LAB-5-NOUN") + "."
	      ;*/
	  }
	 
	 
	 public String getWord(String service) {
		 	System.out.println("Get Word Service");
		    ServiceInstance instance = client.choose(service);
		    System.out.println("Get Word Service 1"+instance.getUri());
		    return ( new RestTemplate()).getForObject(instance.getUri(), String.class);
	 }
	  
}
