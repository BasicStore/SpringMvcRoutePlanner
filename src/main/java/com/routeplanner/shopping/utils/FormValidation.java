package com.routeplanner.shopping.utils;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.ui.ModelMap;

public class FormValidation {

	private static final String DIGITS_ONLY_REGEX = "^[0-9]*$";
	
	public static void validateEmailFieldPattern(String emailFld, String emailVal, String emailErrLit, ModelMap model) {
		if (StringUtils.isNoneBlank(emailVal) && !EmailValidator.getInstance().isValid(emailVal)) {
			model.addAttribute(emailFld, emailErrLit);
		}
	}
		
	public static void addNumericValidation(String fldName, String fldVal, String errorLit, ModelMap model) {
		Pattern p = Pattern.compile(DIGITS_ONLY_REGEX);
		if (StringUtils.isNoneBlank(fldVal)) {
			Matcher m = p.matcher(fldVal);
			if (! m.matches()) {
				//model.addAttribute("mobileTel", "rp.contact.details.bad-field-mobile-tel-not-all-digits");
				model.addAttribute(fldName, errorLit);
			}
		}
	}
		
	public static void addBlankValidation(String fldVal, String fieldLit, ModelMap model, String attribute) {
		if (StringUtils.isBlank(fldVal)) {
			model.addAttribute(fieldLit, attribute);
		}
	}
	
	
	public static void addBlankValidation(LocalDate fldVal, String fieldLit, ModelMap model, String attribute) {
		if (fldVal != null && StringUtils.isBlank(fldVal.toString())) {
			model.addAttribute(fieldLit, attribute);
		}
	}

	
}
