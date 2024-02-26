package com.example.todobot.repository;

import com.example.todobot.model.ListPoint;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ListPointRepository extends JpaRepository<ListPoint, Long> {
  ListPoint getListPointByUserIdAndPointNumber(Long userId, Long pointNumber);
  Long countListPointsByUserId(Long userId);
  List<ListPoint> findAllByUserId(Long userId);
  Long countAllByUserId(Long userId);
}
