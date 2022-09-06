package com.example.restaurant.wishlist.service;

import com.example.restaurant.naver.NaverClient;
import com.example.restaurant.naver.dto.SearchLocalReq;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WishListService {

    private final NaverClient naverClient;

    public void search(String query) {
        //지역 검색
        var searchLocalReq = new SearchLocalReq();


        //이미지 검색

        //결과를 리턴
    }
}
