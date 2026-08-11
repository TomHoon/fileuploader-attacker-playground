//package com.example.fileuploader_attacker.util.health;
//
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.http.MediaType;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestClient;
//
//import java.time.LocalDateTime;
//import java.util.Map;
//
//@Slf4j
//@Service
//public class HealthCheckerService {
//
//    private final RestClient restClient = RestClient.create();
//
//    private static final String WEBHOOK_URL =
//            "https://discord.com/api/webhooks/1532402235462516796/K036Kun4NPQ6qKUpZBvsfxZsI6C9iNAxsq0Ow9Tov10sW393A8cQ2v4S6cZjEMu_gTDJ";
//
//
//    @Scheduled(fixedRate = 10000)
//    public void send() {
//        try {
//            String response = restClient.get()
//                    .uri("https://daewoo.digital2")
//                    .retrieve()
//                    .body(String.class);
//
//            restClient.post()
//                    .uri(WEBHOOK_URL)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(Map.of(
//                            "content", "정상 : " + LocalDateTime.now()
//                    ))
//                    .retrieve()
//                    .toBodilessEntity();
//
//            log.info("헬스체크 성공: {}", response);
//        } catch (Exception e) {
//            restClient.post()
//                    .uri(WEBHOOK_URL)
//                    .contentType(MediaType.APPLICATION_JSON)
//                    .body(Map.of(
//                            "content", "서버다운 : " + LocalDateTime.now()
//                    ))
//                    .retrieve()
//                    .toBodilessEntity();
//        }
//    }
//}
