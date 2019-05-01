package com.routeplanner.shopping.utils;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.ui.ModelMap;
import com.routeplanner.shopping.ContactDetails;

public class FormValidation {

	private static final String DIGITS_ONLY_REGEX = "^[0-9]*$";
	
	public static void validateEmailFieldPattern(String emailFld, String emailVal, String emailErrLit, ModelMap model) {
		if (StringUtils.isNoneBlank(emailVal) && !EmailValidator.getInstance().isValid(emailVal)) {
			model.addAttribute(emailFld, emailErrLit);
		}
	}
		
	public static void validateTelFields(ContactDetails contactDetails, ModelMap model) {
		addNumericValidation("mobileTel", contactDetails.getMobileTel(), "rp.contact.details.bad-field-mobile-tel-not-all-digits", model);
		addNumericValidation("homeTel", contactDetails.getHomeTel(), "rp.contact.details.bad-field-home-tel-not-all-digits", model);
	}
		
	private static void addNumericValidation(String fldName, String fldVal, String errorLit, ModelMap model) {
		Pattern p = Pattern.compile(DIGITS_ONLY_REGEX);
		if (StringUtils.isNoneBlank(fldVal)) {
			Matcher m = p.matcher(fldVal);
			if (! m.matches()) {
				//model.addAttribute("mobileTel", "rp.contact.details.bad-field-mobile-tel-not-all-digits");
				model.addAttribute(fldName, errorLit);
			}
		}
	}
		
	public static void addBlankValidation(String field, String fieldLit, ModelMap model, String attribute) {
		if (StringUtils.isBlank(field)) {
			model.addAttribute(fieldLit, attribute);
		}
	}
	
	
}
