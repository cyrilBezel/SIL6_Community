package Projet_Tchat_Tier3;

import java.io.Serializable;
import java.util.Date;
import java.util.Random;

public class Conversation implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributs
	private int id;
	private String content;
	private Date startOn;
	private int authorId;
	
	// Constructeurs
	public Conversation() {
		this.id = new Random().nextInt();
		this.startOn = new Date();
	}
	public Conversation(String content, int authorId) {
		this();
		this.content = content;
		this.authorId = authorId;
	}
	public Conversation(int id, String content, Date postedOn, int authorId) {
		this.id = id;
		this.content = content;
		this.startOn = postedOn;
		this.authorId = authorId;
	}
	
	// Accesseurs
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getPostedOn() {
		return startOn;
	}
	public void setPostedOn(Date postedOn) {
		this.startOn = postedOn;
	}
	public int getAuthorId() {
		return authorId;
	}
	public void setAuthorId(int authorId) {
		this.authorId = authorId;
	}
}