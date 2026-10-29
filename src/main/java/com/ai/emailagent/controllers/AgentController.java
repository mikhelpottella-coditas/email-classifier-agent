package com.ai.emailagent.controllers;

import com.ai.emailagent.services.AgentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class AgentController {

    private final AgentService agentService;


    @GetMapping("ask")
    public String ask(@RequestParam String text) {
        return agentService.ask(text);
    }

}
