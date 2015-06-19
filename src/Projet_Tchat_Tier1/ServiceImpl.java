package Projet_Tchat_Tier1;

import java.util.HashMap;
import java.util.List;
import java.rmi.RemoteException;

import Projet_Tchat_Tier3.User;

public class ServiceImpl implements Service
{
	 private static final HashMap<String,String> conditions;
	 private static final HashMap<String,Integer> temperatures;
	 private static final String [] villes = {"Grenoble", "Lyon", "Lille", "Paris", "Brest"};
	 private static int compteur = 0;
	 
	 static
	 {
	  conditions = new HashMap<String,String>();
	  conditions.put("Grenoble","soleil");
	  conditions.put("Lille","pluie");
	  conditions.put("Paris","nuages");
	  conditions.put("Brest","vent");
	  conditions.put("Lyon","eclaircies");
	  
	  temperatures = new HashMap<String,Integer>();
	  temperatures.put("Grenoble", 25);
	  temperatures.put("Lille", 12);
	  temperatures.put("Paris", 20);
	  temperatures.put("Brest", 18);
	  temperatures.put("Lyon", 22);  
	 }

	@Override
	public boolean creerCompte(String username, String password, String email) throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public boolean authentification(String username, String password)
			throws RemoteException {
		// TODO Auto-generated method stub
		return true;
	}
	
	@Override
	public String[] listerContacts() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String[] listerContactsConnectes() throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String[] infosProfil(int userid) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int supprimerContact(int userId) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public int ajouterContact(int userId) throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<User> trouverNouveauContact(String username)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<String> chargerConversation(int userId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}
