package com.spring.postmanCRUD.service;

import com.spring.postmanCRUD.model.KeyValue;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public interface CacheService {
    ResponseEntity<HttpStatus> postKeyValue(KeyValue keyvalue);
    KeyValue getValue(String key);
}
