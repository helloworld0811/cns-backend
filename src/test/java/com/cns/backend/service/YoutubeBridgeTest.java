package com.cns.backend.service;

import com.cns.backend.service.youtube.YoutubeChannelService;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.model.*;
import com.google.common.collect.Lists;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hku on 04.04.17.
 */
public class YoutubeBridgeTest {

    private static YouTube youtube;

    private static  String channelId="UCtVd0c0tGXuTSbU5d8cSBUg";

    private  Credential _credential;


    private final static List<String> scopes = Arrays.asList(
            "https://www.googleapis.com/auth/youtube.readonly");
    private static final String APP_NAME = "connecsi";

    @Test
    public void testGetChannelStatistics() throws IOException {
       // YoutubeChannelService yb = new YoutubeChannelService();
       // String resource = yb.getCNSChannelByUserName("VenetianPrincess").getDescription();
       // System.out.println(resource);
        connect1();

        String apiKey = "AIzaSyD5vOoJEKltWf7bsY9j7nmnVEIW3tzIPk0";

        YouTube.GuideCategories youtubeCategories = youtube.guideCategories();
        YouTube.GuideCategories.List category = youtubeCategories.list("snippet");

         category.setKey(apiKey);
         category.setHl("EN");
         category.setRegionCode("US");

        GuideCategoryListResponse response = category.execute();

        List<String> categoryIds=new ArrayList<>();
        for(GuideCategory item:response.getItems())
        {
            String title=item.getSnippet().getTitle();
            String categoryID=item.getId();
            categoryIds.add(categoryID);
            System.out.println("title:"+title+ " #category:"+categoryID);
        }

        YouTube.Channels.List channels = youtube.channels().list("snippet");
        channels.setCategoryId("GCU3BvcnRz");
        //channels.setForUsername("VEVO");
        channels.setHl("EN");
        channels.setKey(apiKey);
        channels.setMaxResults(30L);

        //channels.setOauthToken();
        channels.execute();
        ChannelListResponse responseChannels = channels.execute();
        System.out.println("channels:"+responseChannels.getItems().size());
/*
        YouTube.Search.List search = youtube.search().list("id,snippet");

        search.setKey(apiKey);
        search.setQ("music");

        search.setType("video");

        search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/high/url)");
        search.setMaxResults(20L);
        SearchListResponse searchResponse = search.execute();
        String regionCode=searchResponse.getRegionCode();
        System.out.println(regionCode);

        */
    }

    private void connect1()
    {

        youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer()
        {
            @Override
            public void initialize(HttpRequest request) throws IOException {
            }
        }).setApplicationName("connecsi.app").build();

    }

    /*
    private void connect2() throws IOException {

        _credential = Auth.authorize(scopes, APP_NAME);
        youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT,
                Auth.JSON_FACTORY, _credential).setApplicationName(APP_NAME)
                .build();
    }

*/


}
