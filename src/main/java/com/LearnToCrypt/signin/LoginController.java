package com.LearnToCrypt.signin;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.DAO.DAOAbstractFactory;
import com.LearnToCrypt.DAO.IUserDAO;

@Controller
public class LoginController implements WebMvcConfigurer {
	
	ValidateUserCredentials validateUserCredentials;
	DAOAbstractFactory abstractFactory;
	
    public LoginController() {
    	validateUserCredentials = new ValidateUserCredentials();
    	abstractFactory = new DAOAbstractFactory();
    }
    
	@GetMapping("/login")
    public String displayLogin(ModelMap model) {
		
		model.addAttribute("user", new User());
		
		return "login.html";
    }
	
	@PostMapping("/login")
    public String showDashboard(ModelMap model, User user, RedirectAttributes redirectAttributes) {
		
		// Credential validations will go here.
		boolean isUserValid = validateUserCredentials.validateCredentials(user);
        
		if (!isUserValid) {
            model.put("invalidLogin", "Invalid Credentials");
            return "login";
        }
		
		IUserDAO userDAOName = abstractFactory.createUserDAO();
		String userName = userDAOName.getUserName(user.getEmail());
		
		redirectAttributes.addFlashAttribute("username", userName);
		
        return "redirect:/dashboard";
    }
}