package com.routeplanner.shopping.utils;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.ui.ModelMap;

public class FormValidation {

	public static final String DIGITS_ONLY_REGEX = "^[0-9]*$";
	
	public static void validateEmailFieldPattern(String emailFld, String emailVal, String emailErrLit, ModelMap model) {
		if (StringUtils.isNoneBlank(emailVal) && !EmailValidator.getInstance().isValid(emailVal)) {
			model.addAttribute(emailFld, emailErrLit);
		}
	}
	
	
	public static void addValidation(String fldName, String fldVal, String errorLit, ModelMap model, final String regex) {
		Pattern p = Pattern.compile(regex);
		if (StringUtils.isNoneBlank(fldVal)) {
			Matcher m = p.matcher(fldVal);
			if (! m.matches()) {
				model.addAttribute(fldName, errorLit);
			}
		}
	}
		
	
	// DATES:  yyyy-MM-dd                
	
	
	
	
	
	
	public static void addStringFldLengthValidation(String fldName, String fldVal, String errorLit, int reqLength, ModelMap model) {
		if (!StringUtils.isBlank(fldVal) && fldVal.length() != reqLength) {
			model.addAttribute(fldName, errorLit);
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
