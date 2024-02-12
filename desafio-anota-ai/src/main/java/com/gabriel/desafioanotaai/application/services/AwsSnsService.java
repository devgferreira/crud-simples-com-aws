package com.gabriel.desafioanotaai.application.services;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.Topic;
import com.gabriel.desafioanotaai.application.dtos.MessageDTO;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class AwsSnsService {

    private final AmazonSNS _amazonSNS;
    private final Topic _catalogTopic;


    public AwsSnsService(AmazonSNS amazonSNS, @Qualifier("catalogEventTopic") Topic catalogTopic) {
        _amazonSNS = amazonSNS;
        _catalogTopic = catalogTopic;
    }

    public void publish(MessageDTO message){
        _amazonSNS.publish(_catalogTopic.getTopicArn(), message.toString());
    }
}
