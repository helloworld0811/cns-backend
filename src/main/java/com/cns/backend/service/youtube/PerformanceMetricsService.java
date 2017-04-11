package com.cns.backend.service.youtube;

import com.cns.backend.model.CNSChannel;
import com.cns.backend.model.ChannelType;
import com.cns.backend.model.PerformanceMetrics;
import com.cns.backend.repository.ChannelRepository;
import com.cns.backend.repository.PerformanceMetricsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hku on 11.04.17.
 */

@Service
public class PerformanceMetricsService {


    @Autowired
    private ChannelRepository channelRepository;


    @Autowired
    private PerformanceMetricsRepository performanceMetricsRepository;

    public PerformanceMetrics updatePerformanceMetricsForChannel(Long channelId) throws Exception
    {
       CNSChannel ownerChannel=channelRepository.findOne(channelId);
       String ownerChannelId=ownerChannel.getChannelId();
       // build the requesting URL for performance statistics
        String requestUrl = YoutubeConstants.API_CHANNEL_STATS_URL_PREFIX + "&id=" + ownerChannelId + "&key=" + YoutubeConstants.API_KEY;
        System.out.println("requested Url for channel stats:" + requestUrl);
        try {
            String json = JSONUtil.readJsonFromUrl(requestUrl);

            return null;
        }
        catch (Exception e) {
            throw new Exception("Error:" + e);
        }

        }


    public PerformanceMetrics updatePerformanceMetricsForChannel(String channelId, Long cnsChannelId, Boolean writeToDB) throws Exception {
        String requestUrl = YoutubeConstants.API_CHANNEL_STATS_URL_PREFIX + "&id=" + channelId + "&key=" + YoutubeConstants.API_KEY;
        System.out.println("requested Url for channel stats:" + requestUrl);
        try {

            String json = JSONUtil.readJsonFromUrl(requestUrl);
            PerformanceMetrics performanceMetrics = JSONUtil.buildPerformanceMetricsFromJSON(json, cnsChannelId);

            performanceMetrics.setOwnerChannelId(cnsChannelId);
            if(writeToDB) {
                return performanceMetricsRepository.save(performanceMetrics);
            }

            else {
               return  performanceMetrics;
            }
        } catch (Exception e) {
            throw new Exception("" + e);
        }
    }


    public Integer updatePerformanceMetricsForAllChannels() throws Exception {
        Map<Long, String> cnsChannelIdToChannelIdMap = new HashMap<>();
        List<PerformanceMetrics> performanceMetricsToUpdate=new ArrayList<>();
        Integer updateCount = 0;
        for (CNSChannel cnsChannel : channelRepository.findAll()) {
            cnsChannelIdToChannelIdMap.put(cnsChannel.getId(), cnsChannel.getChannelId());
        }

        for (Map.Entry<Long, String> entry : cnsChannelIdToChannelIdMap.entrySet()) {
            PerformanceMetrics performanceMetrics=updatePerformanceMetricsForChannel(entry.getValue(), entry.getKey(), false);
            performanceMetricsToUpdate.add(performanceMetrics);
            updateCount++;
        }
        performanceMetricsRepository.save(performanceMetricsToUpdate);
        return updateCount;
    }

    public Integer updatePerformanceMetricsForChannelsTypes(ChannelType channelType) throws Exception {
        Map<Long, String> cnsChannelIdToChannelIdMap = new HashMap<>();
        List<PerformanceMetrics> performanceMetricsToUpdate=new ArrayList<>();
        Integer updateCount = 0;
        for (CNSChannel cnsChannel : channelRepository.findByChannelType(channelType)) {
            cnsChannelIdToChannelIdMap.put(cnsChannel.getId(), cnsChannel.getChannelId());
        }

        for (Map.Entry<Long, String> entry : cnsChannelIdToChannelIdMap.entrySet()) {
            PerformanceMetrics performanceMetrics=updatePerformanceMetricsForChannel(entry.getValue(), entry.getKey(), false);
            performanceMetricsToUpdate.add(performanceMetrics);
            updateCount++;
        }
        performanceMetricsRepository.save(performanceMetricsToUpdate);
        return updateCount;
    }


}
