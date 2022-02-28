import 'package:flutter/material.dart';

void main() {
  runApp(MyState());
}

class MyState extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return MaterialApp(
      home: Scaffold(
        body: Center(
          child: Image(
            image: AssetImage('images/mine_cart.png'),
          ),
        ),
        backgroundColor: Colors.blueGrey,
        appBar: AppBar(
          title: Text('Hello Coal Mine'),
          backgroundColor: Colors.blueAccent[800],
        ),
      ),
    );
  }
}
