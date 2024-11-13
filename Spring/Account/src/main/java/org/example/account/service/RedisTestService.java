package org.example.account.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@Slf4j
@RequiredArgsConstructor
public class RedisTestService {

    private final RedissonClient redissonClient;

    public String getLock(){
        RLock lock = redissonClient.getLock("sampleLock");

        try{
            boolean isLock = lock.tryLock(1, 3, TimeUnit.SECONDS);
            if(!isLock){
                log.error("==========lock acquisition failed============");
                return "lock failed";
            }
        }catch(Exception e){
            log.error("Redis lock Failed");
        }

        return "get lock success";
    }
}