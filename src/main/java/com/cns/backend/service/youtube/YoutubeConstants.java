package com.cns.backend.service.youtube;

import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;

/**
 * Created by hku on 04.04.17.
 */
public class YoutubeConstants {

    public static final JsonFactory JSON_FACTORY = new JacksonFactory();

    public static String API_BASE_URL="https://www.googleapis.com/youtube/v3";

    // connecsi.app@gmail.com
    // connecsi2017

    // channel stats
    public static String API_CHANNEL_STATS_URL_PREFIX = "https://www.googleapis.com/youtube/v3/channels?part=snippet,statistics";

    // get channel id
    public static String API_CHANNEL_ID_URL_PREFIX ="https://www.googleapis.com/youtube/v3/channels"; //?key={YOUR_API_KEY}&forUsername={USER_NAME}&part=id


    public static String API_KEY = "AIzaSyD5vOoJEKltWf7bsY9j7nmnVEIW3tzIPk0";

    public static  String CLIENT_ID="284817125363-tankjd47qdmvv7l0b4eif2icp3r1u7vu.apps.googleusercontent.com";

    public static String CLIENT_SECRET="f11XL0aq5f95kvk0uFqC0MH7";

    public static String APP_NAME="";


    public static String DEFAULT_REGION="US";

    public static String DEFAULT_LANGUAGE="EN";

    public static String API_CATEGORIES_ID_URL="https://www.googleapis.com/youtube/v3/guideCategories?part=snippet&hl="+DEFAULT_LANGUAGE+"&regionCode="+DEFAULT_REGION+"&key="+API_KEY;

    public static String API_CHANNELS_BY_CATEGORY_URL="https://www.googleapis.com/youtube/v3/search?part=snippet&maxResults={RESULT_COUNT}&guideCategory={CATEGORY_ID}&type=channel&key="+API_KEY;

    //https://www.googleapis.com/youtube/v3/videos?part=statistics&id=kffacxfA7G4&key=AIzaSyAei1LOM_cbWAJmOqxiwYX-spyPtAa0dW8

    // https://www.googleapis.com/youtube/v3/channels?part=statistics&id=SWMb9NxQL9I6c&key=AIzaSyAei1LOM_cbWAJmOqxiwYX-spyPtAa0dW8

    //guide category Id
    //https://www.googleapis.com/youtube/v3/guideCategories?part=snippet&hl=en&regionCode=US&key=AIzaSyAei1LOM_cbWAJmOqxiwYX-spyPtAa0dW8

    // get channels with search query
    //https://www.googleapis.com/youtube/v3/search?part=snippet&q=bieber&type=channel&key=AIzaSyAei1LOM_cbWAJmOqxiwYX-spyPtAa0dW8

    // get channels with category Id
    //https://www.googleapis.com/youtube/v3/search?part=snippet&guideCategory=GCQmVhdXR5ICYgRmFzaGlvbg&type=channel&key=AIzaSyAei1LOM_cbWAJmOqxiwYX-spyPtAa0dW8

    //https://www.googleapis.com/youtube/v3/search?part=snippet&type=video&videoCategoryId=17&maxResults=50&key=AIzaSyAei1LOM_cbWAJmOqxiwYX-spyPtAa0dW8

    //https://www.googleapis.com/youtube/v3/channels?part=snippet&maxResults=5&categoryId=GCQmVhdXR5ICYgRmFzaGlvbg&key=AIzaSyAei1LOM_cbWAJmOqxiwYX-spyPtAa0dW8
}
