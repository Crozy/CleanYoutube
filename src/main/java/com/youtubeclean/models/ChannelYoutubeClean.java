package com.youtubeclean.models;

import com.google.api.client.util.DateTime;

import java.util.Date;

public class ChannelYoutubeClean<date> {
	
	private String titre;
	private String description;
	private String subscriptionDate;
	private String createDate;
	private String id;
	private String logo;
	private String activityDate;
	
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getSubscriptionDate() {
		return subscriptionDate;
	}
	public void setSubscriptionDate(String subscriptionDate) {
		this.subscriptionDate = subscriptionDate;
	}
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getActivityDate() {return activityDate;}
	public void setActivityDate(String activityDate) {this.activityDate = activityDate;}
	
}
