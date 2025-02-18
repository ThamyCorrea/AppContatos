package com.thamirisoc.AppContatos.service;

import java.util.regex.Pattern;

public class ValidacoesDeDados {

	public static boolean validandoEmail(String email) {	       
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return Pattern.compile(emailRegex).matcher(email).matches();
    }

	 public static boolean validandoUrl(String url) {	        
        String urlRegex = "^(http|https)://[^\\s/$.?#].[^\\s]*$";
        return Pattern.compile(urlRegex).matcher(url).matches();
    }
}
