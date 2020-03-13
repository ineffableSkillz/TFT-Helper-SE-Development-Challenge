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

              /* Champions on Board Row */
              Row(
                children: <Widget>[
                  new OnBoardChampions(),
                ],),

              /* Champions on Bench Row */
              Row(
                children: <Widget>[
                  new OnBenchChampions(),
                ],),

              Row(
                children: <Widget>[

                  /* List of Types */
                  Expanded( //LHS of Screen
                      child: Column(
                      children: <Widget>[
                        new ListOfAttributes(),
                      ],
                    )
                  ),

                  /* RHS of Screen */
                  Expanded(
                    child: Column(

                    )
                  )
                ],

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
        width: MediaQuery
            .of(context)
            .size
            .width,
        padding: EdgeInsets.only(bottom: 0.1),
        child: Row(
          children: <Widget>[
            Expanded(child: _getChampionColumn(0)),
            Expanded(child: _getChampionColumn(1)),
            Expanded(child: _getChampionColumn(2)),
            Expanded(child: _getChampionColumn(3)),
            Expanded(child: _getChampionColumn(4)),
            Expanded(child: _getChampionColumn(5)),
            Expanded(child: _getChampionColumn(6)),
            Expanded(child: _getChampionColumn(7)),
          ],
        )
    );
  }

  Widget _getChampionColumn(int i) {
    return new Column(
      children: <Widget>[
        Image(image: AssetImage('assets/images/playerIcons/AzirSquare.png'))
      ],
    );
  }
}

class OnBenchChampions extends StatefulWidget {

  @override
  OnBenchChampionsState createState() => OnBenchChampionsState();
}
class OnBenchChampionsState extends State<OnBenchChampions> {

  Widget build(BuildContext context) {
    return new Container(
        width: MediaQuery
            .of(context)
            .size
            .width,
        child: Row(
          children: <Widget>[
            Expanded(child: _getChampionColumn(0)),
            Expanded(child: _getChampionColumn(1)),
            Expanded(child: _getChampionColumn(2)),
            Expanded(child: _getChampionColumn(3)),
            Expanded(child: _getChampionColumn(4)),
            Expanded(child: _getChampionColumn(5)),
            Expanded(child: _getChampionColumn(6)),
            Expanded(child: _getChampionColumn(7)),
          ],
        )
    );
  }

  Widget _getChampionColumn(int i) {
    return new Column(
      children: <Widget>[
        Image(image: AssetImage('assets/images/playerIcons/AhriSquare.png'))
      ],
    );

  }
}

class ListOfAttributes extends StatefulWidget {
  @override
  _ListOfAttributesState createState() => _ListOfAttributesState();

}
class _ListOfAttributesState extends State<ListOfAttributes> {

  List<String> championNames = ["Aatrox", "Ahri", "Akali", "Alistar", "Amumu", "Anivia", "Annie", "Aphelios", "Ashe"];

  @override
  Widget build(BuildContext context) {
    return new Container(
        width: MediaQuery.of(context).size.width/2,
        height: MediaQuery.of(context).size.height * 0.62,
        child: ListView.builder(
          itemCount: championNames.length,
          physics: BouncingScrollPhysics(),
          itemBuilder: (context, pos) {

            return Card(
                child: InkWell(

                  splashColor: Colors.black,
                  onTap: () {

                  },
                  child: Padding(
                      padding: const EdgeInsets.all(5.0),
                      child: ListTile(
                        leading: Image(image: AssetImage(_getChampionImagePath(championNames[pos]))),
                        title: Text(championNames[pos], style: TextStyle(fontSize: 22.0),),
                      )
                  ),
                )
            );
          },
        )
    );
  }

  String  _getChampionImagePath(String champName) {
    return 'assets/images/playerIcons/' + champName + 'Square.png';

  }
}
