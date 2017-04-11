package com.cns.backend.service.youtube;

import com.cns.backend.model.CNSChannel;
import com.cns.backend.model.Category;
import com.cns.backend.model.ChannelType;
import com.cns.backend.repository.CategoryRepository;
import com.cns.backend.repository.ChannelRepository;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.youtube.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hku on 07.04.17.
 */

@Service
public class YoutubeCategoryService {

    @Autowired
    private ChannelRepository channelRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    public Iterable<Category> buildTopCategoryAll(boolean updateExisting) throws Exception
    {
        String requestUrl = YoutubeConstants.API_CATEGORIES_ID_URL;
        System.out.println("requested Url for categories ids stats:" + requestUrl);
        List<Category> categories=new ArrayList<>();
        try {
            String json = JSONUtil.readJsonFromUrl(requestUrl);
            GuideCategoryListResponse guideCategoryListResponse=convertJSONToGuideCategoryListResponse(json);
            List<GuideCategory> guideCategories = guideCategoryListResponse.getItems();
            for(GuideCategory guideCategory:guideCategories)
            {
               String categoryId= guideCategory.getId();
               String title=guideCategory.getSnippet().getTitle();
               Category category=new Category();
               category.setCategoryName(title);
               category.setChannelCategoryId(categoryId);
               category.setChannelType(ChannelType.YOUTUBE);
               category.setRoot(true);
               category.setParentCategoryId(null);
               categories.add(category);
            }
            Iterable<Category> save = categoryRepository.save(categories);
            return save;

        } catch (Exception e) {
            throw new Exception("Error:" + e);
        }


    }

    public Iterable<CNSChannel> buildAllChannelsFromCategory(String channelCategoryId, Integer maximumChannelCount, Long categoryId) throws Exception
    {

        String requestUrl= YoutubeConstants.API_CHANNELS_BY_CATEGORY_URL;
        System.out.println("Request URL:"+requestUrl+ "#categoryId:"+channelCategoryId);

        requestUrl=requestUrl.replace("{CATEGORY_ID}", channelCategoryId).replace("{RESULT_COUNT}",Integer.toString(maximumChannelCount));
        System.out.println("Request URL:"+requestUrl);

        if(categoryId==null)
        {
            //TODO: get categoryId from DB by channelCategoryId
        }


        try{
           String jsonResponse=JSONUtil.readJsonFromUrl(requestUrl);
           SearchListResponse searchListResponse=convertJSONToSearchListResponse(jsonResponse);
           String regionCode=searchListResponse.getRegionCode();
           List<SearchResult> searchResults = searchListResponse.getItems();
           List<CNSChannel> cnsChannels= new ArrayList<>();
           for(SearchResult searchResult:searchResults)
           {
               // item should be of type channel
              // if(searchResult.getKind().equals("youtube#channel"))
              // {
                   String channelId= searchResult.getSnippet().getChannelId();
                   String title=searchResult.getSnippet().getTitle();
                   Long publishedAt=searchResult.getSnippet().getPublishedAt().getValue();
                   String description=searchResult.getSnippet().getDescription();

                   CNSChannel cnsChannel= new CNSChannel( ChannelType.YOUTUBE,  channelId, true, false, true);
                   cnsChannel.setTitle(title);
                   cnsChannel.setRegistered(false);
                   cnsChannel.setChannelType(ChannelType.YOUTUBE);
                   cnsChannel.setCountry(regionCode);
                   cnsChannel.setPublishedTime(publishedAt);
                   cnsChannel.setDescription(description);
                   cnsChannel.setDescriptionLocal(description);
                   cnsChannel.setCategoryId(categoryId);
                   cnsChannel.setTitleLocal(title);
                   System.out.println("CNS- CHANNEL:"+cnsChannel.toString());
                   cnsChannels.add(cnsChannel);

              // }
           }

               Iterable<CNSChannel> save=  channelRepository.save(cnsChannels);
               return save;


        }catch (Exception e)
        {
            throw  new Exception("Error" +e);
        }


    }

    public String buildAllChannelsForAllCategory(int maximumChannelsPerCategoryCount)  throws Exception
    {
        Iterable<Category> categories = categoryRepository.findAll();
        Integer count=0;
        for(Category category:categories)
        {
            count++;
            Long categoryId=category.getId();
            String  channelCategoryId=category.getChannelCategoryId();
            Iterable<CNSChannel> savedResults= buildAllChannelsFromCategory(channelCategoryId, 50, categoryId);

// TODO: fix channel creation for categories and remove break
            break;
        }
        return "Successfully generated channels for "+count+" categories";

    }


    public List<String> getTopYoutubeCategoriesStored(Integer count)
    {
        Iterable<Category> categories = categoryRepository.findAll();
        List<String> youtubeCategoryIdsStored= new ArrayList<>();
        int counter=1;
        for(Category category:categories)
        {

            if(category.getChannelType().equals(ChannelType.FACEBOOK) && category.getRoot() )
            {
               youtubeCategoryIdsStored.add(category.getChannelCategoryId());
               counter++;
            }
            if( counter>=count)
            {
                break;
            }
        }
        return youtubeCategoryIdsStored;
    }

    private static SearchListResponse convertJSONToSearchListResponse(String jsonString)
    {
        SearchListResponse searchListResponse=null;
        try{
            searchListResponse= YoutubeConstants.JSON_FACTORY.fromString(jsonString, SearchListResponse.class);
        }catch (IOException e) {
            e.printStackTrace();
        }
        return searchListResponse;
    }


    private GuideCategoryListResponse convertJSONToGuideCategoryListResponse(String jsonString) {
        System.out.println("category-lList" + jsonString);
        GuideCategoryListResponse guideCategoryListResponse = null;
        try {
            guideCategoryListResponse = YoutubeConstants.JSON_FACTORY.fromString(jsonString, GuideCategoryListResponse.class);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return guideCategoryListResponse;
    }

    public void updateCategory(Category category)
    {
        categoryRepository.save(category);
    }


}
