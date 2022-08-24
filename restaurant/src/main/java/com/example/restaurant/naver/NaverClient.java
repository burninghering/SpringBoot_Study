package com.example.restaurant.naver;

import com.example.restaurant.naver.dto.SearchImageReq;
import com.example.restaurant.naver.dto.SearchImageRes;
import com.example.restaurant.naver.dto.SearchLocalReq;
import com.example.restaurant.naver.dto.SearchLocalRes;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class NaverClient {

    @Value("${naver.client.id}")
    private String naverClientId;

    @Value("${naver.client.secret}")
    private String naverClientSecret;

    @Value("https://openapi.naver.com/v1/search/local.json")
    private String naverLocalSearchUrl;

    @Value("https://openapi.naver.com/v1/search/image")
    private String naverImageSearchUrl;

    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq){
        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
                .queryParams(searchLocalReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchLocalRes>(){};


        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }


    public SearchImageRes searchImage(SearchImageReq searchImageReq){
        var uri = UriComponentsBuilder.fromUriString(naverImageSearchUrl)
                .queryParams(searchImageReq.toMultiValueMap())
                .build()
                .encode()
                .toUri();

        var headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id", naverClientId);
        headers.set("X-Naver-Client-Secret", naverClientSecret);
        headers.setContentType(MediaType.APPLICATION_JSON);

        var httpEntity = new HttpEntity<>(headers);
        var responseType = new ParameterizedTypeReference<SearchImageRes>(){};


        var responseEntity = new RestTemplate().exchange(
                uri,
                HttpMethod.GET,
                httpEntity,
                responseType
        );

        return responseEntity.getBody();
    }

}


//    public SearchLocalRes searchLocal(SearchLocalReq searchLocalReq){
//        //요청 주소
//        var uri = UriComponentsBuilder.fromUriString(naverLocalSearchUrl)
//                .queryParams(searchLocalReq.toMultiValueMap())
//                .build()
//                .encode()
//                .toUri();
//
//        //헤더 준비
//       var headers = new HttpHeaders();
//        headers.set("X-Naver-Client-Id",naverClientId);
//        headers.set("X-Naver-Client-Secret",naverClientSecret);
//        headers.setContentType(MediaType.APPLICATION_JSON);
//
//        //request
//        var httpEntity=new HttpEntity<>(headers);
//        //responseType
//        var responseType=new ParameterizedTypeReference<SearchLocalRes>(){};
//
//        //위 2개 준비해서 RestTemplate을 사용해 결과 얻어오기
//        var responseEntity=new RestTemplate().exchange(
//                uri,
//                HttpMethod.GET,
//                httpEntity,
//                responseType
//        );
//        //getBody() 통해 결과 리턴시키기
//        return responseEntity.getBody();
//    }
