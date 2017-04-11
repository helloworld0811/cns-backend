package com.cns.backend.service;

import com.cns.backend.service.youtube.YoutubeCategoryService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by hku on 07.04.17.
 */
public class YoutubeCategoryServiceTest {

    @Autowired
    YoutubeCategoryService youtubeCategoryService;

    @Test
    public void testCategoryBuildAll()
    {

        try {
            youtubeCategoryService.buildTopCategoryAll(true);
            List<String> topYoutubeCategoriesStored = youtubeCategoryService.getTopYoutubeCategoriesStored(5);
            for(String categoryId:topYoutubeCategoriesStored)
            {
                youtubeCategoryService.buildAllChannelsFromCategory(categoryId, 25, null);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
