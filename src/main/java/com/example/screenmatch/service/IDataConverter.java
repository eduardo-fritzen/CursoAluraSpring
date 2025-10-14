package com.example.screenmatch.service;

public interface IDataConverter {
    <T> T getData(String json, Class<T> classConverter);
}
