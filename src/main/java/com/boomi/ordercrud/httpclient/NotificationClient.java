package com.boomi.ordercrud.httpclient;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class NotificationClient {

	
	private final RestTemplate restTemplate = new RestTemplate();
	
	private static final Logger log = LoggerFactory.getLogger(NotificationClient.class);
	
	@Value("${httpclient.notification.url}")
	private String notificationUrl;
	
	
	public boolean sendNotification(Long orderId) {
		
		try {
			log.info("Notification Client inside SendNotification Method befor restTemplate Call ith OrderId : ={}",orderId);
	
		restTemplate.postForObject(notificationUrl, orderId, Void.class);
		
		log.info("After the RestTemplate Call Successfull");
		
		return true;
		
		}
		catch (Exception e) {
			
			log.error("Notification Client Send Notification Method Will Faild With Orderid : ={}",orderId);
		
			return false;
		}
		
		}
		
		
}
