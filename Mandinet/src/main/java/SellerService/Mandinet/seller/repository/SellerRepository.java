package SellerService.Mandinet.seller.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import SellerService.Mandinet.seller.model.Seller;

/*
 * 	private String name;
	private String contactno;
	private String address;
	
	//mail it self for uniq identification
	private String mail;
	private List<String> prodlst;
	private String password;
 * 
 */
public interface SellerRepository extends MongoRepository<Seller, String>{

	public Seller findBymail(String mail);
	public List<Seller> findByname(String name);
	public List<Seller> findBycontactno(String contactno);
	public void deleteBymail(String mail);
	
	
}
