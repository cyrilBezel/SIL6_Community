package Projet_Tchat_Tier3;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class Conversation implements Serializable {
	private static final long serialVersionUID = 1L;

	// Attributs
	private List<String> content;
	private int authorId1;
	private int authorId2;
	
	
	// Constructeurs
	public Conversation(List<String> content, int authorId1, int authorId2) {
		this.content = content;
		this.authorId1 = authorId1;
		this.authorId2 = authorId2;
	}

	// Accesseurs
	public List<String> getContent() {
		return content;
	}
	public void setContent(List<String> content) {
		this.content = content;
	}
	public int getAuthorId1() {
		return authorId1;
	}
	public void setAuthorId1(int authorId1) {
		this.authorId1 = authorId1;
	}
	public int getAuthorId2() {
		return authorId2;
	}
	public void setAuthorId2(int authorId2) {
		this.authorId2 = authorId2;
	}
}