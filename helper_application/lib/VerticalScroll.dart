import 'dart:io';

import 'package:flutter/material.dart';


List<String> championNames = ["Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie", "Aphelios", "Ashe"];

void main() {
  runApp(MyApp());
}

String  _getChampionImagePath(String champName) {
  return 'assets/images/playerIcons/' + champName + 'Square.png';

}

class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: new Scaffold(
        body: new verticalScrollViewWidget(),
    ),
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
  @override
  Widget build(BuildContext context) {
    return new ListView.builder(
        itemBuilder: (context, pos) {
          return Card(
            child: InkWell(

              splashColor: Colors.black,
              onTap: () {

              },
              child: Padding(
                  padding: const EdgeInsets.all(16.0),
                  child: ListTile(
                    leading: Image(image: AssetImage(_getChampionImagePath(championNames[pos]))),
                    title: Text(championNames[pos], style: TextStyle(fontSize: 22.0),),
                  )
              ),
            )
          );
        },
        itemCount: championNames.length,
        physics: BouncingScrollPhysics(),
    );
  }
}
