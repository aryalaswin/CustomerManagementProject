package com.aswin.jwt;

public class JwtResponse {
String token;



@Override
public String toString() {
	return "JwtResponse [token=" + token + "]";
}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

public JwtResponse(String token) {
	
	this.token = token;
}
}
