import 'dart:io';

import 'package:flutter/cupertino.dart';
import 'package:flutter/material.dart';
import 'package:helper_application/VerticalScroll.dart';

void main() => runApp(MyApp());


class MyApp extends StatelessWidget {
  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      home: Container(
          child: Column(
            children: <Widget>[
              Row( //Board Row
                children: <Widget>[
                  new OnBoardChampions()
                ],
              ),
              Row( //Bench Row

              ),
              Row(

              )
            ],
          )
      ),
    );
  }
}

class OnBoardChampions extends StatefulWidget {

  @override
  OnBoardChampionsState createState() => OnBoardChampionsState();
}
class OnBoardChampionsState extends State<OnBoardChampions> {
  @override
  Widget build(BuildContext context) {
    return new Container(
      child: Row(
        children: <Widget>[
          getChampionColumn(0),
          getChampionColumn(1),
          getChampionColumn(2),
          getChampionColumn(3),
          getChampionColumn(4),
          getChampionColumn(5),
          //getChampionColumn(6),
          //getChampionColumn(7),

        ],
      )
    );

  }

  Widget getChampionColumn(int i) {
    return new Column(
      children: <Widget>[
        Image(image: AssetImage('assets/images/playerIcons/AhriSquare.png'))
      ],
    );
  }

}

