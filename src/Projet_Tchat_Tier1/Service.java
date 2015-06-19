package Projet_Tchat_Tier1;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import Projet_Tchat_Tier3.User;

public interface Service extends Remote {
	
	 // Methodes
	 public boolean creerCompte(String username, String password, String email) throws RemoteException;
	 public boolean authentification(String username, String password) throws RemoteException;
	 public String[] listerContacts() throws RemoteException;
	 public String[] listerContactsConnectes() throws RemoteException;
	 public List<User> trouverNouveauContact(String username) throws RemoteException;
	 public List<String> chargerConversation(int userId) throws RemoteException;
	 public String[] infosProfil(int userid) throws RemoteException;
	 public int supprimerContact(int userId) throws RemoteException;
	 public int ajouterContact(int userId) throws RemoteException;
}