package com.codestar.HAMI.repository;

import com.codestar.HAMI.entity.Profile;
import com.codestar.HAMI.entity.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {

    List<Subscription> findByProfileId(Profile profile);

}
