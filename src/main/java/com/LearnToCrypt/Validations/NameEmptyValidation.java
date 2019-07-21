package com.LearnToCrypt.Validations;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.LearnToCrypt.BusinessModels.User;
import com.LearnToCrypt.app.LearnToCryptApplication;

public class NameEmptyValidation implements IValidation {

	private static final Logger logger = LogManager.getLogger(LearnToCryptApplication.class);

	@Override
	public boolean isValid(User user, String confirmPassword) {
		String username = user.getName();
		if (username != null) {
			boolean result = !username.equals("");
			logger.info("Name empty validation for user : " + user.getEmail() + ". Result : " + result);
			return result;
		}
		return false;
	}

	@Override
	public String getError() {
		return "Name can not be empty.";
	}
}
