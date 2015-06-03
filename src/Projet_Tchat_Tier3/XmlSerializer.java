package Projet_Tchat_Tier3;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class XmlSerializer {

	private Marshaller userMarshaller;
	private Marshaller conversationMarshaller;
	private Unmarshaller userUnmarshaller;
	private Unmarshaller conversationUnmarshaller;
	
	/** 
	 * Constructeur par défaut qui initialise les Marshallers et Unmarshallers nécessaires à la sérialisation et
	 * désérialisation des conversations et des utilisateurs.
	 */
	public XmlSerializer() throws JAXBException {
		JAXBContext userContext = JAXBContext.newInstance(UserListXml.class);
		userMarshaller = userContext.createMarshaller();
		userMarshaller.setProperty("jaxb.encoding", "UTF-8");
		userMarshaller.setProperty("jaxb.formatted.output", true);
		userUnmarshaller = userContext.createUnmarshaller();
		
		JAXBContext conversationContext = JAXBContext.newInstance(ConversationsListXml.class);
		conversationMarshaller = conversationContext.createMarshaller();
		conversationMarshaller.setProperty("jaxb.encoding", "UTF-8");
		conversationMarshaller.setProperty("jaxb.formatted.output", true);
		conversationUnmarshaller = conversationContext.createUnmarshaller();
		
		System.out.println("[Tier3:XmlSerializer] Service started");
	}

	/** Charge et désérialise les utilisateurs depuis le fichier inputFile */
	public List<User> unmarshallUsers(File inputFile) {
		if (!inputFile.exists()) {
			return new ArrayList<User>();
		}
		try {
			UserListXml xmlUusers = (UserListXml) userUnmarshaller.unmarshal(inputFile);
			return xmlUusers.getUsers();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	/** Charge et désérialise les conversations du fichier inputFile */
	public List<Conversation> unmarshallConversation(File inputFile) {
		if (!inputFile.exists()) {
			return new ArrayList<Conversation>();
		}
		try {
			ConversationsListXml xmlConversation = (ConversationsListXml) conversationUnmarshaller.unmarshal(inputFile);
			return xmlConversation.getConversations();
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	/** Sérialise et enregistre les utilisateurs passés en paramètre dans le fichier outputFile */
	public void marshallUsers(Collection<User> users, File outputFile) {
		UserListXml userListXml = new UserListXml(users);
		try {
			userMarshaller.marshal(userListXml, outputFile);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	/** Sérialise et enregistre les tweets passés en paramètre dans le fichier outputFile */
	public void marshallConversations(Collection<Conversation> conversations, File outputFile) {
		ConversationsListXml conversationListXml = new ConversationsListXml(conversations);
		try {
			conversationMarshaller.marshal(conversationListXml, outputFile);
		} catch (JAXBException e) {
			throw new RuntimeException(e);
		}
	}
	
	
	
	/** Classe pour la sérialisation/désérialisation XML des utilisateurs */
	@XmlRootElement(name="users") @XmlAccessorType(XmlAccessType.FIELD)
	private static class UserListXml {
		@XmlElement(name="user")
		List<User> users;
		@SuppressWarnings("unused") public UserListXml() { }
		public UserListXml(Collection<User> users) {
			this.users = new ArrayList<>(users);
		}
		public List<User> getUsers() {
			return users != null ? users : new ArrayList<User>();
		}
	}

	/** Classe pour la sérialisation/désérialisation XML des conversations */
	@XmlRootElement(name="conversations") @XmlAccessorType(XmlAccessType.FIELD)
	private static class ConversationsListXml {
		@XmlElement(name="conversation")
		List<Conversation> conversations;
		@SuppressWarnings("unused") public ConversationsListXml() { }
		public ConversationsListXml(Collection<Conversation> conversations) {
			this.conversations = new ArrayList<>(conversations);
		}
		public List<Conversation> getConversations() {
			return conversations != null ? conversations : new ArrayList<Conversation>();
		}
	}

}
