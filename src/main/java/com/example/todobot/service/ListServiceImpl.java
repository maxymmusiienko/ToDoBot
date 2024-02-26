package com.example.todobot.service;

import com.example.todobot.constant.Commands;
import com.example.todobot.model.ListPoint;
import com.example.todobot.repository.ListPointRepository;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
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

  @Override
  public String prepareStats(Long userId) {
    Long totalPoints = listPointRepository.countAllByUserId(userId);
    List<ListPoint> points = listPointRepository.findAllByUserId(userId);
    Long donePoints = countDone(points);
    Long undonePoints = totalPoints - donePoints;
    Optional<ListPoint> oldestUndone = getTheOldestUndonePoint(points);
    Optional<ListPoint> fastestDone = getTheFastestDonePoint(points);
    StringBuilder res = new StringBuilder();
    res.append(Commands.STATS_INTRO).append(System.lineSeparator())
        .append(Commands.TOTAL_IN_LIST_MESSAGE).append(totalPoints).append(System.lineSeparator())
        .append(Commands.TOTAL_DONE_MESSAGE).append(donePoints).append(System.lineSeparator())
        .append(Commands.TOTAL_POINTS_TO_BE_DONE_MESSAGE).append(undonePoints).append(System.lineSeparator())
        .append(Commands.THE_OLDEST_UNDONE_MESSAGE);
    if (oldestUndone.isEmpty()) {
      res.append(Commands.ALL_POINTS_DONE_MESSAGE);
    } else {
      res.append(oldestUndone.get().getPointMessage())
          .append(", ").append(oldestUndone.get().getDateAdded());
    }
    res.append(System.lineSeparator()).append(Commands.FASTEST_DONE_MESSAGE);
    if (fastestDone.isEmpty()) {
      res.append(Commands.ZERO_DONE_MESSAGE);
    } else {
      res.append(fastestDone.get().getPointMessage())
          .append(", ").append(fastestDone.get().getDateAdded())
          .append(" - ").append(fastestDone.get().getDateDone());
    }
    return res.toString();
  }

  private Optional<ListPoint> getTheOldestUndonePoint(List<ListPoint> points) {
    return points.stream()
        .filter(p -> !p.isDone())
        .max(Comparator.comparing(p -> Duration.between(p.getDateAdded(), LocalDateTime.now())));
  }

  private Optional<ListPoint> getTheFastestDonePoint(List<ListPoint> points) {
    return points.stream()
        .filter(ListPoint::isDone)
        .min(Comparator.comparing(p -> Duration.between(p.getDateAdded(), p.getDateDone())));
  }

  private Long countDone(List<ListPoint> points) {
    return points.stream()
        .filter(ListPoint::isDone)
        .count();
  }
}
