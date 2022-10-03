package com.example.demo.services;

import com.example.demo.dto.TweetCreateDTO;
import com.example.demo.exception.tweet.TweetCouldNotBeCreatedException;
import com.example.demo.model.Tweet;
import com.example.demo.model.User;
import com.example.demo.repository.TweetRepository;
import com.example.demo.security.ICustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class TweetService implements ITweetService{
    @Autowired
    private TweetRepository tweetRepo;

    @Autowired
    private ICustomUserDetailsService customUserDetailsService;

    public void softDelete(UUID id){
        var tweet = tweetRepo.findById(id).orElseThrow();
        tweet.setDeleted(true);
    }

    @Override
    public Tweet create(TweetCreateDTO dto) {
        Tweet tweet = new Tweet();
        tweet.setText(dto.getText());

        User user = customUserDetailsService.getLoggedInUser();

        // set tweet user and add tweet to the user
        tweet.setUser(user);
        user.addTweet(tweet);

        // save tweet
        tweetRepo.save(tweet);

        if(tweet == null) throw new TweetCouldNotBeCreatedException();
        return tweet;

    }
}
