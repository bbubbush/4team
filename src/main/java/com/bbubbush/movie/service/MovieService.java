package com.bbubbush.movie.service;

import com.bbubbush.config.YamlConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import kr.or.kobis.kobisopenapi.consumer.rest.KobisOpenAPIRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    YamlConfig yamlConfig;

    public Map<String, Object> getDailyMovie(Map<String, Object> inParam) {
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
