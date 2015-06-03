package Projet_Tchat_Tier3;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Persistence {
	
	private final static String USER_FILE_PATH = "users.xml";
	private final static String CONVERSATION_FILE_PATH = "data/tier3/%d_tweets.xml";

	/** Le cache mémoire des utilisateurs */
	private Map<Integer, User> users;
	/** Le cache mémoire des conversations */
	private Map<Integer, List<Conversation>> conversations;

	/** Le service de sérialisation/désérialisation */
	private XmlSerializer serializer;
	
	public Persistence(XmlSerializer serializer) {
		this.serializer = serializer;
		this.loadUsers();
		this.loadConversations();
		System.out.println("[Tier3:Persistence] Service started");
	}
	
	/** Retourne la Map contenant les utilisateurs */
	public Map<Integer, User> getUsers() {		
		return users;
	}

	/** Retourne la Map contenant les conversations des utilisateurs */
	public Map<Integer, List<Conversation>> getConversations() {
		return conversations;
	}
	
	/** Persiste les utilisateurs de la HashMap users dans le fichier USER_FILE_PATH */
	public void writeUsers() {	
		File outputFile = new File(USER_FILE_PATH);
		Collection<User> userList = users.values();
		serializer.marshallUsers(userList, outputFile);
	}
	
	/**
	 * Persiste les conversations de l'utilisateur dont l'id est passé en paramètre
	 * @param userId : Id de l'utilisateur dont on veut sauvegarder les conversations
	 */
	public void writeConversation(int userId){
		File outputFile = new File(String.format(CONVERSATION_FILE_PATH, userId));
		List<Conversation> conversationList = conversations.get(userId);
		serializer.marshallConversations(conversationList, outputFile);
	}

	/** Charge les utilisateurs depuis le fichier USER_FILE_PATH dans la HashMap users */
	public void loadUsers() {
		this.users = new HashMap<>();
		File inputFile = new File(USER_FILE_PATH);
		List<User> unmarshalledUsers = serializer.unmarshallUsers(inputFile);
		for (User u : unmarshalledUsers) {
			this.users.put(u.getId(), u);
		}
	}

	/** Charge les conversations depuis le système de fichiers dans la HashMap conversations */
	public void loadConversations() {
		this.conversations = new HashMap<>();
		for (int userId : this.users.keySet()) {
			File inputFile = new File(String.format(CONVERSATION_FILE_PATH, userId));
			List<Conversation> unmarshalledTweets = serializer.unmarshallConversation(inputFile);
			conversations.put(userId, unmarshalledTweets);
		}
	}

	/** Supprime les conversations de l'utilisateur dont l'id est passé en paramètre */
	public void deleteConversations(int userId) {
		conversations.remove(userId);
		File file = new File(String.format(CONVERSATION_FILE_PATH, userId));
		if (!file.delete()) {
			file.deleteOnExit();
		}
	}

}
