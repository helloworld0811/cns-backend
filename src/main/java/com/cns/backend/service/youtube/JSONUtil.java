package com.cns.backend.service.youtube;

import com.cns.backend.model.CNSChannel;
import com.cns.backend.model.PerformanceMetrics;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.Channel;
import com.google.api.services.youtube.model.ChannelListResponse;
import com.google.api.services.youtube.model.SearchListResponse;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.time.Instant;

/**
 * Created by hku on 07.04.17.
 */
public class JSONUtil {


    private static String readAll(Reader rd) throws IOException {
        StringBuilder sb = new StringBuilder();
        int cp;
        while ((cp = rd.read()) != -1) {
            sb.append((char) cp);
        }
        return sb.toString();
    }

    public static String readJsonFromUrl(String url) throws IOException {
        InputStream is = new URL(url).openStream();
        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
            String jsonText = readAll(rd);

            return jsonText;
        } finally {
            is.close();
        }
    }


    public static PerformanceMetrics buildPerformanceMetricsFromJSON(String json, Long ownerChannelId) {
        ChannelListResponse channelListResponse = convertJSONToChannelListResponse(json);
        Channel channel = null;
        CNSChannel cnsCNSChannel = null;
        if (channelListResponse != null && channelListResponse.getItems() != null && !channelListResponse.getItems().isEmpty()) {

            channel = channelListResponse.getItems().get(0);
            PerformanceMetrics performanceMetrics = buildPerformanceMetrics(channel, ownerChannelId);
            return performanceMetrics;
        }
        if (cnsCNSChannel != null) {
            return null;
        }
        return null;

    }

    private static ChannelListResponse convertJSONToChannelListResponse(String jsonString) {
        System.out.println("channelList" + jsonString);
        ChannelListResponse channelListResponse = null;
        try {
            channelListResponse = YoutubeConstants.JSON_FACTORY.fromString(jsonString, ChannelListResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return channelListResponse;
    }

    private static PerformanceMetrics buildPerformanceMetrics(Channel channel, Long cnsChannelId) {
        //stats
        PerformanceMetrics pm = new PerformanceMetrics();
        pm.setCommentCount(channel.getStatistics().getCommentCount().longValue());
        pm.setSubscribeCount(channel.getStatistics().getSubscriberCount().longValue());
        pm.setVideoCount(channel.getStatistics().getVideoCount().longValue());
        pm.setViewsCount(channel.getStatistics().getViewCount().longValue());

        pm.setOwnerChannelId(cnsChannelId);
        Instant now = Instant.now();
        pm.setTimestamp(now.getEpochSecond());
        return pm;
    }



}
