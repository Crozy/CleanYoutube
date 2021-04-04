package com.youtubeclean.service;

import com.google.api.services.youtube.model.*;
import com.youtubeclean.google.GoogleService;
import com.youtubeclean.models.ChannelYoutubeClean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class YoutubeService {
	
	@Autowired
	private GoogleService googleService;

	private List<Subscription> listChannelYoutubeSubscribed() throws GeneralSecurityException, IOException {
		
		List<Subscription> listSubscriptions = new ArrayList<Subscription>();
		
		SubscriptionListResponse listChannel = googleService.getListChannel("");
		listSubscriptions.addAll(listChannel.getItems());
		while(listChannel.getNextPageToken() != null) {
			listChannel = googleService.getListChannel(listChannel.getNextPageToken());
			listSubscriptions.addAll(listChannel.getItems());
		}
		
		return listSubscriptions;
	}
	
	public List<ChannelYoutubeClean> listChannelSubscribed() throws GeneralSecurityException, IOException {
		List<Subscription> listChannelYoutube = this.listChannelYoutubeSubscribed();
		List<ChannelYoutubeClean> listChannelYoutubeClean = new ArrayList<ChannelYoutubeClean>();
		listChannelYoutube.forEach(channel -> {
			ChannelYoutubeClean channelYoutubeClean = new ChannelYoutubeClean();
			channelYoutubeClean.setId(channel.getSnippet().getResourceId().getChannelId());
			channelYoutubeClean.setDescription(channel.getSnippet().getDescription());
			channelYoutubeClean.setTitre(channel.getSnippet().getTitle());
			channelYoutubeClean.setLogo(channel.getSnippet().getThumbnails().getHigh().toString());		
			if(channel.getSnippet() != null) {
				channelYoutubeClean.setSubscriptionDate(channel.getSnippet().getPublishedAt().toString());
			}
			try {
				ChannelListResponse channelDetail = googleService.getChannelById(channelYoutubeClean.getId());
				channelYoutubeClean.setCreateDate(channelDetail.getItems().get(0).getSnippet().getPublishedAt().toString());
				//DateTime lastActivity = this.activityChannel(channelYoutubeClean.getId()).getItems().get(0).getSnippet().getPublishedAt();
				List<Activity> activity = this.activityChannel(channelYoutubeClean.getId()).getItems();
				Long lastActivity = activity.size() > 0 ? activity.get(0).getSnippet().getPublishedAt().getValue() : null;
				if (lastActivity != null) {
					Date date = new Date(lastActivity);
					Format format = new SimpleDateFormat("yyyy MM dd HH:mm:ss");
					channelYoutubeClean.setActivityDate(format.format(date));
				} else {
					channelYoutubeClean.setActivityDate(null);
				}
			} catch (GeneralSecurityException | IOException e) {
				e.printStackTrace();
			}
			listChannelYoutubeClean.add(channelYoutubeClean);
		});
		
		return listChannelYoutubeClean;
	}

	public ActivityListResponse activityChannel(String idCHannel) throws GeneralSecurityException, IOException {
		return googleService.channelActivity(idCHannel, "");
	}
	
}
