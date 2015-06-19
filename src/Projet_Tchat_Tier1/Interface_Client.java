package Projet_Tchat_Tier1;

import java.util.List;
import java.util.Scanner;

import Projet_Tchat_Tier3.User;

public class Interface_Client {

	private String loggedInUserName;
	
	private Scanner scanner;
	
	private Service service;

	public Interface_Client(Service service) throws Exception {
		this.service = service;
		this.scanner = new Scanner(System.in);
		this.mainMenuNotLoggedIn();
	}

	private void mainMenuNotLoggedIn() throws Exception {
		int choix = -1;
		while (choix < 1 || choix > 2) {
			System.out.println("Bienvenue sur le Tchat. Vous pouvez :");
			System.out.println("1. Vous enregistrer");
			System.out.println("2. Vous connecter");
			System.out.println("Entrez votre choix :");
			choix = scanner.nextInt();
			scanner.nextLine();
		}
		switch (choix) {
		case 1:
			showRegister();
			break;
		case 2:
			showLogin();
			break;
		}
	}
	
	private void mainMenuLoggedIn() throws Exception {
		int choix = -1;
		while (choix < 1 || choix > 5) {
			System.out.println("Bonjour "+loggedInUserName+". Vous pouvez :");
			System.out.println("1. Voir votre profil");
			System.out.println("2. Liste contacts connectés");
			System.out.println("3. Liste contacts");
			System.out.println("4. Trouver un utilisateur");
			System.out.println("Entrez votre choix :");
			choix = scanner.nextInt();
			scanner.nextLine();
		}
		switch (choix) {
		case 1:
			voirProfil();
			break;
		/*case 2:
			listeConnectes();
			break;
		case 3:
			listeContacts();
			break;*/
		case 4:
			trouverUtilisateur();
			break;
		}
		
	}

	private void menuUserAction(User userFind) throws Exception {
		int choix = -1;
		while (choix < 1 || choix > 2) {
			System.out.println("Pour l'utilisateur "+userFind.getUsername()+". Vous pouvez :");
			System.out.println("1. Voir son profil");
			System.out.println("2. Vous abonner");			
			System.out.println("Entrez votre choix :");
			choix = scanner.nextInt();
			scanner.nextLine();
		}
		switch (choix) {
		case 1:
			
			break;
		case 2:
			
			break;		
		}
		
	}

	private void trouverUtilisateur() throws Exception {
		System.out.println("Entrer le nom ou une partie du nom de l'utilisateur à chercher");
		String userToFind = scanner.nextLine();
		List<User> userList = service.trouverNouveauContact(userToFind);
		
		if(userList.size()>0){
			int i = 1;
			System.out.println("O: Revenir au menu");
			for (User user : userList) {
				System.out.println(i+": "+user.getUsername());
			}
			Integer choice = -1;
			while (choice < 0 || choice > userList.size()) {
				System.out.println("Entrer le numéro correspondant à l'utilisateur que vous recherchez");				
				choice = scanner.nextInt();
				scanner.nextLine();
			}
			if (choice.equals(0)) {
				mainMenuLoggedIn();
			} else {
				User user = userList.get(choice - 1);
				menuUserAction(user);
			}
		} else {
			System.out.println("Aucun utilisateur ne correspond à votre recherche");
			mainMenuLoggedIn();
		}
	}

	private void voirProfil() throws Exception {
		System.out.println("Mon Profil");
		mainMenuLoggedIn();		
	}

	private void showRegister() throws Exception {
		System.out.println("Entrez un nom d'utilisateur :");
		String username = scanner.next();
		System.out.println("Entrez votre mot de passe :");
		String pass = scanner.next();
		System.out.println("Entrez votre email :");
		String email = scanner.next();
		boolean result = service.creerCompte(username, pass, email);
		while (!result) {
			System.err.println("Ce nom d'utilisateur est déjà utilisé. Entrez un autre nom d'utilisateur :");
			username = scanner.next();
			result = service.creerCompte(username, pass, email);
		}
		loggedInUserName = username;
		System.out.println("Vous avez été enregistré avec succès.");
		mainMenuLoggedIn();
	}
	
	private void showLogin() throws Exception{
		System.out.println("Entrez votre nom d'utilisateur :");
		String username = scanner.next();
		System.out.println("Entrez votre mot de passe :");
		String pass = scanner.next();
		boolean result = service.authentification(username, pass);
		while(!result){
			System.err.println("Username ou password éronné !");
			System.out.println("Entrez votre nom d'utilisateur :");
			username = scanner.next();
			System.out.println("Entrez votre mot de passe :");
			pass = scanner.next();
			result = service.authentification(username, pass);			
		}
		loggedInUserName = username;
		System.out.println("Connecté");
		mainMenuLoggedIn();
	}
}
