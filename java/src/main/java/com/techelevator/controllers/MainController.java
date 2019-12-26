package com.techelevator.controllers;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.techelevator.authentication.AuthProvider;
import com.techelevator.dao.ParkDao;
import com.techelevator.dao.RegistrationDao;
import com.techelevator.dao.SurveyDao;
import com.techelevator.dao.WeatherDao;
import com.techelevator.models.Login;
import com.techelevator.models.Park;
import com.techelevator.models.Registration;
import com.techelevator.models.Survey;
import com.techelevator.models.User;
import com.techelevator.models.Weather;


@Controller 
public class MainController {
	
	@Autowired
	private SurveyDao surveyDao;
	@Autowired
	private ParkDao parkDao;
	@Autowired
	private WeatherDao weatherDao;
	@Autowired
	private RegistrationDao registrationDao;
	@Autowired
	private AuthProvider auth;


	@RequestMapping("/")
	public String displayHome(ModelMap modelHolder) {
		
		List<Park> parkList = new ArrayList<Park>();
		parkList = parkDao.getAllParks();
		for(Park aPark : parkList) {
		}
		modelHolder.put("parks", parkList);
		return "home";
	}
	
	@RequestMapping(path = "/submitSurvey", method = RequestMethod.GET)
	public String displaySubmitSurvey(Model modelHolder) {
		
		if( ! modelHolder.containsAttribute("Survey")) {
			modelHolder.addAttribute("Survey", new Survey());
		}
		
		return "./submitSurvey";
	}
	@RequestMapping(path = "/submitSurvey", method = RequestMethod.POST)
		public String displaySubmitSurvey(@Valid @ModelAttribute("Survey") Survey surveyFormData, BindingResult result,
										RedirectAttributes flash) {
		
		Survey aSurvey = new Survey();
		aSurvey.setActivityLevel(surveyFormData.getActivityLevel());
		aSurvey.setEmailAddress(surveyFormData.getEmailAddress());
		aSurvey.setParkCode(surveyFormData.getParkCode());
		aSurvey.setState(surveyFormData.getState());
	
		if(result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "Survey", result);
			flash.addFlashAttribute("Survey", surveyFormData);
			
			
			
			return "redirect:submitSurvey";
		}
		
		surveyDao.saveSurvey(aSurvey);
		return "redirect:surveyResult";
	}
	@RequestMapping(path = "/surveyResult", method = RequestMethod.GET)
	public String displaySurveyResult(  ModelMap map) {
			LinkedHashMap<Park,Integer> parkSurveyCount = new LinkedHashMap<Park,Integer>();
			LinkedHashMap<String,Integer> parkMap = surveyDao.getParksSurveyCount();
			
			Set <String>theKeys = parkMap.keySet();
			for(String aKey : theKeys) {
				Park aPark = new Park();
				aPark = parkDao.getParkByParkCode(aKey);

				parkSurveyCount.put(aPark, parkMap.get(aKey));
			}
			
			map.put("parkInfo", parkSurveyCount);	
		
		
		return "./surveyResult";
	}
	
	@RequestMapping("/parkDetails")
	public String displayParkDetails(@RequestParam String parkCode, ModelMap modelHolder) {
		List<Weather> weatherList = new ArrayList<Weather>();
		weatherList = weatherDao.getWeatherByParkCode(parkCode);
		modelHolder.put("weather", weatherList);
		
	
		
		Park aPark = new Park();
		aPark = parkDao.getParkByParkCode(parkCode);
		
		modelHolder.put("park", aPark);
		return "./parkDetails";
		
		
	}
	@RequestMapping(path="/parkDetails", method=RequestMethod.POST)
	public String changeTempUnit(@RequestParam String parkCode, @RequestParam String temp, HttpSession session, ModelMap modelHolder) {
		List<Weather> weatherList = new ArrayList<Weather>();
		weatherList = weatherDao.getWeatherByParkCode(parkCode);
		modelHolder.put("weather", weatherList);
		
		session.setAttribute("unitPref", temp);
		Park aPark = new Park();
		aPark = parkDao.getParkByParkCode(parkCode);
		
		modelHolder.put("park", aPark);
		return "./parkDetails";
		
		
	}
	@RequestMapping(path = "/registrationForm", method = RequestMethod.GET)
	public String displayRegistrationForm(Model modelHolder) {
		
		if( ! modelHolder.containsAttribute("Registration")) {
			modelHolder.addAttribute("Registration", new Registration());
		}
		
		return "./registrationForm";
	}
	
	@RequestMapping(path = "/registrationForm", method = RequestMethod.POST)
	public String submitRegistrationForm(@Valid @ModelAttribute("Registration") Registration registerFormData, 
											BindingResult result,RedirectAttributes flash) {
		Registration aRegister = new Registration();
		aRegister.setConfirmPassword(registerFormData.getConfirmPassword());
		aRegister.setEmailAddress(registerFormData.getEmailAddress());
		aRegister.setFirstName(registerFormData.getFirstName());
		aRegister.setLastName(registerFormData.getLastName());
		aRegister.setPassword(registerFormData.getPassword());
		aRegister.setState(registerFormData.getState());
		aRegister.setUsername(registerFormData.getUsername());

		if(result.hasErrors()) {
			flash.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "Registration", result);
			flash.addFlashAttribute("Registration",registerFormData);
			
			return "redirect:registrationForm";
		}
		
		registrationDao.registerUser(aRegister);
		
		return "./registrationConfirmation";
	}
	
	@RequestMapping(path = "/login", method = RequestMethod.GET)
    public String login(ModelMap modelHolder) {
       
		if( ! modelHolder.containsAttribute("Login")) {
			modelHolder.addAttribute("Login", new Login());
		}
		
		 return "login";
	}
	
	 @RequestMapping(path = "/login", method = RequestMethod.POST)
	    public String login(@RequestParam String username, @RequestParam String password, RedirectAttributes flash) {
	        if (auth.signIn(username, password)) {
	        	
	            return "redirect:/";
	            
	        } else {
	            flash.addFlashAttribute("message", "Login Invalid");
	            return "redirect:/login";
	        }
	    }
		@RequestMapping(path = "/logout", method = RequestMethod.GET)
	    public String logout(ModelMap modelHolder) {
	       
			auth.logOff();
			
			 return "redirect:/";
		}
	
	
}