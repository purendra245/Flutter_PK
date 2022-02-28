import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:todo_flutter_app/TaskModel.dart';
import 'package:todo_flutter_app/TaskNotif.dart';
import 'package:todo_flutter_app/widget/add_task.dart';
import 'package:todo_flutter_app/widget/todo_list.dart';

class TaskScreen extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Scaffold(
      backgroundColor: Colors.blue[300],
      floatingActionButton: Builder(
        builder: (context) => FloatingActionButton(
          onPressed: () {
            showModalBottomSheet(
              context: context,
              builder: (context) => AddTaskScreen(
                (value) {
                  Provider.of<TaskNotif>(context)
                      .setTaskData(TaskModel(lable: value));
                },
              ),
            );
          },
          backgroundColor: Colors.blue[300],
          child: Icon(
            Icons.add,
          ),
        ),
      ),
      body: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: <Widget>[
          Column(
            children: <Widget>[
              Container(
                child: Padding(
                  padding:
                      EdgeInsets.only(left: 30, right: 30, top: 60, bottom: 20),
                  child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: <Widget>[
                      CircleAvatar(
                        radius: 30,
                        backgroundColor: Colors.white,
                        child: Icon(
                          Icons.list,
                          size: 30,
                          color: Colors.blue[300],
                        ),
                      ),
                      SizedBox(
                        height: 10,
                      ),
                      Text(
                        'Todey',
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 40,
                          fontWeight: FontWeight.w700,
                        ),
                      ),
                      Text(
                        '${Provider.of<TaskNotif>(context).getTaskCount()} tasks',
                        style: TextStyle(
                          color: Colors.white,
                          fontSize: 20,
                        ),
                      ),
                    ],
                  ),
                ),
              ),
            ],
          ),
          Expanded(
            child: Container(
              padding: EdgeInsets.only(left: 20, right: 20, top: 0, bottom: 0),
              decoration: BoxDecoration(
                color: Colors.white,
                borderRadius: BorderRadius.only(
                  topLeft: Radius.circular(10),
                  topRight: Radius.circular(10),
                  bottomRight: Radius.circular(0),
                  bottomLeft: Radius.circular(0),
                ),
              ),
              child: TodoList(),
            ),
          ),
        ],
      ),
    );
  }
}
