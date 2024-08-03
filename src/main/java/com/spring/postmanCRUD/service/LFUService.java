package com.spring.postmanCRUD.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.spring.postmanCRUD.model.KeyValue;

import java.util.HashMap;

@Service
public class LFUService implements CacheService{
    HashMap<String,String> keyValues;
    HashMap<String,Integer> frequency;
    private static final Integer MAX_SIZE = 5;

    public LFUService() {
        this.keyValues = new HashMap<>();
        this.frequency = new HashMap<>();
    }

    public ResponseEntity<HttpStatus> postKeyValue(KeyValue keyvalue){
        if(keyValues.containsKey(keyvalue.getKey())){
            keyValues.put(keyvalue.getKey(),keyvalue.getValue());
            frequency.put(keyvalue.getKey(),frequency.get(keyvalue.getKey())+1);
        } else{
            if(keyValues.size()>=MAX_SIZE){
                int minFrequency = Integer.MAX_VALUE;
                String keyToEvict = "";
                for(String key : frequency.keySet()){
                    if(frequency.get(key)<minFrequency){
                        minFrequency = frequency.get(key);
                        keyToEvict = key;
                    }
                }
                frequency.remove(keyToEvict);
                keyValues.remove(keyToEvict);
            }
            keyValues.put(keyvalue.getKey(),keyvalue.getValue());
                frequency.put(keyvalue.getKey(),1);
        }
        System.out.println("Update success");
        System.out.println(keyValues);
        System.out.println(frequency);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public KeyValue getValue(String key){
        if(!keyValues.containsKey(key)) return null;
        frequency.put(key,frequency.get(key)+1);
        System.out.println("Key exists");
        System.out.println(keyValues);
        System.out.println(frequency);
        return new KeyValue(key,keyValues.get(key));
    }
}
