package com.mk.programiz;

enum UserType {
	PERSONAL, MERCHANT;
}

interface User {

	public void printUser();
}

class UserFactory {

	User createUser(UserType userType) {

		if (userType == UserType.PERSONAL) {
			return new PersonalUser();
		} else if (userType == UserType.MERCHANT) {
			return new MerchantUser();
		}
		return null;
	}

}

class PersonalUser implements User {

	public void printUser() {
		System.out.println("I'm a PersonalUser");
	}

}

class MerchantUser implements User {

	public void printUser() {
		System.out.println("I'm a MerchantUser");
	}

}

class CreateUserFactory {

	public static void main(String[] args) {

		UserFactory uf = new UserFactory();

		User user1 = uf.createUser(UserType.PERSONAL);

		user1.printUser(); // prints I'm a PersonalUser

		User user2 = uf.createUser(UserType.MERCHANT);

		user2.printUser(); // prints "I'm a MerchantUser"
	}

}
