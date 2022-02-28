import 'package:flutter/material.dart';

import 'TaskModel.dart';

class TaskNotif extends ChangeNotifier {
  List<TaskModel> _listTask = [];

  void setTaskData(TaskModel taskModel) {
    _listTask.add(taskModel);
    notifyListeners();
  }

  List<TaskModel> getTaskList() {
    return _listTask;
  }

  int getTaskCount() {
    return _listTask.length;
  }

  void setTaskDone(TaskModel taskModel) {
    taskModel.setTaskDone();
    notifyListeners();
  }
}
