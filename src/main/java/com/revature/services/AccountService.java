package com.revature.services;

import static org.mockito.Mockito.RETURNS_DEEP_STUBS;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.AccountDaoImpl;
import com.revature.dao.IAccountDao;
import com.revature.exceptions.RegisterUserFailedException;
import com.revature.models.Account;
import com.revature.models.User;

public class AccountService extends AccountDaoImpl {

	// this is the dependency that the service uses to perform CRUD operations on
	// Account
	// objects

	private static Scanner scan = new Scanner(System.in);

	public IAccountDao adao = new AccountDaoImpl();

	// The Service layer depends on the DAO to perform crud operations, and adds
	// extra business logic

	/**
	 * Maybe in some menu, a user has the option to enter a number that will invoke
	 * the method. Then we pass the user object (of the user who's opening the
	 * account) into the method -- the accOwner id == user's id
	 * 
	 */

	public Account openAccount(User u) {

		// prompt the user to enter an initial deposit amount
		System.out.println("Please enter the initial amount you would like to deposit:");

		// capture the deposit value
		double deposit = scan.nextDouble();

		// instantiate an Account with the initial deposit
		Account a = new Account(deposit, u.getId(), false); // it's inactive until a manager looks at

		// call the insert method and return the Primary Key generated by the DB
		int pk = adao.insert(a);

		// set the acount's id = to the PK returned
		a.setId(pk);

		// return the account object
		return a;
	}

	public List<Account> findByOwner(int userId) {

		return adao.findByOwner(userId);

	}
	
	public void currentAccount(int userId) {

		for(Account a : adao.findAll()) {
			if (a.getId( ) == userId) {
				System.out.println(a);
				
			}
		}
		
	}

	public Account deposit(double depositAmount, int id) {

		System.out.println("Please enter the amount you would like to deposit:");

		double deposit = scan.nextDouble();

		for (Account a : adao.findAll()) {

			if (a.getId() == id) {

				a.setBalance(deposit + a.getBalance());

				adao.updateAccount(a);

				System.out.println(deposit + " Desposited ");

				return a;
			}
		}

		return null;
	}

	public boolean withdraw(double withdrawAmount, int accOwner) {

		for (Account a : adao.findAll()) {

			if (a.getAccOwner() == accOwner) {

				if (a.getBalance() == withdrawAmount || a.getBalance() > withdrawAmount) {

					a.setBalance(a.getBalance() - withdrawAmount);
					adao.updateAccount(a);
					System.out.println(withdrawAmount + " has been withdrawn from Account " + accOwner);

				} else {
					System.out.println("There is not enough money in the account.");
				}
			}
		}
		return true;
	}

	public boolean transfer(double transferAmount) {

		System.out.println("Please enter the amount you would like to transfer: ");
		
		
		
		return false;
	}

	public boolean deleteAccount(int id) {

		System.out.println("Please enter the username for the account you would like to delete: ");

		boolean deleteAccount = scan.hasNext();

		for (Account a : adao.findAll()) {

			if (a.getId() == id) {

				if (a.getBalance() == 0) {

					adao.delete(id);

					return deleteAccount;

				} else {

					throw new RegisterUserFailedException("Failed to delete user because id was not found ");

				}
			}

		}
		return true;
	}

}