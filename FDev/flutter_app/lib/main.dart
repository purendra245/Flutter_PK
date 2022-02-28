import 'package:flutter/material.dart';
import 'package:provider/provider.dart';

void main() {
  runApp(MyApp());
}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter Demo',
      home: MyHomePage(title: 'Flutter Demo Home Page'),
    );
  }
}

class MyHomePage extends StatefulWidget {
  MyHomePage({Key key, this.title}) : super(key: key);
  final String title;
  @override
  _MyHomePageState createState() => _MyHomePageState();
}

class _MyHomePageState extends State<MyHomePage> {
  @override
  Widget build(BuildContext context) {
    return ChangeNotifierProvider<Data>(
      builder: (context) => Data(),
      child: Scaffold(
        body: Padding(
          padding: EdgeInsets.only(left: 10, top: 50, right: 10, bottom: 10),
          child: Column(
            children: <Widget>[
              MyTextWidget(),
              MyTextFieldWidget(),
            ],
          ),
        ),
      ),
    );
  }
}

class MyTextWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Text(Provider.of<Data>(context).inputText);
  }
}

class MyTextFieldWidget extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return TextField(
      onChanged: (newValue) {
        Provider.of<Data>(context).setData(newValue);
      },
    );
  }
}

class Data extends ChangeNotifier {
  String inputText = " ";

  void setData(String inputD) {
    inputText = inputD;
    notifyListeners();
  }
}
