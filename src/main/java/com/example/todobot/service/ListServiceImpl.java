package com.example.todobot.service;

import com.example.todobot.model.ListPoint;
import com.example.todobot.repository.ListPointRepository;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class ListServiceImpl implements ListService {
  private final ListPointRepository listPointRepository;

  public ListServiceImpl(ListPointRepository listPointRepository) {
    this.listPointRepository = listPointRepository;
  }

  @Override
  public String prepareList(Long userId) {
    List<ListPoint> points = listPointRepository.findAllByUserId(userId);
    StringBuilder list = new StringBuilder();
    for (int i = 1; i <= points.size(); i++) {
      list.append(points.get(i - 1).getPointNumber()).append('.').append(points.get(i - 1).getPointMessage());
      if (points.get(i - 1).isDone()) {
        list.append('✅');
      }
      list.append(System.lineSeparator());
    }
    return list.toString();
  }
}
