package com.example.demo.controller.tweet;

import com.example.demo.dto.TweetCreateDTO;
import com.example.demo.exception.tweet.TweetCouldNotBeCreatedException;
import com.example.demo.model.Tweet;
import com.example.demo.services.ITweetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TweetController {

    @Autowired
    private ITweetService tweetService;

    @PostMapping("/api/tweet/create")
    public ResponseEntity<String> createNewTweet(@RequestBody TweetCreateDTO dto){
        try{
            Tweet tweet = tweetService.create(dto);
            return ResponseEntity.ok("tweet created: " + tweet.getText());
        }catch(TweetCouldNotBeCreatedException ex){

        }
        return null;
    }
}
