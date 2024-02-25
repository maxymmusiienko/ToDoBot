package com.example.todobot.constant;

public class Commands {
  public static final String START_COMMAND = "/start";
  public static final String INFO_COMMAND = "/info";
  public static final String INVALID_COMMAND_MESSAGE = "There is no such command";
  public static final String SHOW_COMMAND = "/show";
  public static final String ADD_POINT_MESSAGE = "You successfully added a point";
  public static final String DELETE_POINT_MESSAGE = "You successfully deleted a point";
  public static final String UPDATE_POINT_MESSAGE = "You successfully updated a point";
  public static final String ADD_COMMAND = "/add";
  public static final String UPDATE_COMMAND = "/update";
  public static final String DELETE_COMMAND = "/delete";
  public static final String DONE_COMMAND = "/done";
  public static final String DONE_POINT_MESSAGE = "Great job! This point is marked as done";
  public static final String START_COMMAND_MESSAGE = "Hi! This is ToDoBot." +
      " Use command /info to watch more commands and create your own list!";
  public static final String INFO_COMMAND_MESSAGE = """
      Here are all possible commands:
      /add (point) -> add a new point to list
      /done (number) -> mark point as done
      /show -> look at the current list
      /delete (number) -> delete a point from list""";
}
