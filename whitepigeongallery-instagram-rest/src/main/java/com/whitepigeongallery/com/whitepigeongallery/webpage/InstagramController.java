package com.whitepigeongallery.com.whitepigeongallery.webpage;

import org.brunocvcunha.instagram4j.Instagram4j;
import org.brunocvcunha.instagram4j.requests.InstagramSearchUsernameRequest;
import org.brunocvcunha.instagram4j.requests.InstagramUserFeedRequest;
import org.brunocvcunha.instagram4j.requests.payload.InstagramFeedItem;
import org.brunocvcunha.instagram4j.requests.payload.InstagramSearchUsernameResult;
import org.brunocvcunha.instagram4j.requests.payload.InstagramUser;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class InstagramController {

    private Instagram4j instagram;

    private InstagramUser instagramUser;

    public static String USER_NAME = "whitepigeongallery";

    public InstagramController() throws IOException {
        this.instagram = Instagram4j.builder()
                .username("whitepigeonsoftware")
                .password("uUkTCmgEH9Kk3TAWFRnmgHVkmdqVsRatTXUWk4wP\n").build();
        instagram.setup();
        instagram.login();

        instagramUser = login();
    }

    @RequestMapping("/instagramuser")
    public InstagramUser getInstagramUser() throws IOException {
        return instagramUser;
    }

    @RequestMapping("/feed")
    public List<InstagramFeedItem> getFeed() throws IOException {
        return this.instagram.sendRequest(new InstagramUserFeedRequest(instagramUser.getPk())).getItems();
    }


    private InstagramUser login() throws IOException {
        InstagramSearchUsernameResult instagramSearchUsernameResult = this.instagram
                .sendRequest(new InstagramSearchUsernameRequest(USER_NAME));
        return instagramSearchUsernameResult.getUser();
    }

}
