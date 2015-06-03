package Projet_Tchat_Tier1;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Service extends Remote {
	
	 // Methodes
	 public int creerCompte(String username, String password) throws RemoteException;
	 public int authentification(String username, String password) throws RemoteException;
	 public String[] listerContacts() throws RemoteException;
	 public String[] listerContactsConnectes() throws RemoteException;
	 public String[] trouverNouveauContact(String username) throws RemoteException;
	 public String[] infosProfil(int userid) throws RemoteException;
	 public int supprimerContact(int userId) throws RemoteException;
	 public int ajouterContact(int userId) throws RemoteException;
}


