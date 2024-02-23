package com.example.todobot.service;

import com.example.todobot.model.ListPoint;
import com.example.todobot.repository.ListPointRepository;
import org.springframework.stereotype.Service;

@Service
public class ListPointServiceImpl implements ListPointService {
  private final ListPointRepository listPointRepository;

  public ListPointServiceImpl(ListPointRepository listPointRepository) {
    this.listPointRepository = listPointRepository;
  }

  @Override
  public ListPoint addPoint(Long userId, String point) {
    ListPoint listPoint = new ListPoint();
    listPoint.setUserId(userId);
    listPoint.setPointMessage(point);
    Long pointNumber = listPointRepository.countListPointsByUserId(userId) + 1;
    listPoint.setPointNumber(pointNumber);
    return listPointRepository.save(listPoint);
  }

  @Override
  public ListPoint markPointAsDone(Long userId, Long pointNumber) {
    ListPoint listPoint = listPointRepository.getListPointByUserIdAndPointNumber(userId, pointNumber);
    listPoint.setDone(true);
    return listPointRepository.save(listPoint);
  }

  @Override
  public void deletePoint(Long userId, Long pointNumber) {
    ListPoint listPoint = listPointRepository.getListPointByUserIdAndPointNumber(userId, pointNumber);
    listPoint.setDeleted(true);
    listPointRepository.save(listPoint);
  }
}
