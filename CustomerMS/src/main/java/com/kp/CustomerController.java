package com.kp;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class CustomerController {
ArrayList<Customer> allCustomers=new ArrayList<Customer>();
	
	// Dependency Injection
	@Autowired
	RestTemplate restTemplate;
	@Autowired
    private DiscoveryClient discoveryClient;
	
	public CustomerController() {
		System.out.println("Preparing the customer details in CustomerController");
		int[] stocksList1= {1,2};
		int[] stocksList2= {2,3};
		int[] stocksList3= {3,4,5};
		int[] stocksList4= {1,2,4,5};
		int[] stocksList5= {5};
		
		Customer c1=new Customer(1, "Guru", "Murthy", 9731801675L, "java.guru@yahoo.com", stocksList1);
		Customer c2=new Customer(2, "Rohit", "Sharma", 56789762, "rohit@yahoo.com", stocksList2);
		Customer c3=new Customer(3, "Pradeep", "Patel", 98767890L, "pradeep@yahoo.com", stocksList3);
		Customer c4=new Customer(4, "Sarif", "Rana", 456789765L, "sarif@yahoo.com", stocksList4);
		Customer c5=new Customer(5, "Yamini", "Rajula", 4567897, "yamini@yahoo.com", stocksList5);
		
		allCustomers.add(c1);
		allCustomers.add(c2);
		allCustomers.add(c3);
		allCustomers.add(c4);
		allCustomers.add(c5);
	}
	
	// API 1 -returning all customers
	@RequestMapping(value="/customers", method=RequestMethod.GET)
	public ArrayList<Customer> getAllcustomers()
	{
		return allCustomers;
	}
	
	// API2
	@RequestMapping(value="/customers/id/{custId}", method=RequestMethod.GET)
	public ResponseEntity<Object> getACustomer(@PathVariable("custId") int custId) {
		for (Customer c:allCustomers) {
			if (c.getCustomerId()==custId) {
				// success condition
				return ResponseEntity.status(200).body(c);
			}
		}
		// failure condition
		return ResponseEntity.status(404).body("The customer id "+custId+" is not present");
	}
	
	//API 3 (service-to-service communication using RestTemplate)
	// For a given customer id, it should return an ArrayList of Stock objects with the details
	// The details are fetched from other microservice
	// End Point: /customers/id/{custId}/stocks
	@RequestMapping(value="/customers/id/{custId}/stocks", method=RequestMethod.GET)
	public ArrayList<Object> getStockDetails(@PathVariable("custId") int custId) {
		ArrayList<Object> allStocksForAGivenCustomerId=new ArrayList(); // empty
		int stockIds[]=null;
		Object stockTemp=null;
		
		// check if the customer id exists, if not return null straight away
		for (Customer c:allCustomers) {
			if (c.getCustomerId()==custId) {
				// customer id is matching, retrieve the stock ids for this customer
				stockIds=c.getStockIds(); // [1,2,3]
				break; // come out of the loop
			}
		}
		
		if (stockIds==null) {
			return allStocksForAGivenCustomerId; // empty array list
		} else {
			// first print their stock ids
			for (int i=0;i<stockIds.length;i++) {
				System.out.println("Stock id is "+stockIds[i]);
				stockTemp=getAStockObject(stockIds[i]);
				allStocksForAGivenCustomerId.add(stockTemp);
			}
		}
		
		return allStocksForAGivenCustomerId;
	}

	private Object getAStockObject(int stockId) {
		// From here, I am going to call the other microservice's end point
		// The input is stockId (integer) and output is Stock object
		// to access the other microservices / external APIs / to make a remote method call
		// Spring boot provides us a pre-defined class called RestTemplate
		System.out.println(discoveryClient.getServices());

		return restTemplate.getForObject("http://stockms/stocks/{stockId}", Object.class, stockId);
	}

}
