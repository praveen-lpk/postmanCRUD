package com.spring.postmanCRUD.controller;

import com.spring.postmanCRUD.model.KeyValue;
import com.spring.postmanCRUD.service.CacheService;
import com.spring.postmanCRUD.service.LFUService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("v1/lfu")
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @PostMapping
    public ResponseEntity<HttpStatus> postKeyValue(@RequestBody KeyValue keyvalue){
        cacheService.postKeyValue(keyvalue);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<KeyValue> getValue(@RequestParam("key") String key){
        KeyValue keyValue = cacheService.getValue(key);
        if(keyValue==null) return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return ResponseEntity.ok(keyValue);
    }

}
