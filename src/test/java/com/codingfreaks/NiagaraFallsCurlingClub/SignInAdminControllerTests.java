package com.codingfreaks.NiagaraFallsCurlingClub;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.codingfreaks.NiagaraFallsCurlingClub.controllers.SignInAdminController;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.UserRepository;

@SpringBootTest
class SignInAdminControllerTests {
	SignInAdminController signInAdminController = new SignInAdminController();
	@Autowired
	UserRepository userRepo;

	@SuppressWarnings("static-access")
	@Test
	void testEmailAddressGood() {
		String emailAddress = "testadmin@admin.com";
		boolean result = SignInAdminController.isValidEmail(emailAddress);
		assertTrue(result);
	}

	@SuppressWarnings("static-access")
	@Test
	void testEmailAddressBad() {
		String emailAddress = null;
		boolean result = signInAdminController.isValidEmail(emailAddress);
		assertFalse(result);
	}

	@SuppressWarnings("static-access")
	@Test
	void testEmailAddressBoundaryIn() {
		String emailAddress = "test@admin.ca";
		boolean result = signInAdminController.isValidEmail(emailAddress);
		assertTrue(result);
	}

	@SuppressWarnings("static-access")
	@Test
	void testEmailAddressBoundaryOut() {
		String emailAddress = "test!admin.ca";
		boolean result = signInAdminController.isValidEmail(emailAddress);
		assertFalse(result);
	}
}
