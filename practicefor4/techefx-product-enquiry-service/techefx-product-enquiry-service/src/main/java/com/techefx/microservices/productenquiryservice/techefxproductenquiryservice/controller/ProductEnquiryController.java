package com.techefx.microservices.productenquiryservice.techefxproductenquiryservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.techefx.microservices.productenquiryservice.techefxproductenquiryservice.beans.ProductEnquiryBean;
import com.techefx.microservices.productenquiryservice.techefxproductenquiryservice.client.ProductStockClient;

@RestController
public class ProductEnquiryController {

	
	@Autowired
	ProductStockClient client;
	
	@GetMapping("/product-enquiry/name/{name}/availabilty/{availabilty/unit/{unit}}")
	public ProductEnquiryBean getEnquiryOfProduct(@PathVariable String name,
			@PathVariable String availability,
			@PathVariable int unit) {
		
	
		ProductEnquiryBean productEnquiryBean=client.checkProductStock(name, availability);
		double totalPrice=productEnquiryBean.getProductPrice().doubleValue() *unit;
		double discount=productEnquiryBean.getDiscountOffer();
		double discountPrice=totalPrice-totalPrice*discount/100;
	return new ProductEnquiryBean(productEnquiryBean.getId(), name, productEnquiryBean.getProductPrice(), availability, productEnquiryBean.getDiscountOffer(), unit,discountPrice, productEnquiryBean.getPort());
	}
	
}
