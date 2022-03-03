package com.revature;

import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.User;
import com.revature.services.AccountService;
import com.revature.services.UserService;
import com.revature.exceptions.*;

public class App {

	private static Scanner scan = new Scanner(System.in);
	private static UserService userv = new UserService();
	private static AccountService aserv = new AccountService();
	private static User loggedInEmployee = null;
	private static Account a = null;

	public static void main(String[] args) throws InappropriateCharacterException, UsernameTooShortException {


		while (true) {

			boolean loggedIn = false;
			System.out.println(" ___       __   ___  ___       ___       ___  ________  _____ ______   ________      \n"
					+ "|\\  \\     |\\  \\|\\  \\|\\  \\     |\\  \\     |\\  \\|\\   __  \\|\\   _ \\  _   \\|\\   ____\\     \n"
					+ "\\ \\  \\    \\ \\  \\ \\  \\ \\  \\    \\ \\  \\    \\ \\  \\ \\  \\|\\  \\ \\  \\\\\\__\\ \\  \\ \\  \\___|_    \n"
					+ " \\ \\  \\  __\\ \\  \\ \\  \\ \\  \\    \\ \\  \\    \\ \\  \\ \\   __  \\ \\  \\\\|__| \\  \\ \\_____  \\   \n"
					+ "  \\ \\  \\|\\__\\_\\  \\ \\  \\ \\  \\____\\ \\  \\____\\ \\  \\ \\  \\ \\  \\ \\  \\    \\ \\  \\|____|\\  \\  \n"
					+ "   \\ \\____________\\ \\__\\ \\_______\\ \\_______\\ \\__\\ \\__\\ \\__\\ \\__\\    \\ \\__\\____\\_\\  \\ \n"
					+ "    \\|____________|\\|__|\\|_______|\\|_______|\\|__|\\|__|\\|__|\\|__|     \\|__|\\_________\\\n"
					+ "                                                                         \\|_________|\n"
					+ "                                                                                     \n"
					+ "                                                                                     \n"
					+ " ________  ________  ________   ___  __            ________  ________  ________      \n"
					+ "|\\   __  \\|\\   __  \\|\\   ___  \\|\\  \\|\\  \\         |\\   __  \\|\\   __  \\|\\   __  \\     \n"
					+ "\\ \\  \\|\\ /\\ \\  \\|\\  \\ \\  \\\\ \\  \\ \\  \\/  /|_       \\ \\  \\|\\  \\ \\  \\|\\  \\ \\  \\|\\  \\    \n"
					+ " \\ \\   __  \\ \\   __  \\ \\  \\\\ \\  \\ \\   ___  \\       \\ \\   __  \\ \\   ____\\ \\   ____\\   \n"
					+ "  \\ \\  \\|\\  \\ \\  \\ \\  \\ \\  \\\\ \\  \\ \\  \\\\ \\  \\       \\ \\  \\ \\  \\ \\  \\___|\\ \\  \\___|   \n"
					+ "   \\ \\_______\\ \\__\\ \\__\\ \\__\\\\ \\__\\ \\__\\\\ \\__\\       \\ \\__\\ \\__\\ \\__\\    \\ \\__\\      \n"
					+ "    \\|_______|\\|__|\\|__|\\|__| \\|__|\\|__| \\|__|        \\|__|\\|__|\\|__|     \\|__|    ");
			System.out.println(" ");
			System.out.println("1. Login ");
			System.out.println("2. Register");
			System.out.println("3. Quit");

			int choice = scan.nextInt();

			switch (choice) {

			case 1: {

				System.out.println("Please enter your username");
				scan.next();
				System.out.println("Please enter your password");
				scan.next();

				System.out.println("Account created successfully");
				User u = userv.register(loggedInEmployee);
				System.out.println(u);

			}
				break;

			case 2: {

				System.out.println("Please enter a username");
				String username = scan.next();
				System.out.println("Please enter a password");
				String password = scan.next();
				loggedInEmployee = userv.login(username, password);

				if (loggedInEmployee != null) {
					loggedIn = true;
					System.out.println(loggedInEmployee);

				}
			}
				break;
				
			case 3:
				System.out.println("Quitting");
				break; 

			}

			while (loggedInEmployee.getRole() != null) {

				if (loggedIn) {

					if (loggedInEmployee.getRole().equals("Y")) {

						System.out.println("Entered as Admin user");
						System.out.println(
								"1:view all users\n2:create new user\n3:update a user\n4:delete a user\n5:See all accounts\n6:Log Out");
						int decision = scan.nextInt();

						switch (decision) {

						case 1: {

							userv.viewUser(loggedInEmployee);
						}
							;
							break;

						case 2: {

							System.out.println("Please enter a username");
							scan.next();
							System.out.println("Please enter a password");
							scan.next();
							User user = userv.register(loggedInEmployee);
							System.out.println(user);
						}
							;
							break;

						case 3: {

							System.out.println("Please enter user id for update function");
							int uId = scan.nextInt();

							userv.updateUser(uId);

						}

							;
							break;

						case 4: {

							System.out.println("Please enter user id for delete");
							int uId = scan.nextInt();
						
							userv.deleteUser(uId);
						}
							;
							break;

						case 5: {

							System.out.println("Accounts:");
							aserv.findAll();
						}
							;
							break;

						case 6: {

							loggedIn = false;
						}
							;
							break;

						}

					} else {

						System.out.println("Welcome");
						System.out.println("Accounts:");
						aserv.currentAccount(choice);
						System.out.println(
								"1:Create Account\n2:Delete Account\n3:Deposit\n4:Withdrawl\n5:See All Accounts\n6:Log Out");
						int decision = scan.nextInt();

						switch (decision) {

						case 1: {

							System.out.println("Please enter a account name");
							String accName = scan.next();
							loggedInEmployee.getId();
							a = aserv.openAccount(loggedInEmployee);
							System.out.println(
									"Account created\nName is :" + accName + "\nBank Account ID : " + a.getAccOwner());

						}
							;
							break;

						case 2: {

							System.out.println("Please enter account id for delete");
							int accId = scan.nextInt();
							loggedInEmployee.getId();
							aserv.deleteAccount(accId);

						}
							;
							break;

						case 3: {

							System.out.println("Please enter account id for deposit");
							int accId = scan.nextInt();
							System.out.println("Please enter amount for deposit");

							double amount = scan.nextDouble();
							int userId = loggedInEmployee.getId();
							aserv.deposit(amount, userId);
							System.out.println(amount + " added to account number " + accId);

						}
							;
							break;

						case 4: {

							System.out.println("Please enter account id for withdraw");
							int accId = scan.nextInt();
							System.out.println("Please enter amount for withdraw");

							double amount = scan.nextDouble();
							loggedInEmployee.getId();
							aserv.withdraw(amount, accId);

						}
							;
							break;

						case 5: {

							System.out.println("Accounts:");
							aserv.currentAccount(loggedInEmployee.getId());
						}
							;
							break;

						case 6: {

							loggedIn = false;
						}
							;
							break;

						}
					}

				}

			}

		}

	}

}