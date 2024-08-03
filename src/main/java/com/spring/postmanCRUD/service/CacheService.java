package com.spring.postmanCRUD.service;

import com.spring.postmanCRUD.model.KeyValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;

public interface CacheService {
    ResponseEntity<HttpStatus> postKeyValue(KeyValue keyvalue);
    KeyValue getValue(String key);
}
