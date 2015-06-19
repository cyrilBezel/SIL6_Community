package Projet_Tchat_Tier3;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name="user")

public class User implements Serializable {
	private static final long serialVersionUID = 1L;

	private int id;
	private String username;
	private String password;
	private String email;
	private List<Integer> contactUserIds;
	private List<Integer> postedTchatIds;
	
	public User() {
		this.id = new Random().nextInt();
		this.contactUserIds = new ArrayList<>();
		this.postedTchatIds = new ArrayList<>();
	}
	public User(String username, String password, String email) {
		this();
		this.username = username;
		this.password = password;
		this.email = email;
	}
	
	// Accesseurs
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public List<Integer> getContactUserIds() {
		return contactUserIds;
	}
	public void setContactUserIds(List<Integer> contactUserIds) {
		this.contactUserIds = contactUserIds;
	}
	public List<Integer> getPostedTchatIds() {
		return postedTchatIds;
	}
	public void setPostedTchatIds(List<Integer> postedTchatIds) {
		this.postedTchatIds = postedTchatIds;
	}

}
