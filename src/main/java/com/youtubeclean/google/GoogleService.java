package com.youtubeclean.google;

import java.io.IOException;
import java.security.GeneralSecurityException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.SubscriptionListResponse;

@Service
public class GoogleService {
	
	@Autowired
	GoogleManage googleManage;

	public SubscriptionListResponse getListChannel(String PageToken) throws GeneralSecurityException, IOException {
		YouTube youtubeService = googleManage.getService();
		// Define and execute the API request
		YouTube.Subscriptions.List request = youtubeService.subscriptions().list("snippet,contentDetails");
		SubscriptionListResponse response = request
				.setMine(true)
				.setMaxResults(50L)
				.setPageToken(PageToken)
				.execute();
		System.out.println(response);
		return response;
	}
	
    public ChannelListResponse getChannelById(String idChannel)
            throws GeneralSecurityException, IOException, GoogleJsonResponseException {
            YouTube youtubeService = googleManage.getService();
            // Define and execute the API request
            YouTube.Channels.List request = youtubeService.channels()
                .list("snippet,contentDetails,statistics");
            ChannelListResponse response = request.setId(idChannel).execute();
            System.out.println(response);
            return response;
        }

}
