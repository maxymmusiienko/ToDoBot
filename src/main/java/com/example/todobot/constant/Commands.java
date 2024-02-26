package com.example.todobot.constant;

public class Commands {
  public static final String START_COMMAND = "/start";
  public static final String INFO_COMMAND = "/info";
  public static final String INVALID_COMMAND_MESSAGE = "There is no such command";
  public static final String SHOW_COMMAND = "/show";
  public static final String ADD_POINT_MESSAGE = "You successfully added a point!\nHere is updated list:\n";
  public static final String DELETE_POINT_MESSAGE = "You successfully deleted a point!\nHere is an updated list:\n";
  public static final String UPDATE_POINT_MESSAGE = "You successfully updated a point!\nHere is an updated list:\n";
  public static final String ADD_COMMAND = "/add";
  public static final String UPDATE_COMMAND = "/update";
  public static final String DELETE_COMMAND = "/delete";
  public static final String DONE_COMMAND = "/done";
  public static final String DONE_POINT_MESSAGE = "Great job! This point is marked as done!\nHere is an updated list:\n";
  public static final String START_COMMAND_MESSAGE = "Hi! This is ToDoBot." +
      " Use command /info to watch more commands and create your own list!";
  public static final String INFO_COMMAND_MESSAGE = """
      Here are all possible commands:
      /add (point) -> add a new point to list
      /done (number) -> mark point as done
      /show -> look at the current list
      /delete (number) -> delete a point from list
      /stat -> see unique statistic of your toDo list""";
  public static final String STATS_INTRO = "Here are your toDo stats!";
  public static final String TOTAL_IN_LIST_MESSAGE = "Total points in list: ";
  public static final String TOTAL_DONE_MESSAGE = "Total points done: ";
  public static final String FASTEST_DONE_MESSAGE = "Point that took min time: ";
  public static final String THE_OLDEST_UNDONE_MESSAGE = "The longest pending point: ";
  public static final String TOTAL_POINTS_TO_BE_DONE_MESSAGE = "Total pending points: ";
  public static final String ALL_POINTS_DONE_MESSAGE = "You have done all the points, keep going!";
  public static final String ZERO_DONE_MESSAGE = "You haven`t done any points yet";
  public static final String STAT_COMMAND = "/stat";
  public static final String REQUEST_ADD_POINT = "Please, type the toDo point you want to add";
  public static final String REQUEST_DELETE_NUMBER = "Please, type the point number you want to delete";
  public static final String REQUEST_DONE_NUMBER = "Please, type the point number you have done";
}
