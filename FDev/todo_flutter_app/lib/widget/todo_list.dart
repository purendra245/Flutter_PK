import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:todo_flutter_app/widget/todo_tile_list.dart';

import '../TaskNotif.dart';

class TodoList extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Consumer<TaskNotif>(
      builder: (context, taskData, child) => ListView.builder(
        itemBuilder: (BuildContext buildContext, int index) {
          return TodoTileList(
            taskName: taskData.getTaskList()[index].lable,
            isTaskDone: taskData.getTaskList()[index].isDone,
            checkBox: (newValue) {
              taskData.setTaskDone(taskData.getTaskList()[index]);
            },
          );
        },
        itemCount: taskData.getTaskList().length,
      ),
    );
  }
}
