package com.alibaba.cloud.integration.consumer.service.impl;

import com.alibaba.cloud.integration.consumer.service.PraiseService;
import org.springframework.stereotype.Service;

@Service
public class PraiseServiceImpl implements PraiseService {

    @Override
    public void praiseItem(Integer itemId) {
        // todo..
        System.out.println("点赞记录");
    }


}
