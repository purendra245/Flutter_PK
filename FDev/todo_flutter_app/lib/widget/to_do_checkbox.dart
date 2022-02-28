import 'package:flutter/material.dart';

class ToDoCheckBox extends StatelessWidget {
  final bool isCheckBox;
  final Function onCheck;
  ToDoCheckBox({this.isCheckBox, this.onCheck});
  @override
  Widget build(BuildContext context) {
    return Checkbox(
      value: isCheckBox,
      onChanged: onCheck,
    );
  }
}
