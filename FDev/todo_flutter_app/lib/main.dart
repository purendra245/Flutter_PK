import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:todo_flutter_app/widget/task_screen.dart';

import 'TaskNotif.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.

  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<TaskNotif>(
      builder: (context) => TaskNotif(),
      child: MaterialApp(
        title: 'Flutter Demo',
        home: TaskScreen(),
      ),
    );
  }

//  @override
//  State<StatefulWidget> createState() {}
}
