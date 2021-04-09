package com.redis.example.Product.repo;

import com.redis.example.Product.model.Item;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class ItemRepo {

    public static final String KEY = "ITEM";
    private RedisTemplate<String, Item> redisTemplate;
    private HashOperations hashOperations;

    public ItemRepo(RedisTemplate<String, Item> redisTemplate) {
        this.redisTemplate = redisTemplate;
        hashOperations = redisTemplate.opsForHash();
    }


    public Map<Integer,Item> getAllItems(){
        return hashOperations.entries(KEY);
    }


    public Item getItem(int itemId){
        return (Item) hashOperations.get(KEY,itemId);
    }


    public void addItem(Item item){
        hashOperations.put(KEY,item.getId(),item);
    }


    public void deleteItem(int id){
        hashOperations.delete(KEY,id);
    }


    public void updateItem(Item item){
        addItem(item);
    }
}