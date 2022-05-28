package br.com.greetingservice.controller;

import br.com.greetingservice.configuration.GreetingConfiguration;
import br.com.greetingservice.domain.Greeting;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    private static final String TEMPLATE = "%s, %s!";
    private final AtomicLong counter = new AtomicLong();

    private final GreetingConfiguration greetingConfiguration;

    public GreetingController (GreetingConfiguration greetingConfiguration) {
        this.greetingConfiguration = greetingConfiguration;
    }

    @RequestMapping("/greeting")
    public Greeting greeting (@RequestParam(value = "name", defaultValue = "") String name){

        if(name.isEmpty()){
            name = greetingConfiguration.getDefaultValue();
        }

        return new Greeting(counter.incrementAndGet(), String.format(TEMPLATE,greetingConfiguration.getGreeting(), name));

    }
}
