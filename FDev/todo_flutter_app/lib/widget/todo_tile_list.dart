import 'package:flutter/material.dart';
import 'package:todo_flutter_app/widget/to_do_checkbox.dart';

class TodoTileList extends StatelessWidget {
  final String taskName;
  final bool isTaskDone;
  final Function checkBox;

  TodoTileList({this.taskName, this.isTaskDone, this.checkBox});

  @override
  Widget build(BuildContext context) {
    return ListTile(
      leading: Text(
        taskName,
        style: TextStyle(
          decoration: isTaskDone ? TextDecoration.lineThrough : null,
        ),
      ),
      trailing: ToDoCheckBox(
        isCheckBox: isTaskDone,
        onCheck: checkBox,
      ),
    );
  }
}
