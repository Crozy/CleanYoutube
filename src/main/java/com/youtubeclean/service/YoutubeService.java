package com.youtubeclean.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.Subscription;
import com.google.api.services.youtube.model.SubscriptionListResponse;
import com.youtubeclean.google.GoogleService;
import com.youtubeclean.models.ChannelYoutubeClean;

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
			} catch (GeneralSecurityException | IOException e) {
				e.printStackTrace();
			}
			listChannelYoutubeClean.add(channelYoutubeClean);
		});
		
		return listChannelYoutubeClean;
	}
	
}
