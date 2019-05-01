package com.routeplanner.ctrl;
import java.time.LocalDate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.routeplanner.shopping.Basket;
import com.routeplanner.shopping.ContactDetails;
import com.routeplanner.shopping.Order;
import com.routeplanner.shopping.PaymentInfo;
import com.routeplanner.shopping.Purchase;
import com.routeplanner.shopping.Shopping;
import com.routeplanner.shopping.Ticket;
import com.routeplanner.shopping.service.BasketService;
import com.routeplanner.shopping.service.OrderService;
import com.routeplanner.shopping.service.PaymentInfoService;
import com.routeplanner.shopping.service.PurchaseService;
import com.routeplanner.shopping.utils.FormValidation;


@Controller
@RequestMapping("/routeplanner")
public class CheckoutController {

	private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);
	
	private LoginController loginController;
	
	@Autowired
	private PaymentInfoService paymentInfoService;
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private PurchaseService purchaseService;
	
	@Autowired
	private BasketService basketService;
	
	
	
	public CheckoutController() {
		
	}

	
	@PostMapping("/go-to-checkout-person-details")
	public ModelAndView goToCheckout(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute Basket basket, BindingResult errors) {
		if (errors.hasErrors()) {
    		logger.info("errors exist on submitBasketForm on view basket page");
    		model.addAttribute("basket", basket);
    		model.addAttribute("ticket", new Ticket());
    		return new ModelAndView("view-basket");
    	}
		
		logger.info("go to checkout from basket page. Basket = " + basket.toString());
		
		// prepare new payment info and new contact details parts of the screen
		model.addAttribute("contactDetails", new ContactDetails());
		
		return new ModelAndView("contact-details");
	}
	
	
	
	@PostMapping("/add-contact-details")
	public ModelAndView goToPayment(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute ContactDetails contactDetails, BindingResult errors) {
		if (errors.hasErrors()) {
    		logger.info("errors exist on contactDetailsForm on contact details page");
    		model.addAttribute("errorLine1", "rp.contact.details.generic.form.error");
    		model.addAttribute("contactDetails", contactDetails);
    		addBespokeErrMsgs(contactDetails, model);
    		return new ModelAndView("contact-details");
    	}
		
		logger.info("persisting contact details as part of this order process = " + contactDetails.toString());
		paymentInfoService.saveContactDetails(contactDetails);
		
		// the user is creating an order if following the standard journey, so at this stage create a new order object
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		Order order = shopping.getOrder();
		if (shopping.getOrder() == null) {
			order = new Order();
			orderService.save(order);
		}
		
		shopping.setOrder(order);
		PaymentInfo paymentInfo = new PaymentInfo();
		paymentInfo.setContactDetails(contactDetails);
		
		// persist payment info
		paymentInfoService.save(paymentInfo);
		
		// persist order with the payment info (contact details part)		
		order.setPaymentInfo(paymentInfo);
		order.setBasket(shopping.getBasket());
		order.setUser(shopping.getUser());
		orderService.save(order);
		
		// go to checkout page
		model.addAttribute("paymentInfo", new PaymentInfo());
		return new ModelAndView("checkout");
	}
	
	
	
	private void addBespokeErrMsgs(ContactDetails contactDetails, ModelMap model) {
//		FormValidation.addBlankValidation(contactDetails.getFullname(), "fullname", model, "rp.contact.details.bad-field-fullname-no-value");
//		FormValidation.addBlankValidation(contactDetails.getAddressLine1(), "addressLine1", model, "rp.contact.details.bad-field-address-line-1-no-value");
//		FormValidation.addBlankValidation(contactDetails.getCity(), "city", model, "rp.contact.details.bad-field-city-no-value");
//		FormValidation.addBlankValidation(contactDetails.getRegionOrState(), "regionOrState", model, "rp.contact.details.bad-field-region-no-value");
//		FormValidation.addBlankValidation(contactDetails.getCountry(), "country", model, "rp.contact.details.bad-field-country-no-value");
//		FormValidation.addBlankValidation(contactDetails.getEmail(), "email", model, "rp.contact.details.bad-field-email-no-value");
//		FormValidation.validateTelFields(contactDetails, model);
//		FormValidation.validateEmailFieldPattern("email", contactDetails.getEmail(), "rp.contact.details.bad-field-invalid-email", model);
		
		addBlankValidation(contactDetails.getFullname(), "fullname", model, "rp.contact.details.bad-field-fullname-no-value");
		addBlankValidation(contactDetails.getAddressLine1(), "addressLine1", model, "rp.contact.details.bad-field-address-line-1-no-value");
		addBlankValidation(contactDetails.getCity(), "city", model, "rp.contact.details.bad-field-city-no-value");
		addBlankValidation(contactDetails.getRegionOrState(), "regionOrState", model, "rp.contact.details.bad-field-region-no-value");
		addBlankValidation(contactDetails.getCountry(), "country", model, "rp.contact.details.bad-field-country-no-value");
		addBlankValidation(contactDetails.getEmail(), "email", model, "rp.contact.details.bad-field-email-no-value");
		validateTelFields(contactDetails, model);
		validateEmailFieldPattern("email", contactDetails.getEmail(), "rp.contact.details.bad-field-invalid-email", model);
	}

	
	
	///////////
	
	
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
	
	
	////////////
	
	
	
	
	@PostMapping("/do-purchase")
	public ModelAndView addPaymentInfo(HttpServletRequest request, ModelMap model, @Valid @ModelAttribute PaymentInfo paymentInfo, BindingResult errors) {
		Shopping shopping = (Shopping)request.getSession().getAttribute("shopping");
		if (errors.hasErrors()) {
    		logger.info("errors exist on doPurchaseForm on checkout page");
    		model.addAttribute("paymentInfo", paymentInfo);
    		return new ModelAndView("checkout");
    	}
		
		logger.info("payment info added by user (excludes contact info): " + paymentInfo.toString());
				
		// update the payment with the card details
		PaymentInfo shopPayInfo = shopping.getOrder().getPaymentInfo();
		shopPayInfo.setCardNumber(paymentInfo.getCardNumber());
		shopPayInfo.setCardType(paymentInfo.getCardType());		
		shopPayInfo.setExpiry_date(paymentInfo.getExpiry_date());
		shopPayInfo.setNameOnCard(paymentInfo.getNameOnCard());
		shopPayInfo.setSecurityCode(paymentInfo.getSecurityCode());
		
		// persist the payment info in full
		paymentInfoService.save(shopPayInfo);
		logger.info("persisted payment info in full: " + paymentInfo.toString());

		// persist the purchase
		try {
			if (purchase(shopping.getOrder())) {
				Purchase purchase = new Purchase(shopping.getUser(), LocalDate.now(), shopping.getOrder());
				
				// TODO when the back button is involved, sometimes the basket is lost from the order.................needs fixing
				Basket basket = purchase.getOrder().getBasket();
				
				// set the basket and its contents to closed
				basket.setOpen(false);
				basketService.saveTickets(basket.getTickets());
				basketService.save(basket);
				shopping.setPurchase(purchase);
				purchaseService.save(purchase);
				shopping.postPurchasePruneOrder();
			} else {
				// TODO go to sale-failure with explanation, and a link to payment
			}
		} catch(Throwable t) { 
			logger.info("An error occurred whilst attempting to persist the purchase data - " + t.getMessage());
    		model.addAttribute("paymentInfo", paymentInfo);
    		return new ModelAndView("checkout");
		}
				
		return new ModelAndView("sale-confirmation");
	}
	
	
	
	// do something to actually purchase the 
	private boolean purchase(Order order) {
		return true;
	}
	
	
}
