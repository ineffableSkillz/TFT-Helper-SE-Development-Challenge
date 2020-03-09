import 'dart:io';

import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: new verticalScrollViewWidget(),
    );
  }
}


// ignore: camel_case_types
class verticalScrollViewWidget extends StatefulWidget {
  @override
  _verticalScrollViewWidgetState createState() => _verticalScrollViewWidgetState();

}

// ignore: camel_case_types
class _verticalScrollViewWidgetState extends State<verticalScrollViewWidget> {


  List<String> championNames = ["a", "b", "c", "d", "e", "f", "g", "h", "a", "b", "c", "d", "e", "f", "g", "h"];

  String stringOfChampNames;

  @override
  Widget build(BuildContext context) {

    getListOfChampions(context);

    return new Scaffold(
     body: new ListView.builder(
         itemBuilder: (context, pos) {
           return Card(
             child: Padding(
               padding: const EdgeInsets.all(16.0),
               child: Text(championNames[pos], style: TextStyle(fontSize: 22.0),)
             ),
           );
         },
       itemCount: championNames.length,
       physics: BouncingScrollPhysics(),
     ),
    );
  }


  void getListOfChampions(BuildContext context) {

    File f = new File('assets/textFiles/listOfChampions.txt');
    //print(f.readAsLinesSync());
    

  }

}
