package com.cns.backend.rest;

import com.cns.backend.model.CNSChannel;
import com.cns.backend.model.Category;
import com.cns.backend.model.PerformanceMetrics;
import com.cns.backend.service.youtube.PerformanceMetricsService;
import com.cns.backend.service.youtube.YoutubeCategoryService;
import com.cns.backend.service.youtube.YoutubeChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hku on 05.04.17.
 */
@RequestMapping(value = "/api/v1/youtube")
@RestController
public class YoutubeChannelController {

    @Autowired
    private YoutubeChannelService youtubeChannelService;

    @Autowired
    private YoutubeCategoryService youtubeCategoryService;

    @Autowired
    private PerformanceMetricsService performanceMetricsService;
/*
    @RequestMapping(method = RequestMethod.GET, value = "/{userName}")
    public @ResponseBody ResponseEntity<?> getStatsByUserName(@PathVariable String userName )
    {
        final String channelStatistics = youtubeChannelService.getChannelStatistics(userName);
        return (channelStatistics == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(channelStatistics);
    }
*/
    @RequestMapping(method = RequestMethod.GET, value = "create/channel/create/{userName}")
    public @ResponseBody ResponseEntity<?> createCNSChannelByUserName(@PathVariable String userName ) throws Exception {
        final CNSChannel channelCreated = youtubeChannelService.createChannelByUserName(userName);
        return (channelCreated == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(channelCreated);
    }

    @RequestMapping(method = RequestMethod.GET, value = "update/channel/performance-metrics")
    public @ResponseBody ResponseEntity<?> updatePerformanceMetricsByUserName() throws Exception {
        final Integer recordsUpdatedCount = performanceMetricsService.updatePerformanceMetricsForAllChannels();
        return (recordsUpdatedCount == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(recordsUpdatedCount);
    }


    // to call from DB level

    @RequestMapping(method = RequestMethod.GET, value = "get/channel/{userName}")
    public @ResponseBody ResponseEntity<?> getCNSChannelByUserName(@PathVariable String userName ) throws Exception {
        final CNSChannel cnsChannel = youtubeChannelService.getCNSChannelByUserName(userName);
        return (cnsChannel == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(cnsChannel);
    }

    @RequestMapping(method = RequestMethod.GET, value = "get/channel/{id}")
    public @ResponseBody ResponseEntity<?> getCNSChannelByCNSChannelId(@PathVariable Long id ) throws Exception {
        final CNSChannel cnsChannel = youtubeChannelService.getCNSChannelByCNSChannelId(id);
        return (cnsChannel == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(cnsChannel);
    }

    @RequestMapping(method = RequestMethod.GET, value = "get/channel/{channelId}")
    public @ResponseBody ResponseEntity<?> getCNSChannelByChannelId(@PathVariable String channelId ) throws Exception {
        final CNSChannel cnsChannel = youtubeChannelService.getCNSChannelByChannelId(channelId);
        return (cnsChannel == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(cnsChannel);
    }


    @RequestMapping(method = RequestMethod.GET, value = "update/channel/register/{channelId}")
    public @ResponseBody ResponseEntity<?> setCNSChannelRegisteredByChannelId(@PathVariable String channelId ) throws Exception {
        final CNSChannel cnsChannel = youtubeChannelService.getCNSChannelByChannelId(channelId);
        cnsChannel.setRegistered(true);
        CNSChannel cnsChannelUpdated= youtubeChannelService.updateCNSChannel(cnsChannel);
        return (cnsChannelUpdated == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(cnsChannelUpdated);
    }

    //http://localhost:8080/api/v1/youtube/update/category/build
    @RequestMapping(method = RequestMethod.GET, value = "update/category/build")
    public @ResponseBody ResponseEntity<?> buildTopCategories( ) throws Exception {
        Iterable<Category> categories = youtubeCategoryService.buildTopCategoryAll(true);
        
        return (categories == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(categories);
    }

    @RequestMapping(method = RequestMethod.GET, value = "update/channel/build/{categoryId}")
    public @ResponseBody ResponseEntity<?> buildTopChannels(@PathVariable String categoryId ) throws Exception {
        Object channels = youtubeCategoryService.buildAllChannelsFromCategory(categoryId, 25, null);

        return (channels == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(channels);
    }

    @RequestMapping(method = RequestMethod.GET, value = "update/channel/buildAll")
    public @ResponseBody ResponseEntity<?> buildTopChannelsForAllCategories( ) throws Exception {
        Object channels = youtubeCategoryService.buildAllChannelsForAllCategory( 25);

        return (channels == null) ? ResponseEntity.notFound().build() : ResponseEntity.ok(channels);
    }

}
