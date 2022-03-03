package com.revature.services;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.dao.IUserDao;
import com.revature.dao.UserDaoImpl;
import com.revature.exceptions.RegisterUserFailedException;
import com.revature.models.User;

public class UserService  {

	private static Logger logger = Logger.getLogger(UserService.class);

	// UserService DEPENDS on the UserDaoImpl...so therefore we will inject a
	// dependency into this class

	public IUserDao udao = new UserDaoImpl();

	private static List<User> users;

	
	
	public User register(User u) {

		// extract the username first
		String username = u.getUsername();

		// TO check that the user DOESN'T exist, call findUserByUsername() method and
		// see what it returns
		User user = udao.findByUsername(username); // this now returns either an empty User object OR the fully
															// initialized User object

		// does it exist already? Run some checks on its ID. As is possibleUser's ID > 0
		// or == 0?
		if (user.getId() == 0) { // this means that the user doesn't exist in the DB and we can register it

			logger.info("successfully registered user with username " + username);

			int id = udao.insert(u); // are you maintaining the integrity of the data returned?

			u.setId(id);
			return u; // return the user after its ID has been assigned.

		} else {

			throw new RegisterUserFailedException("Failed to register user because id was not 0");

			// logger.info("User with username " + username + " already exists");
			// return the User object found instead of re-inserting it
			// return possibleUser;
		}
	}

	// login(String username, String password) method
	public User login(String username, String password) {

		// first fetch a User object from the DB by calling
		// udao.findByUsername(username)
		User returnedUser = udao.findByUsername(username);

		// then check that that User's password is == to the password passed to this
		// method
		if (returnedUser.getPwd().equals(password)) { // look into memory address of string returned from DB, and
														// password returned from getPwd();
			logger.info("Successfully logged in the user with username " + username);
			return returnedUser;
		}

		// IF the above if-statement returns false, then we return null and log that the
		// password and username combo was incorrect
		
		logger.info("password and username combination for user" + username + " was incorrect");
		return null;
	}

	public List<User> viewUser(User user) {

		System.out.println("User List");

		Iterator<User> i = udao.findAll().iterator();

		while (i.hasNext()) {

			System.out.println(i.next());
		}

		return udao.findAll();
	}

	// source code
	public boolean updateUser(int id) {

		try (Scanner scan = new Scanner(System.in)) {
			System.out.println("Please select an option to change: " + udao.findById(id));
			System.out.println("USERNAME PASSWORD NAME ACCOUNT");
			String op = scan.next();
			User u = udao.findById(id);

			if (u.getId() == id) {
				
				switch (op) {
				
				case "username": {
					System.out.println("Enter NEW username: ?");
					String name = scan.next();
					u.setUsername(name);
					System.out.println("Succesfully changed username!");
				}
					break;
					
				case "password": {
					System.out.println("Enter NEW password:?");
					String password = scan.next();
					u.setPwd(password);
					System.out.println("Succesfully changed PASSWORD!");
				}

					break;
					
				case "account": {
					System.out.println("1:Create Account");
					System.out.println("2:Delete Account");

					int a = scan.nextInt();
					System.out.println("Succesfully changed ACCOUNT INFO!");

				}
				}
			}

			udao.update(u);
		}
		
		return true;

	}

	public boolean deleteUser(int id) {

		users = udao.findAll();

		for (User u : udao.findAll()) {

			if (u.getId() == id) {

				users.remove(u);

				udao.delete(id);
			}

		}
		return true;

	}

}