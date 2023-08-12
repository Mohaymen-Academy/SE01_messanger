package com.codestar.HAMI.service;

import com.codestar.HAMI.entity.Profile;
import com.codestar.HAMI.entity.Subscription;
import com.codestar.HAMI.repository.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SubscriptionService {
    @Autowired
    SubscriptionRepository subscriptionRepository;

    public List<Subscription> getSubscriptionsByProfileId(Profile profile) {

        return subscriptionRepository.findByProfileId(profile);
    }

}
