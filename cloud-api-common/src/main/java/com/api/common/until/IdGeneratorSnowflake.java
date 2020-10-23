package com.api.common.until;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.net.NetUtil;
import cn.hutool.core.util.IdUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Slf4j
public class IdGeneratorSnowflake {

    private long workerId=0; // 范围是0-31
    private long datacenterId=0; // 范围是0-31

    private Snowflake snowflake= IdUtil.createSnowflake(workerId,datacenterId);

    @PostConstruct
    public void init(){
        try {
            workerId= NetUtil.ipv4ToLong(NetUtil.getLocalhostStr());
            log.info("当前机器的workerId:{}",workerId);
        }catch (Exception e){
            e.printStackTrace();
            log.warn("当前workId获取失败",e);
            workerId=NetUtil.getLocalhostStr().hashCode();
        }

    }

    public synchronized long snowflakeId(){
        return snowflake.nextId();
    }

    public synchronized  long snowflakeId(long workerId,long datacenterId){
        Snowflake snowflake=IdUtil.createSnowflake(workerId,datacenterId);
        return snowflake.nextId();
    }


    public static void main(String[] args) {
        System.out.println(new IdGeneratorSnowflake().snowflakeId());
    }
}
