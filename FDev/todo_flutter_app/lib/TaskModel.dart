class TaskModel {
  String lable;
  bool isDone = false;
//  TaskModel({this.lable, this.isDone});
  TaskModel({this.lable});

  setTaskDone() {
    isDone = !isDone;
  }
}
