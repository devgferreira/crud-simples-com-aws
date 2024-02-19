package com.gabriel.crudsimplescomaws.application.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.gabriel.crudsimplescomaws.application.dtos.MessageDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

      AmazonSNS _amazonSNS;
      Topic _catalogTopic;

    @Autowired
    public AwsSnsService(AmazonSNS amazonSNS, @Qualifier("catalogEventsTopic") Topic catalogTopic) {
        _amazonSNS = amazonSNS;
        _catalogTopic = catalogTopic;
    }

    public void publish(MessageDTO message){
        _amazonSNS.publish(_catalogTopic.getTopicArn(), message.getMessage());
    }
}
