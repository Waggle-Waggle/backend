package kr.co.onedayclass.auth.dto;

public interface OAuth2Response {

	String getProvider();

	String getProviderId();

	String getEmail();

	String getName();

}
