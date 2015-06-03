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
	private List<Integer> contactUserIds;
	private List<Integer> postedTchatIds;
	
	public User() {
		this.id = new Random().nextInt();
		this.contactUserIds = new ArrayList<>();
		this.postedTchatIds = new ArrayList<>();
	}
	public User(String username, String password) {
		this();
		this.username = username;
		this.password = password;
	}

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
	public List<Integer> getFollowedUserIds() {
		return contactUserIds;
	}
	public void setFollowedUserIds(List<Integer> followedUserIds) {
		this.contactUserIds = followedUserIds;
	}
	public List<Integer> getPostedTweetIds() {
		return postedTchatIds;
	}
	public void setPostedTweetIds(List<Integer> postedTweetIds) {
		this.postedTchatIds = postedTweetIds;
	}

}
