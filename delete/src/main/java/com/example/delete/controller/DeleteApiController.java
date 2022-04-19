package com.example.delete.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DeleteApiController {

    @DeleteMapping("/delete/{userId}")
    public void delete(@PathVariable String userId, @RequestParam String account){

        System.out.println(userId);
        System.out.println(account);

        //delete -> 리소스 삭제 -> 무조건 200 (어쨌든 삭제했고, 삭제되어있기 때문에!)
    }
}
