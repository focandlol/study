package focandlol.weather.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import focandlol.weather.domain.DateWeather;
import focandlol.weather.domain.Diary;
import focandlol.weather.repository.DateWeatherRepository;
import focandlol.weather.repository.DiaryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.ByteArrayInputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DiaryServiceTest {

    @Mock
    DiaryRepository diaryRepository;

    @Mock
    DateWeatherRepository dateWeatherRepository;

    @Spy
    @InjectMocks
    DiaryService diaryService;

    @Test
    void testGetWeatherFromApi() throws Exception {
        // DiaryService를 Spy로 생성

        // openHttpConnection 메서드 모의(Mock)
        HttpURLConnection mockConnection = Mockito.mock(HttpURLConnection.class);
        doReturn(mockConnection).when(diaryService).openHttpConnection(anyString());

        // HttpURLConnection 동작 설정
        given(mockConnection.getResponseCode()).willReturn(200);
        given(mockConnection.getInputStream()).willReturn(
                new ByteArrayInputStream((
                        "{\n" +
                                "  \"weather\": [{\"main\": \"Clear\", \"icon\": \"01d\"}],\n" +
                                "  \"main\": {\"temp\": 23.1}\n" +
                                "}").getBytes())
        );

        // 테스트 실행
        DateWeather result = diaryService.getWeatherFromApiForTest();

        // 검증
        assertEquals(LocalDate.now(), result.getDate());
        assertEquals("Clear", result.getWeather());
        assertEquals(23.1, result.getTemperature());
        assertEquals("01d", result.getIcon());
    }

    @Test
    void testGetWeatherString_success() throws Exception {
        // HttpURLConnection 모의(Mock)
        HttpURLConnection mockConnection = Mockito.mock(HttpURLConnection.class);

        // createURL 메서드가 특정 URL을 반환하도록 설정
        doReturn(mockConnection).when(diaryService).openHttpConnection(anyString());

        // HttpURLConnection 동작 설정
        given(mockConnection.getResponseCode()).willReturn(200);
        given(mockConnection.getInputStream()).willReturn(
                new ByteArrayInputStream((
                        "{\n" +
                                "  \"weather\": [{\"main\": \"Clear\", \"icon\": \"01d\"}],\n" +
                                "  \"main\": {\"temp\": 23.1}\n" +
                                "}").getBytes())
        );

        // 테스트 실행
        String response = diaryService.getWeatherStringForTest();

        // JSON 문자열 비교 대신 JSON 객체로 비교
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(
                "{\n" +
                        "  \"weather\": [{\"main\": \"Clear\", \"icon\": \"01d\"}],\n" +
                        "  \"main\": {\"temp\": 23.1}\n" +
                        "}");
        JsonNode jsonNode1 = objectMapper.readTree(response);

        assertEquals(jsonNode, jsonNode1);
    }

    @Test
    void testParseWeather() {
        String jsonString = "{\n" +
                "  \"weather\": [{\"main\": \"Clear\", \"icon\": \"01d\"}],\n" +
                "  \"main\": {\"temp\": 23.1}\n" +
                "}";

        Map<String, Object> result = diaryService.parseWeatherForTest(jsonString);

        assertEquals("Clear", result.get("main"));
        assertEquals("01d", result.get("icon"));
        assertEquals(23.1, result.get("temp"));
    }

    @Test
    void testParseWeather_InvalidJson() {
        String invalidJson = "adfadsfasfasdf";
        assertThrows(RuntimeException.class, () -> diaryService.parseWeatherForTest(invalidJson));
    }
}