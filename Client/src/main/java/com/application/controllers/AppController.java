package com.application.controllers;


import com.application.dto.OfferDto;
import com.application.dto.Enum.Answer;
import lombok.AllArgsConstructor;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
@AllArgsConstructor
public class AppController {
    @GetMapping("/offers")
    public String home(Model model){
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        String url = "http://localhost:8080/offers/"+userName;
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<List<OfferDto>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){});
        List<OfferDto> objects = response.getBody();
        model.addAttribute("offers", objects);
        model.addAttribute("user", userName);
        return "offers";
    }

    @PostMapping("/offers/{id}")
    public String updateOffer(@PathVariable long id, @RequestParam(name = "answer") Answer answer){
        String url = "http://localhost:8080/update/"+id;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        MultiValueMap<String, Answer> map= new LinkedMultiValueMap<>();
        map.add("answer", answer);
        HttpEntity<MultiValueMap<String, Answer>> request = new HttpEntity<>(map, headers);
        restTemplate.postForEntity( url, request , String.class );

        return "redirect:/offers";
    }
}
