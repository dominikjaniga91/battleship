<html>
<head>
    <title>Battleship</title>
    <meta   charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Dominik Janiga" />
    <link rel="stylesheet" href="css/ship-style.css" type="text/css" />
    <link href="https://fonts.googleapis.com/css?family=Quicksand&display=swap" rel="stylesheet">
    <script src="javascript/script.js"></script>

</head>
<body>

<div class="container">
    <div id="header1" class="header"><p>YOUR SHIPS</p></div>
    <div id="header2" class="header"><p>BATTLEFIELD</p></div>
    <div id="headerGame" class="header" style="width:267px; margin-left: 32px;" ><p style="transform: translate(20%, 50%);" >GAME INFO</p></div>
    <div id="newTable" class="battleTable"></div>
    <div id="newTable1" class="battleTable"></div>
    <div id="gameInfo"><p style="padding-bottom: 15px;">To win the game you have to destroy 3 computer ships.
        Each ship has 3 fields. The left table represents your ships,
        fire on the right battelfield. Good luck! </p></div>
</div>
<div id="start" onclick="prepareFieldWithShips(); preparePlayerBattlefield(); receiveShipPosition(); "><p>START</p></div>
<div id="gameOver" ></div>
</body>

</html>
