package com.whitepigeongallery.webpage.instagramclient;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.*;

public class InstagramClientTest {

    @Test
    public void getUserName() throws IOException {
        // Login to instagram
        Instagram4j instagram = Instagram4j.builder()
                .username("whitepigeongallery")
                .password("t0sterk65").build();
        instagram.setup();
        instagram.login();

        InstagramSearchUsernameResult userResult = instagram.sendRequest(new InstagramSearchUsernameRequest("whitepigeongallery"));

        final Long userId = userResult.getUser().getPk();

        InstagramFeedResult instagramFeedResult =
                instagram.sendRequest(new InstagramUserFeedRequest(userId));

        List<InstagramFeedItem> items = instagramFeedResult.getItems();
    }
}