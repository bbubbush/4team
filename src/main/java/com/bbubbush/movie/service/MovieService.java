package com.bbubbush.movie.service;

import com.bbubbush.config.YamlConfig;
import com.bbubbush.movie.dto.MovieSearchDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    YamlConfig yamlConfig;

    public Map<String, Object> getDailyMovie(MovieSearchDTO dto) {
        Map<String, Object> inParam = new HashMap<>();
        try {

            Field[] fields = dto.getClass().getDeclaredFields();

            for (Field field: fields) {
                String fieldName = field.getName();
                String methodName = fieldName.replace(fieldName.substring(0, 1), fieldName.substring(0, 1).toUpperCase());
                inParam.put(fieldName, dto.getClass().getDeclaredMethod("get" + methodName).invoke(dto));
                System.out.println("fieldName :: " + fieldName);
                System.out.println("methodName :: " + methodName);
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


        // 조회일자
        String targetDt = "20190506";

        // 발급키
        String key = yamlConfig.getKey();
        System.out.println("key :: " + key);

        // Rest Client 통해 호출
        KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
        HashMap<String, Object> dailyResult = null;
        try {
            String dailyResponse = service.getDailyBoxOffice(true, inParam);

            // JSON 사용
            ObjectMapper mapper = new ObjectMapper();
            dailyResult = mapper.readValue(dailyResponse, HashMap.class);
            LinkedHashMap<String, Object> boxOfficeResult = (LinkedHashMap<String, Object>)dailyResult.get("boxOfficeResult");
            boxOfficeResult.forEach((k, value) -> {
                System.out.println("key :: " + k + ", value :: " + boxOfficeResult.get(k));
            });
            ArrayList<LinkedHashMap<String, Object>> dailyBoxOfficeList = (ArrayList<LinkedHashMap<String, Object>>)boxOfficeResult.get("dailyBoxOfficeList");
            for (LinkedHashMap<String, Object> map: dailyBoxOfficeList) {
                map.forEach((k, v) -> {
                    System.out.println("key :: " + k + ", value :: " + map.get(k));
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



        return dailyResult;
    }
}
