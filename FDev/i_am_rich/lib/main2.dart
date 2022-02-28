import 'package:flutter/material.dart';

void main() {
  runApp(MaterialApp(
    home: Scaffold(
      body: Center(
        child: Image(
//          image: NetworkImage(
//              "https://img.rawpixel.com/s3fs-private/rawpixel_images/website_content/a016-jeremyb-2.jpg?w=1000&dpr=1&fit=default&crop=default&q=65&vib=3&con=3&usm=15&bg=F4F4F3&ixlib=js-2.2.1&s=efe0ec1f73ba52205ecc54b5b45fa192"
//          ),
          image: AssetImage("images/diamond.png"),
        ),
      ),
      appBar: AppBar(
        title: Text(
          "I am rich bro",
        ),
        backgroundColor: Colors.blueGrey[900],
      ),
      backgroundColor: Colors.amber[900],
    ),
    debugShowCheckedModeBanner: false,
  ));
}
//  Text('Hello Purendra'),
