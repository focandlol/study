package focandlol.weather.controller;

import focandlol.weather.domain.Diary;
import focandlol.weather.service.DiaryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;

    @Operation(summary = "일기 텍스트와 날씨를 이용해서 db에 일기 저장", description = "This API fetches example data.")
    @PostMapping("/create/diary")
    void createDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text) throws IllegalAccessException {
        //throw new IllegalAccessException("sa");
        diaryService.createDiary(date,text);
    }

    @Operation(summary = "선택한 날짜의 모든 일기 데이터 가져옵니다")
    @GetMapping("/read/diary")
    List<Diary> readDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date) throws IllegalAccessException {
        return diaryService.readDiary(date);
    }

    @Operation(summary = "선택한 기간중의 모든 일기 데이터 가져옵니다")
    @GetMapping("/read/diaries")
    List<Diary> readDiaries(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                            @Parameter(description = "조회할 기간의 첫번째날", example = "2024-11-15")LocalDate startDate
    ,@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE)
                            @Parameter(description = "조회할 기간의 마지막날", example = "2024-11-21") LocalDate endDate){

        return diaryService.readDiaries(startDate,endDate);
    }

    @PutMapping("/update/diary")
    void updateDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date,
                     @RequestBody String text){
        diaryService.updateDiary(date,text);
    }

    @DeleteMapping("/delete/diary")
    void deleteDiary(@RequestParam @DateTimeFormat(iso= DateTimeFormat.ISO.DATE) LocalDate date){
        diaryService.deleteDiary(date);
    }


}
