package SellerService.Mandinet.seller.model;

import java.util.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;

@Document
public class Seller {

	private String name;
	private String contactno;
	private String address;
	
	//mail it self for uniq identification
	
	@Indexed(name="mail",unique=true)
	private String mail;
	
	private List<String> prodlst;
	private String password;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContactno() {
		return contactno;
	}
	public void setContactno(String contactno) {
		this.contactno = contactno;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public List<String> getProdlst() {
		return prodlst;
	}
	public void setProdlst(List<String> prodlst) {
		this.prodlst = prodlst;
	}
	
	public void addToProdlst(String prodlst) {
		this.prodlst.add(prodlst);
	}
	
	public void removeFromProdLst(String prodlst) {
		this.prodlst.remove(prodlst);
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
