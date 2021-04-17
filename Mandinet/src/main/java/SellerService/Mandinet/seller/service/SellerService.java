package SellerService.Mandinet.seller.service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.http.HttpStatus;

import SellerService.Mandinet.seller.model.Seller;
import SellerService.Mandinet.seller.repository.SellerRepository;

@Service
public class SellerService {

	@Autowired
	SellerRepository seller;
	
	@Autowired
	MongoTemplate mongoTemplate;

	
	public int createSeller(Seller sellerObj) {
		
		sellerObj.setMail(sellerObj.getMail().toLowerCase());
		seller.save(sellerObj);
		return 1;
	}
	
	public int deleteSeller(String mail) {
		Seller sellerObj = seller.findBymail(mail);
		if(sellerObj == null) {
			System.out.println("ERROR::deleteSeller: Not found record");
			return 0;
		}
		seller.deleteBymail(mail);
		return 1;
	}
	
	public Seller getByMail(String mail) {
		
		return seller.findBymail(mail);
	}
	
	public HttpStatus addToProdLst(String mail, String prodid) {
		
		//seller.findBymail(mail).addToProdlst(prodid);
		Seller seller = getByMail(mail);
		System.out.println(seller);
		List<String> lst = seller.getProdlst();
		System.out.println(lst);
		
		if(lst != null)
			lst.add(prodid);
		else {
			lst = new ArrayList<String>();
			lst.add(prodid);
		}
		
		Query query = new Query();
		query.addCriteria(Criteria.where("mail").is(mail));
		
		Update update = new Update();
		
		update.set("prodlst", lst);
		mongoTemplate.upsert(query, update, Seller.class);
		return HttpStatus.OK;
	}
	
	public HttpStatus removeFromProdLst(String mail, String prodid) {
		
		Seller seller = getByMail(mail);
		System.out.println(seller);
		List<String> lst = seller.getProdlst();
		System.out.println(lst);
		
		if(lst == null) {
			System.out.println("removeFromProdLst::INFO:List is already Empty");
			return HttpStatus.OK;
		}
		else {
			lst = new ArrayList<String>();
			lst.remove(prodid);
		}
		
		Query query = new Query();
		query.addCriteria(Criteria.where("mail").is(mail));
		
		Update update = new Update();
		
		update.set("prodlst", lst);
		mongoTemplate.upsert(query, update, Seller.class);
		
		
		return HttpStatus.OK;
	}
	
	public List<String> getProdLst(String mail) {
		return seller.findBymail(mail).getProdlst();
	}
}
