

const SHIP_URL = 'battleship';
function myFunction(myId) {

    const numberId = document.getElementById(myId);
    const gameInfo  = document.getElementById("gameInfo");
    const gameOver = document.getElementById("gameOver");

    numberId.innerHTML = "<p>X</p>";
    const payload = {
        "name": "shot",
        "id": myId
                    };

    fetch(SHIP_URL,
        {
            method: "POST",
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(payload)
        })
        .then((response) => response.json())
        .then( function(data) {
          
            const today = new Date();
            const time = today.getHours() + ":" + today.getMinutes() + ":" + today.getSeconds();
            const computerId = data["map"]["message"]["map"]["computerShot"].concat("_");
            const computerShotResult = data["map"]["message"]["map"]["computerShotResult"];
            const playerShotResult = data["map"]["message"]["map"]["playerShotResult"];

            gameInfo.firstElementChild.insertAdjacentHTML('afterend', `<p>${time}</p>
                                                                          <p> player shot result  <b>${playerShotResult}</b> </p> 
                                                                          <p> computer shot result  <b>${computerShotResult}</b> </p><br>`);


            document.getElementById(computerId).innerHTML =  "<p>X</p>";

            if(playerShotResult === "hit" || playerShotResult === "sunken") {
                numberId.style.backgroundColor = "#0080ff";
            }
            if(playerShotResult === "win" || computerShotResult === "win"){

                if(playerShotResult === "win"){
                    gameOver.innerHTML =  `<p> <b>player ${playerShotResult}</b>`;
                }else if(playerShotResult === "win"){
                    gameOver.innerHTML =  `<p> <b>computer ${playerShotResult}</b>`;
                }


                document.getElementById("start").style.display = "block";


                gameOver.style.display = "block";


                gameInfo.innerHTML ="";
                gameInfo.style.display = "none";

                const battleTable  = document.getElementById("newTable");
                battleTable.innerHTML ="";
                battleTable.style.display = "none";

                const battleTable1  = document.getElementById("newTable1");
                battleTable1.innerHTML ="";
                battleTable1.style.display = "none";

                document.getElementById("header1").style.display = "none";
                document.getElementById("header2").style.display = "none";
                document.getElementById("headerGame").style.display = "none";

            }
        }).catch(function (error) {
            alert(error);
        });

    gameInfo.scrollTop;

}

function prepareFieldWithShips(){

    let row = 8, html = "";

    for(let j =1; j <= row; j++){
        for (let i=1; i<= row; i++) {

            html += `<div class="fields" style="pointer-events: none;"  id="${j.toString().concat(i).concat("_")}">`+ "</div>";

        }
    }

    let id = document.getElementById("newTable");
    id.style.display = "block";
    id.innerHTML = html;

    document.getElementById("start").style.display = "none";
    document.getElementById("gameOver").style.display = "none";
    const gameInfo = document.getElementById("gameInfo");
    gameInfo.style.display = "block";
    // gameInfo.innerHTML=""
    document.getElementById("header1").style.display = "block";
    document.getElementById("header2").style.display = "block";
    document.getElementById("headerGame").style.display = "block";

}

function preparePlayerBattlefield(){

    let row = 8, html = "";

    for(let j =1; j <= row; j++){
        for (let i=1; i<= row; i++) {

            html += `<div class="fields" id="${j.toString().concat(i)}" onclick="myFunction(this.id)" >` + "</div>";
        }
    }

    let id = document.getElementById("newTable1");
    id.style.display = "block";
    id.innerHTML = html;

}

function receiveShipPosition(){
    document.body.style.cursor='wait';
    const payload = { name: "prepare" };

    fetch(SHIP_URL,{
        method: "POST",
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'},
            body: JSON.stringify(payload)
    }).then((response) => response.json())
        .then( function(data) {

            for(let i = 0; i < data["map"]["id"]["myArrayList"].length; i++ ){

                let id = data["map"]["id"]["myArrayList"][i].concat("_");
                document.getElementById(id).style.backgroundColor = "#0080ff";
            }

        })
        .catch(function (error) {
            alert(error);
        });
    document.body.style.cursor='default';
}

