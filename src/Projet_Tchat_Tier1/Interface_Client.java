package Projet_Tchat_Tier1;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.net.URLEncoder;
import java.util.Scanner;

import Projet_Tchat_Tier3.User;

public class Interface_Client {

	private Integer loggedInUserId;
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
			System.out.println("Bienvenue sur Twitteur. Vous pouvez :");
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
			System.out.println("2. Voir votre timeline");
			System.out.println("3. Créer un tweet");
			System.out.println("4. Trouver un utilisateur");
			System.out.println("5. Trouver un tweet");
			System.out.println("Entrez votre choix :");
			choix = scanner.nextInt();
			scanner.nextLine();
		}
		switch (choix) {
		case 1:
			showMyProfil();
			break;
		case 2:
			showTimeline();
			break;
		case 3:
			writeTweet();
			break;
		case 4:
			findUser();
			break;
		case 5:
			findTweet();
			break;
		}
		
	}
	
	private void showTimeline() {
		// TODO Auto-generated method stub
		
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


	private void findTweet() {
		// TODO Auto-generated method stub
		
	}

	private void findUser() throws Exception {
		System.out.println("Entrer le nom ou une partie du nom de l'utilisateur à chercher");
		String userToFind = scanner.nextLine();
		List<User> userList = service.searchUser(loggedInUserId, userToFind);
		
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

	private void writeTweet() throws Exception {
		System.out.println("Veuillez saisir votre tweet (Max: 140 caractères)");
		String tweet = scanner.nextLine();
		boolean result = service.tweet(loggedInUserId, URLEncoder.encode(tweet,"UTF-8"));
		while(result == false){
			System.out.println("Le tweet est trop long, resaisissez le...");
			tweet = scanner.nextLine();
			result = service.tweet(loggedInUserId, URLEncoder.encode(tweet,"UTF-8"));
		}
		
		System.out.println("Tweet créer avec succès.");
		showMyProfil();
	}

	private void showMyProfil() throws Exception {
		List<Tweet> tweetList = service.showProfile(loggedInUserId, loggedInUserId);
		int i=1;
		for(Tweet tweet : tweetList){
			Date date = tweet.getPostedOn();
			String content = tweet.getContent();
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			
			System.out.println("###### TWEET N°"+i+" ######");
			System.out.println("Date : "+format.format(date)+"\nContenu : "+content);
			System.out.println(date.toString());
			System.out.println("\n");
			
			i++;
		}
		mainMenuLoggedIn();		
	}

	private void showRegister() throws Exception {
		System.out.println("Entrez un nom d'utilisateur :");
		String username = scanner.next();
		System.out.println("Entrez votre mot de passe :");
		String pass = scanner.next();
		Integer result = service.register(username, pass);
		while (result.equals(-1)) {
			System.err.println("Ce nom d'utilisateur est déjà utilisé. Entrez un autre nom d'utilisateur :");
			username = scanner.next();
			result = service.register(username, pass);
		}
		loggedInUserId = result;
		loggedInUserName = username;
		System.out.println("Vous avez été enregistré avec succès.");
		mainMenuLoggedIn();
	}
	
	private void showLogin() throws Exception{
		System.out.println("Entrez votre nom d'utilisateur :");
		String username = scanner.next();
		System.out.println("Entrez votre mot de passe :");
		String pass = scanner.next();
		Integer result = service.login(username, pass);
		while(result.equals(-1)){
			System.err.println("Username ou password éronné !");
			System.out.println("Entrez votre nom d'utilisateur :");
			username = scanner.next();
			System.out.println("Entrez votre mot de passe :");
			pass = scanner.next();
			result = service.login(username, pass);			
		}
		loggedInUserId = result;
		loggedInUserName = username;
		System.out.println("Connecté");
		mainMenuLoggedIn();
		
	}
	
}
