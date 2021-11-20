package com.example.application.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

@Service
public class UserApplicationService {

	@Autowired
	private MessageSource messageSource;

	//性別のマップを生成する
	public Map<String, Integer> getGenderMap(Locale locale){
		Map<String, Integer> genderMap = new LinkedHashMap<>();
		genderMap.put(messageSource.getMessage("male",null, locale),1);
		genderMap.put(messageSource.getMessage("female",null, locale),2);
		return genderMap;
	}

}
