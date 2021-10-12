package com.sg.account.config;

import com.sg.account.dto.TransferOperationDTO;
import com.sg.account.model.TransferCommand;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.List;

@Configuration
@EnableConfigurationProperties(RedisProperties.class)
public class RedisConfig {

    @Bean
    public RedisTemplate<String, List<TransferOperationDTO>> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, List<TransferOperationDTO>> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);
        return template;
    }
}
