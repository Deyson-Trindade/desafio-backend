package br.com.deyson.vuttr.config;

import java.util.TimeZone;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.zalando.problem.jackson.ProblemModule;
import org.zalando.problem.violations.ConstraintViolationProblemModule;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JacksonConfig {

    @Value("${spring.jackson.time-zone:America/Sao_Paulo}")
    private String timeZone;

    @Autowired
    private ObjectMapper objectMapper;

    @PostConstruct
    public void timeZoneDefault() {

        TimeZone.setDefault(TimeZone.getTimeZone(timeZone));
        objectMapper.registerModules(new ProblemModule(), new ConstraintViolationProblemModule());

    }

}
