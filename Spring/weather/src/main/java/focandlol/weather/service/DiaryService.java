package focandlol.weather.service;

import focandlol.weather.WeatherApplication;
import focandlol.weather.domain.DateWeather;
import focandlol.weather.domain.Diary;
import focandlol.weather.error.InvalidDate;
import focandlol.weather.repository.DateWeatherRepository;
import focandlol.weather.repository.DiaryRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DiaryService {

    private final DiaryRepository diaryRepository;
    private final DateWeatherRepository dateWeatherRepository;
    private static final Logger logger = LoggerFactory.getLogger(WeatherApplication.class);

    @Value("${openweathermap.key}")
    private String apiKey;

    @Transactional
    @Scheduled(cron = "0 0 1 * * *")
    public void saveWeatherDate(){
        logger.info("날씨 데이터 가져옴");
        dateWeatherRepository.save(getWeatherFromApi());
    }

    @Transactional
    public void createDiary(LocalDate date, String text) {
        logger.info("started to create diary");
        //날씨 데이터 가져오기
        DateWeather dateWeather = getDateWeather(date);


        //파싱된 데이터 + 일기 값 db에 넣기
        Diary diary = new Diary();
        diary.dateWeather(dateWeather);
        diary.setText(text);

        diaryRepository.save(diary);
        logger.info("end to create diary");
    }

    public List<Diary> readDiary(LocalDate date) {
//        if(date.isAfter(LocalDate.ofYearDay(3050,1))){
//            throw new InvalidDate();
//        }
        return diaryRepository.findAllByDate(date);
    }

    public List<Diary> readDiaries(LocalDate startDate, LocalDate endDate) {
        return diaryRepository.findAllByDateBetween(startDate, endDate);
    }

    @Transactional
    public void updateDiary(LocalDate date, String text) {
        Diary getDiary = diaryRepository.getFirstByDate(date);
        getDiary.setText(text);
    }

    @Transactional
    public void deleteDiary(LocalDate date) {
        diaryRepository.deleteAllByDate(date);
    }

    private String getWeatherString(){
        String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=seoul&appid=" + apiKey;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            int responseCode = con.getResponseCode();
            BufferedReader br;
            if (responseCode == 200) {
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            }else{
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuilder response = new StringBuilder();
            while((inputLine = br.readLine()) != null){
                response.append(inputLine);
            }
            br.close();

            return response.toString();
        } catch (Exception e) {
            return "failed to get response";
        }
    }

    private Map<String,Object> parseWeather(String jsonString){
        JSONParser parser = new JSONParser();
        JSONObject jsonObject;

        try{
            jsonObject = (JSONObject) parser.parse(jsonString);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        Map<String, Object> resultMap = new HashMap<String, Object>();

        JSONArray weatherArray = (JSONArray) jsonObject.get("weather");
        JSONObject weatherData = (JSONObject) weatherArray.get(0);
        resultMap.put("main", weatherData.get("main"));
        resultMap.put("icon", weatherData.get("icon"));

        JSONObject mainData = (JSONObject) jsonObject.get("main");
        resultMap.put("temp",mainData.get("temp"));

        return resultMap;
    }

    private DateWeather getWeatherFromApi(){
        //날씨 데이터 가져오기
        String weatherData = getWeatherString();

        //받아온 날씨 json 파싱하기
        Map<String, Object> parsedWeather = parseWeather(weatherData);

        DateWeather dateWeather = new DateWeather();
        dateWeather.setDate(LocalDate.now());
        dateWeather.setWeather(parsedWeather.get("main").toString());
        dateWeather.setTemperature((Double) parsedWeather.get("temp"));
        dateWeather.setIcon(parsedWeather.get("icon").toString());
        return dateWeather;
    }

    private DateWeather getDateWeather(LocalDate date){
        List<DateWeather> dateWeatherListFromDb = dateWeatherRepository.findAllByDate(date);
        if(dateWeatherListFromDb.size() == 0){
            return getWeatherFromApi();
        }
        return dateWeatherListFromDb.get(0);
    }


}
