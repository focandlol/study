package focandlol.openaitest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class ApiService {

    @Value("${openai.api.key}")
    private String apiKey;

    @Value("${openai.api.url}")
    private String apiUrl;

    private final RestTemplate restTemplate = new RestTemplate();

    public String generateRecipeInKorean(String ingredients) {
        // Chat model 요청 메시지 작성
        OpenAiChatRequest.Message userMessage = new OpenAiChatRequest.Message(
                "user",
                "다음 재료를 사용하여 레시피를 작성해 주세요: " + ingredients +
                        ". 필요한 재료 목록, 조리 과정, 예상 조리 시간을 포함해 주세요. 한식 느낌으로 해주세요"
        );

        // 요청 객체 생성
        OpenAiChatRequest request = new OpenAiChatRequest(
                "gpt-4", // 또는 "gpt-4"
                Collections.singletonList(userMessage),
                1000,
                0.2
        );

        // 요청 헤더 설정
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + apiKey);

        // 요청 엔티티 생성
        HttpEntity<OpenAiChatRequest> entity = new HttpEntity<>(request, headers);

        // OpenAI API 호출
        ResponseEntity<OpenAiResponse> responseEntity = restTemplate.exchange(
                apiUrl + "/chat/completions",
                HttpMethod.POST,
                entity,
                OpenAiResponse.class
        );

        // 응답 처리
        OpenAiResponse response = responseEntity.getBody();
        if (response != null && response.getChoices() != null && !response.getChoices().isEmpty()) {
            return response.getChoices().get(0).getMessage().getContent();
        } else {
            throw new RuntimeException("OpenAI API 응답이 비어 있습니다.");
        }
    }
}
