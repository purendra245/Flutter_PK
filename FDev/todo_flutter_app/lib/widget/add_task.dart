import 'package:flutter/material.dart';

class AddTaskScreen extends StatelessWidget {
  Function addTask;
  String taskName;
  AddTaskScreen(this.addTask);

  @override
  Widget build(BuildContext context) {
    return Container(
      color: Color(0xff757575),
      child: Container(
        decoration: BoxDecoration(
          color: Colors.white,
          borderRadius: BorderRadius.only(
            topLeft: Radius.circular(10),
            bottomLeft: Radius.circular(0),
            bottomRight: Radius.circular(0),
            topRight: Radius.circular(10),
          ),
        ),
        child: Padding(
          padding: EdgeInsets.only(left: 30, right: 30, top: 20, bottom: 20),
          child: Column(
            crossAxisAlignment: CrossAxisAlignment.stretch,
            children: <Widget>[
              Center(
                child: Text(
                  'Add Task',
                  style: TextStyle(
                      color: Colors.blue[300],
                      fontSize: 30,
                      fontWeight: FontWeight.w500),
                ),
              ),
              Padding(
                padding:
                    EdgeInsets.only(left: 0, top: 10, right: 0, bottom: 20),
                child: TextField(
                  onChanged: (newValue) {
                    taskName = newValue;
                  },
                  autofocus: true,
                  textAlign: TextAlign.center,
                  decoration: InputDecoration(
                    hintText: 'Enter your task',
                    focusColor: Colors.blue,
                  ),
                ),
              ),
              FlatButton(
                onPressed: () {
                  addTask(taskName);
                  Navigator.pop(context);
                },
                color: Colors.blue,
                textColor: Colors.white,
                child: Text(
                  'Add',
                  style: TextStyle(
                    fontSize: 20.0,
                  ),
                ),
                padding: EdgeInsets.all(10),
              )
            ],
          ),
        ),
      ),
    );
  }
}
