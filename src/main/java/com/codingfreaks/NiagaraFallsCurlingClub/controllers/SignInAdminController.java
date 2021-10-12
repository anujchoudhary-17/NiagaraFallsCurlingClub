package com.codingfreaks.NiagaraFallsCurlingClub.controllers;

import java.util.Map;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

import com.codingfreaks.NiagaraFallsCurlingClub.modelClasses.Admin;
import com.codingfreaks.NiagaraFallsCurlingClub.repositories.AdminRespository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class SignInAdminController {

	@Autowired(required = true)
	private AdminRespository adminRepo;

	public static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
			Pattern.CASE_INSENSITIVE);

	@RequestMapping(value = "/signinadmin", method = RequestMethod.GET)
	public String signIn(Model model) {

		return "views/signInAdmin";
	}

	@RequestMapping(value = "/signinadmin", method = RequestMethod.POST)
	public String save(ModelMap model, @RequestParam String email, @RequestParam String password,
			Map<String, Object> map, RedirectAttributes redirectAttrs) {

		if (!validateUserData(model, email, password)) {

			return "redirect:signinadmin";
		} else {
			Admin adminData = adminRepo.emailExists(email);
			model.addAttribute("adminData", adminData).addAttribute("adminId", adminData.getAdminId());
			System.out.println("ADMIN ID IS " + adminData.getAdminId());

			redirectAttrs.addAttribute("aid", adminData.getAdminId());
			return "redirect:adminHome";
			// +userData.getUserId()
		}
	}

	public boolean validateUserData(ModelMap model, String email, String password) {

		if (emailCheck(email, adminRepo)) {
			Admin adminData = adminRepo.emailExists(email);
			if (password.equals(adminData.getPassword())) {
				System.out.println("Hey Password got matched");
				return true;
			} else {
				System.out.println("Hey Password did not match");
				model.put("errorMessage", "Incorrect Password");
			}
			return false;

		} else {
			model.put("errorMessage", "Invalid Email.");
			return false;
		}
	}

	public static boolean emailCheck(String emailAddress, AdminRespository adminRepo) {
		if (isValidEmail(emailAddress))
			return adminRepo.emailExists(emailAddress) != null;
		else
			return false;
	}

	public static boolean isValidEmail(String emailAddress) {
		
		if(emailAddress==null || emailAddress.length()==0)
			return false;
		
		
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailAddress);
		if (matcher.matches()) {
			return true;
		} else {
			return false;
		}
	}

}