package com.routeplanner.shopping.utils;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.ui.ModelMap;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.PaymentInfo;


public class FormValidation {

	public static final String DIGITS_ONLY_REGEX = "^[0-9]*$";
	
	private static final int CREDIT_CARD_NUMBER_LENGTH = 16;
	private static final int CREDIT_CARD_SECURITY_CODE_LENGTH = 3;
	
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
		
	public static void addBespokeContactDetailsErrMsgs(ContactDetails registrationDetails, ModelMap model) {
		FormValidation.addBlankValidation(registrationDetails.getFullname(), "fullname", model, "rp.contact.details.bad-field-fullname-no-value");
		FormValidation.addBlankValidation(registrationDetails.getAddressLine1(), "addressLine1", model, "rp.contact.details.bad-field-address-line-1-no-value");
		FormValidation.addBlankValidation(registrationDetails.getCity(), "city", model, "rp.contact.details.bad-field-city-no-value");
		FormValidation.addBlankValidation(registrationDetails.getRegionOrState(), "regionOrState", model, "rp.contact.details.bad-field-region-no-value");
		FormValidation.addBlankValidation(registrationDetails.getCountry(), "country", model, "rp.contact.details.bad-field-country-no-value");
		FormValidation.addBlankValidation(registrationDetails.getEmail(), "email", model, "rp.contact.details.bad-field-email-no-value");
		validateTelFields(registrationDetails, model);
		FormValidation.validateEmailFieldPattern("email", registrationDetails.getEmail(), "rp.contact.details.bad-field-invalid-email", model);
	}
	
	
	public static void addBespokePaymentInfoErrMsgs(PaymentInfo paymentInfo, ModelMap model) {
		FormValidation.addBlankValidation(paymentInfo.getNameOnCard(), "nameOnCard", model, "rp.checkout.bad-field-name-on-card-no-value");
		FormValidation.addBlankValidation(paymentInfo.getCardNumber(), "cardNumber", model, "rp.checkout.bad-field-card-number-no-value");
		FormValidation.addBlankValidation(paymentInfo.getSecurityCode(), "securityCode", model, "rp.checkout.bad-field-security-code-no-value");
		FormValidation.addBlankValidation(paymentInfo.getExpiryDate(), "expiryDate", model, "rp.checkout.bad-field-expiry-date-no-value");
		FormValidation.addStringFldLengthValidation("cardNumber", paymentInfo.getCardNumber(), 
				"rp.checkout.card-field-wrong-length", CREDIT_CARD_NUMBER_LENGTH, model);
		FormValidation.addStringFldLengthValidation("securityCode", paymentInfo.getSecurityCode(), 
				"rp.checkout.security-field-wrong-length", CREDIT_CARD_SECURITY_CODE_LENGTH, model);
	}
	
	
	private static void validateTelFields(ContactDetails contactDetails, ModelMap model) {
		FormValidation.addValidation("mobileTel", contactDetails.getMobileTel(), 
				"rp.contact.details.bad-field-mobile-tel-not-all-digits", model, FormValidation.DIGITS_ONLY_REGEX);
		FormValidation.addValidation("homeTel", contactDetails.getHomeTel(), 
				"rp.contact.details.bad-field-home-tel-not-all-digits", model, FormValidation.DIGITS_ONLY_REGEX);
	}
	
	
	
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
