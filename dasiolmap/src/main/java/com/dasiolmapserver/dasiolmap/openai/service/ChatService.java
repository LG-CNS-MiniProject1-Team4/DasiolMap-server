package com.dasiolmapserver.dasiolmap.openai.service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.dasiolmapserver.dasiolmap.openai.domain.dto.ChatResponseDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

@Service
public class ChatService {
    @Value("${spring.ai.openai.model}")
    private String model;

    @Value("${spring.ai.openai.api.key}")
    private String key;

    @Value("${spring.ai.openai.api.url}")
    private String url;

    private final OkHttpClient client = new OkHttpClient();
    private final ObjectMapper mapper = new ObjectMapper();

    public ChatResponseDTO recommendStore(List<String> userTags, String storeName) {
        System.out.println(">>> service recommendRestaurant");
        String prompt = " 너는 멋진 인공지능이야." +
                "유저들이 가지고 있는 태그를 기반으로 가게 소개와 추천 이유를 작성해줘" +
                "현재 유저가 가지고 있는 태그들은 '" + userTags + " 이고, 가게 이름은 " + storeName + "'이야.\n" +
                "만약에 userTags가 없다면 네가 스스로 이유를 만들어\n" +
                "아래 JSON 형식으로만 응답해주고 백틱 ` 쓰지마!!\n" +
                "{\"storeName\":\"<가게명>\",\"reason\":\"<추천이유>\"}";
        // messages 생성
        Map<String, Object> systemMsg = new HashMap<>();
        systemMsg.put("role", "system");
        systemMsg.put("content", "넌 반드시 JSON 포멧을 지킬 수 있는 AI Model 이야.");

        Map<String, Object> userMsg = new HashMap<>();
        userMsg.put("role", "user");
        userMsg.put("content", prompt);

        Map<String, Object> requestMsg = new HashMap<>();
        requestMsg.put("model", model);
        requestMsg.put("messages", List.of(systemMsg, userMsg));

        // Object -> json 문자열로 변환해서 요청
        String json = null;
        try {
            json = mapper.writeValueAsString(requestMsg);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(">>>> request json");
        System.out.println(json);

        // 요청
        Request request = new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + key)
                .header("Content-Type", "application/json")
                .post(RequestBody.create(json, MediaType.parse("application/json")))
                .build();
        String responseJson = null;
        try {
            Response response = client.newCall(request).execute();
            System.out.println(">>>>> response ");

            responseJson = response.body().string();
            JsonNode node = mapper.readTree(responseJson);
            ;
            return mapper.readValue(node.at("/choices/0/message/content").asText(), ChatResponseDTO.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
