package com.twitteranalytics.ModelClasses;

public class TweetLocation {
	
	double Latitude;
	double Longitude;
	String City;
	String State;
	String Country;
	String arr[]= null;
			
	public TweetLocation() {
		super();
		City=null;
		State=null;
		Country=null;
	}
	
	public void parseLocation(String location){
		arr = location.split(";");
		City=arr[0];
		State= arr[1];
		Country=arr[2];
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "TweetLocation [Latitude=" + Latitude + ", Longitude="
				+ Longitude + ", City=" + City + ", State=" + State
				+ ", Country=" + Country + "]";
	}
	/**
	 * @return the latitude
	 */
	public double getLatitude() {
		return Latitude;
	}
	/**
	 * @param latitude the latitude to set
	 */
	public void setLatitude(double latitude) {
		Latitude = latitude;
	}
	/**
	 * @return the longitude
	 */
	public double getLongitude() {
		return Longitude;
	}
	/**
	 * @param longitude the longitude to set
	 */
	public void setLongitude(double longitude) {
		Longitude = longitude;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return City;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		City = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return State;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		State = state;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return Country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		Country = country;
	}
	
	
}
