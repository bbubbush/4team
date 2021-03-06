package com.bbubbush.movie.service;

import com.bbubbush.config.YamlConfig;
import com.bbubbush.movie.dto.MovieSearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

@Service
public class MovieService {
    @Autowired
    YamlConfig yamlConfig;

    public Map<String, Object> getDailyMovie(MovieSearchDTO dto) {
        // DTO를 Map으로 변환
        Map<String, Object> param = this.dtoToMap(dto);
        // 발급키
        String key = yamlConfig.getKey();

        // Rest Client 통해 호출

        HashMap<String, Object> dailyResult = null;
//        KobisOpenAPIRestService service = new KobisOpenAPIRestService(key);
//
//        try {
//            String dailyResponse = service.getDailyBoxOffice(true, param);
//
//            // JSON 사용
//            ObjectMapper mapper = new ObjectMapper();
//            dailyResult = mapper.readValue(dailyResponse, HashMap.class);
//            LinkedHashMap<String, Object> boxOfficeResult = (LinkedHashMap<String, Object>)dailyResult.get("boxOfficeResult");
//
//            dailyResult = boxOfficeResult;
//            dailyResult.put("data", dailyResult.get("dailyBoxOfficeList"));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



        return dailyResult;
    }   // end of getDailyMovie method

    // DTO를 Map으로 변환하는 메서드
    public Map<String, Object> dtoToMap (MovieSearchDTO dto) {
        Map<String, Object> inParam = new HashMap<>();
        try {
            Field[] fields = dto.getClass().getDeclaredFields();    // MovieSearchDTO의 모든 필드를 받음

            for (Field field: fields) {
                String fieldName = field.getName(); // 필드의 이름
                String methodName = fieldName.replaceFirst  (
                                                                fieldName.substring(0, 1),
                                                                fieldName.substring(0, 1).toUpperCase()
                                                            ); // 필드의 첫번째 이름을 대문자로 변경(getter형식에 맞추기위해)
                inParam.put(fieldName, dto.getClass().getDeclaredMethod("get" + methodName).invoke(dto));   // key, value형태로 저장
            }
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return inParam;
    }   // end of dtoToMap method


}
