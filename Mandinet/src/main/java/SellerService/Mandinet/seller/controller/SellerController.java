package SellerService.Mandinet.seller.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import SellerService.Mandinet.seller.model.Seller;
import SellerService.Mandinet.seller.service.SellerService;

@RestController
public class SellerController {

	@Autowired
	SellerService sellerservice;
	
	@RequestMapping(value="/seller/create", method=RequestMethod.POST)
	public HttpStatus create(@RequestBody Seller sellerobj) {
		if(sellerservice.createSeller(sellerobj) == 1) {
			return HttpStatus.OK;
		}
		return HttpStatus.NOT_FOUND;
	}
	
	@RequestMapping(value="/seller/delete", method=RequestMethod.DELETE)
	public HttpStatus delete(@RequestParam String mail) {
		if(sellerservice.deleteSeller(mail) == 1) {
			return HttpStatus.OK;
		}
		return HttpStatus.NOT_FOUND;
	}
	
	@RequestMapping(value="/seller/getByMail", method=RequestMethod.GET)
	public Seller getByMail(@RequestParam String mail) {
		Seller sellerobj = sellerservice.getByMail(mail);
		
		if(sellerobj == null) {
			System.out.println("ERROR::getByMail:not found seller");
			return null;
		}
		return sellerobj;
	}
	
	@RequestMapping(value="/seller/addToProdLst", method=RequestMethod.GET)
	public HttpStatus addToProdLst(@RequestParam String mail, @RequestParam String prodid) {
	
		HttpStatus ret = sellerservice.addToProdLst(mail, prodid);
		return ret;
	}
	
	@RequestMapping(value="/seller/removeFromProdLst", method=RequestMethod.GET)
	public HttpStatus removeFromProdLst(@RequestParam String mail, @RequestParam String prodid) {
	
		HttpStatus ret = sellerservice.removeFromProdLst(mail,  prodid);
		return ret;
	}
	
	@RequestMapping(value="/seller/getProdLst", method=RequestMethod.GET)
	public List<String> getProdLst(@RequestParam String mail) {
		return sellerservice.getProdLst( mail);
	}
	
}
