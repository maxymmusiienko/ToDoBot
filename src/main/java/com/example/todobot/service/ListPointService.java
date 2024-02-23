package com.example.todobot.service;

import com.example.todobot.model.ListPoint;

public interface ListPointService {
  ListPoint addPoint(Long userId, String point);

  ListPoint markPointAsDone(Long userId, Long pointNumber);

  void deletePoint(Long userId, Long pointNumber);
}
