package com.kp;

import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class StockController {
	ArrayList<Stock> allStocks=new ArrayList<>();
	
	public StockController() {
		Stock st1=new Stock(1,"TCS",1000);
		Stock st2=new Stock(2,"Infosys",2000);
		Stock st3=new Stock(3,"Wipro",3000);
		Stock st4=new Stock(4,"HCL",4000);
		Stock st5=new Stock(5,"TechM",5000);
		Stock st6=new Stock(6,"CTS",6000);
		allStocks.add(st1);
		allStocks.add(st2);
		allStocks.add(st3);
		allStocks.add(st4);
		allStocks.add(st5);
		allStocks.add(st6);
		System.out.println("All stocks are initialized....");
	}
	
	@GetMapping("/stocks")
	public ArrayList<Stock> getAllStocks() {
		return allStocks;
	}
	
	@GetMapping("/stocks/{stockId}")
	public Stock getAStockById(@PathVariable("stockId") int stockId) {
		for (Stock st:allStocks) {
			if (st.getStockId()==stockId)
				return st;
		}
		return null;
	}
}

