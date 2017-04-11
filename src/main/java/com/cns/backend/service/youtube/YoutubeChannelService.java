package com.cns.backend.service.youtube;

import com.cns.backend.model.CNSChannel;
import com.cns.backend.model.ChannelType;
import com.cns.backend.model.PerformanceMetrics;
import com.cns.backend.repository.ChannelRepository;
import com.cns.backend.repository.PerformanceMetricsRepository;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hku on 03.04.17.
 */
@Service
public class YoutubeChannelService {


    // create new channels by calling createChannelWithUserName and store in DB
    // create or update PerformanceMetrics as crone for all channels in the DB
    // CRUD API calls returns PerformanceStatics and CNSChannel models from the DB
    // CRUD API calls returns PerformnceStatics and channel Info live omn special request

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private PerformanceMetricsRepository performanceMetricsRepository;


    public CNSChannel updateCNSChannel(CNSChannel cnsChannel)
    {
        return channelRepository.save(cnsChannel);
    }


    public CNSChannel createChannelWithChannelId(String channelId) throws Exception {
        String requestUrl = YoutubeConstants.API_CHANNEL_STATS_URL_PREFIX + "&id=" + channelId + "&key=" + YoutubeConstants.API_KEY;
        System.out.println("requested Url for channel stats:" + requestUrl);
        try {

            String json = JSONUtil.readJsonFromUrl(requestUrl);
            CNSChannel CNSChannel = buildCNSChannelFromJSON(json);
            CNSChannel CNSChannelCreated = channelRepository.save(CNSChannel);
            return CNSChannelCreated;
        } catch (Exception e) {
            throw new Exception("Error:" + e);
        }

    }

    public String getYoutubeChannelIdByUserName(String userName) {
        String requestUrl = YoutubeConstants.API_CHANNEL_ID_URL_PREFIX + "?key=" + YoutubeConstants.API_KEY + "&forUsername=" + userName + "&part=id";
        System.out.println("requested Url for channelId" + requestUrl);
        String channelId = null;
        try {

            String json = JSONUtil.readJsonFromUrl(requestUrl);
            System.out.println(json.toString());
            ChannelListResponse channelListResponse = convertJSONToChannelListResponse(json);
            if (channelListResponse != null && channelListResponse.getItems() != null && !channelListResponse.getItems().isEmpty()) {

                channelId = channelListResponse.getItems().get(0).getId();
                System.out.println("id" + channelId);
            }

        } catch (Exception e) {

        }
        return channelId;
    }

    public CNSChannel createChannelByUserName(String userName) throws Exception {
        String channelId = getYoutubeChannelIdByUserName(userName);
        CNSChannel cnsChannelCreated = createChannelWithChannelId(channelId);
        // create PerformanceMetrics
        //updatePerformanceMetricsForChannel(channelId, cnsChannelCreated.getId());

        return cnsChannelCreated;
    }



    public CNSChannel getCNSChannelByUserName(String userName) {
        for (CNSChannel cnsChannel : channelRepository.findAll()) {
            if (cnsChannel.getTitle().equals(userName))
                return cnsChannel;
        }
        return null;
    }

    public CNSChannel getCNSChannelByCNSChannelId(Long id) {
        for (CNSChannel cnsChannel : channelRepository.findAll()) {
            if (cnsChannel.getId() == id)
                return cnsChannel;
        }
        return null;
    }

    public CNSChannel getCNSChannelByChannelId(String channelId) {
        for (CNSChannel cnsChannel : channelRepository.findAll()) {
            if (cnsChannel.getChannelId().equals(channelId))
                return cnsChannel;
        }
        return null;
    }



    private ChannelListResponse convertJSONToChannelListResponse(String jsonString) {
        System.out.println("channelList" + jsonString);
        ChannelListResponse channelListResponse = null;
        try {
            channelListResponse = YoutubeConstants.JSON_FACTORY.fromString(jsonString, ChannelListResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return channelListResponse;
    }

    private CNSChannel buildCNSChannelFromJSON(String json) {
        ChannelListResponse channelListResponse = convertJSONToChannelListResponse(json);
        Channel channel = null;
        CNSChannel cnsCNSChannel = null;
        if (channelListResponse != null && channelListResponse.getItems() != null && !channelListResponse.getItems().isEmpty()) {

            channel = channelListResponse.getItems().get(0);
            cnsCNSChannel = buildCnsChannel(channel);

        }
        if (cnsCNSChannel != null) {
            return cnsCNSChannel;
        }
        return null;

    }




    private CNSChannel buildCnsChannel(Channel channel) {
        CNSChannel cnsCNSChannel = new CNSChannel(ChannelType.YOUTUBE, channel.getId(), true, false, true);
        // not usable as per the model
        String channelKind = channel.getKind();

        cnsCNSChannel.setCountry(channel.getSnippet().getCountry());
        cnsCNSChannel.setLanguage(channel.getSnippet().getDefaultLanguage());
        cnsCNSChannel.setPublishedTime(channel.getSnippet().getPublishedAt().getValue());
        cnsCNSChannel.setTitle(channel.getSnippet().getTitle());
        cnsCNSChannel.setDescription(channel.getSnippet().getDescription());
        cnsCNSChannel.setImageUrl(channel.getSnippet().getThumbnails().getDefault().getUrl());

        // Owner info goes to Profile later
        //channel.getContentOwnerDetails().getContentOwner();

        cnsCNSChannel.setTitleLocal(channel.getSnippet().getLocalized().getTitle());
        cnsCNSChannel.setDescriptionLocal(channel.getSnippet().getLocalized().getDescription());
        cnsCNSChannel.setRegistered(false);

        return cnsCNSChannel;
    }



}
