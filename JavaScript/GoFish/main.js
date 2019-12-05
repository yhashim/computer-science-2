const NUM_FACES = 13;
const PLAY = 1;
const NUM_CARDS = 5;
const WIN_PTS = 10;
var SUITS = ["hearts", "clubs", "diamonds", "spades"];

var traits = []
traits[0] = "IQ1000GOD";
// knows everyone's cards and who has what
// always gets what they want
traits[1] = "BULLY";
// targets the player only
traits[2] = "veggie";
// only asks for cards they don't have themselves
// asks whoever doesn't have the card they want for it


var cards = [];
var CPUs = [];
var numPlayers;

var canvas, ctx;
var backgroundImg;

var gameDone = false;
var turnStart;

var player;
var targetElem;

var status;

window.onload = function init() {
    loadAllImgs();
    canvas = document.getElementById("myCanvas");
    ctx = canvas.getContext("2d");
    ctx.fillStyle = '#870000';
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    turnStart = false;
}

function begin(num) {
    numPlayers = num - 1;
    document.getElementById("title").style.display = 'none';
    document.getElementById("b1").style.display = 'none';
    document.getElementById("b2").style.display = 'none';
    document.getElementById("b3").style.display = 'none';
    document.getElementById("num").style.display = 'block';
    if (numPlayers == 1) {
        document.getElementById("target1").style.display = 'block';
        targetElem = 1;
    } else if (numPlayers == 2) {
        document.getElementById("target2").style.display = 'block';
        targetElem = 2;
    } else {
        document.getElementById("target3").style.display = 'block';
        targetElem = 3;
    }
    document.getElementById("text1").style.display = 'block';
    document.getElementById("text2").style.display = 'block';
    document.getElementById("ask").style.display = 'block';
    status = "Game initialized";
    player = new Player();
    for (var i = 0; i < NUM_CARDS; i++) {
        var suit = SUITS[Math.floor(Math.random() * SUITS.length)];
        var randomCard = new Card(Math.floor(Math.random() * 13) + 1, suit);
        player.recieveCard(randomCard);
    }
    // IS THE ISSUE AUTOMATIC BASE CASE SO ITS NOT CHECKING? FIX BOOLEANS MAYBE?
    checkPairs(player.hand.hand, player);
    for (var i = 0; i < numPlayers; i++) {
        var trait = traits[Math.floor(Math.random() * traits.length)];
        CPUs[i] = new CPU(i, trait);
        for (var j = 0; j < NUM_CARDS; j++) {
            var suit = SUITS[Math.floor(Math.random() * SUITS.length)];
            var randomCard = new Card(Math.floor(Math.random() * 13) + 1, suit);
            CPUs[i].recieveCard(randomCard);
        }
        checkPairs(CPUs[i].hand.hand, CPUs[i]);
    }
    draw();
}

class Player {
    constructor() {
        this.name = "You";
        this.hand = new Hand();
        this.points = 0;
        this.target;
        this.cardNumWanted;
    }
    getHand() {
        return this.hand;
    }
    getPoints() {
        return this.points;
    }
    setCardAndTarg() {
        this.target = document.getElementById("target" + targetElem).value;
        this.cardNumWanted = document.getElementById("num").value;
        play(this);
    }
    getTarget() {
        return this.target;
    }
    getCardWanted() {
        return this.cardNumWanted;
    }
    recieveCard(card) {
        this.hand.addCard(card);
    }
    hasPairs() {
        console.log(this.hand.length);
        if (this.hand.length == 1) {
            this.hand.hasPairs = false;
            console.log(this.name + " has no pairs.");
            return false;
        } else if (this.hand.length == 2) {
            if (this.hand.hand[0].number == this.hand.hand[1].number) {
                this.hand.hasPairs = true;
                console.log(this.name + " has a pair.");
                return true;
            }
        } else {
            for (var i = 0; i < this.hand.length - 1; i++) {
                for (var j = (i + 1); j < this.hand.length; j++) {
                    if (this.hand.hand[i].number == this.hand.hand[j].number) {
                        this.hand.hasPairs = true;
                        console.log(this.name + " has a pair.");
                        return true;
                    }
                }
            }
        }
        this.hand.hasPairs = false;
        console.log(this.name + " has no pairs.");
        return false;
    }
    cardInHand(card) {
        return this.hand.cardPresent(card);
    }
    giveCard(card) {
        return this.hand.removeCard(card);
    }
    isHandEmpty() {
        return this.hand.length == 0;
    }
    refillHand() {
        if (this.hand.isHandEmpty()) {
            for (var i = 0; i < NUM_CARDS; i++) {
                var suit = SUITS[Math.floor(Math.random() * SUITS.length)];
                var randomCard = new Card(Math.floor(Math.random() * 13) + 1, suit);
                this.recieveCard(randomCard);
            }
        }
    }
}

function setCardAndTarg() {
    player.setCardAndTarg();
}

class CPU {
    constructor(number, trait) {
        this.number = number;
        this.name = "CPU" + number;
        this.trait = trait;
        this.hand = new Hand();
        this.points = 0;
        this.target;
        this.cardNumWanted;
    }
    getHand() {
        return this.hand;
    }
    getPoints() {
        return this.points;
    }
    setTarget(card) {
        if (this.trait == "veggie") {
            if (!player.cardInHand(card)) {
                this.target = -1;
            } else {
                for (var i = 0; i < CPUs.length; i++) {
                    if (i != this.number) {
                        if (!CPUs[i].cardInHand(card)) {
                            this.target = i;
                        }
                    }
                }
            }
        } else if (this.trait == "BULLY") {
            this.target = -1; // player
        } else if (this.trait == "IQ1000GOD") {
            if (player.cardInHand(card)) {
                this.target = -1;
            } else {
                for (var i = 0; i < CPUs.length; i++) {
                    if (i != this.number) {
                        if (CPUs[i].cardInHand(card)) {
                            this.target = i;
                        }
                    }
                }
            }
        }
        return this.target;
    }
    getCardWanted() {
        this.cardNumWanted = this.hand.hand[Math.floor(Math.random() * this.hand.length)].number;
        // if (this.trait == VEGETABLE) {
        //     this.cardNumWanted = Math.floor((Math.random()*13)+1);
        //     if (this.cardInHand(card)){
        //         // FIX UP THISSSSSSS  
        //     }
        // } else if (this.trait == BULLY) {
        //     this.cardNumWanted = this.hand[Math.floor(Math.random() * this.hand.length)].number;
        // } else if (this.trait == IQ1000GOD) {

        // }
        return this.cardNumWanted;
    }
    recieveCard(card) {
        this.hand.addCard(card);
    }
    hasPairs() {
        console.log(this.hand.length);
        if (this.hand.length == 1) {
            this.hand.hasPairs = false;
            console.log(this.name + " has no pairs.");
            return false;
        } else if (this.hand.length == 2) {
            if (this.hand.hand[0].number == this.hand.hand[1].number) {
                this.hand.hasPairs = true;
                console.log(this.name + " has a pair.");
                return true;
            }
        } else {
            for (var i = 0; i < this.hand.length - 1; i++) {
                for (var j = (i + 1); j < this.hand.length; j++) {
                    if (this.hand.hand[i].number == this.hand.hand[j].number) {
                        this.hand.hasPairs = true;
                        console.log(this.name + " has a pair.");
                        return true;
                    }
                }
            }
        }
        this.hand.hasPairs = false;
        console.log(this.name + " has no pairs.");
        return false;
    }
    cardInHand(card) {
        return this.hand.cardPresent(card);
    }
    giveCard(card) {
        return this.hand.removeCard(card);
    }
    isHandEmpty() {
        return this.hand.length == 0;
    }
    refillHand() {
        if (this.hand.isHandEmpty()) {
            for (var i = 0; i < NUM_CARDS; i++) {
                var suit = SUITS[Math.floor(Math.random() * SUITS.length)];
                var randomCard = new Card(Math.floor(Math.random() * 13) + 1, suit);
                this.recieveCard(randomCard);
            }
        }
    }
}

class Card {
    constructor(number, suit) {
        this.number = number;
        this.suit = suit;
    }
    get name() {
        return "" + this.suit + this.number;
    }
    getCardName() {
        return this.name;
    }
    getCardNumber() {
        return this.number;
    }
    getSuit() {
        return this.suit;
    }
}

class Hand {
    constructor() {
        this.hand = [];
        this.length = 0;
        this.hasPairs = true;
    }
    cardPresent(card) {
        for (var i = 0; i < this.length; i++) {
            if (this.hand[i].number == card) {
                return true;
            }
        }
        return false;
    }
    addCard(card) {
        this.hand[this.length] = card;
        this.length++;
    }
    removeCard(card) {
        var index = 0;
        var suit;
        for (var i = 0; i < this.length; i++) {
            if (this.hand[i].number == card) {
                index = i;
                suit = this.hand[i].getSuit();
                break;
            }
        }
        var modHand = new Hand();
        for (var i = 0; i < index; i++) {
            modHand.hand[i] = this.hand[i];
            modHand.length++;
        }
        for (var i = index + 1; i < this.length; i++) {
            modHand.hand[i - 1] = this.hand[i];
            modHand.length++;
        }
        this.hand = modHand.hand;
        this.length = modHand.length;
        return suit;
    }
}

function checkPairs(hand, person) {
    if (person.hasPairs() == false) {
        return false;
    } else {
        var escape = false;
        for (var i = 0; i < hand.length; i++) {
            if (escape){
                break;
            }
            for (var j = (i + 1); j < hand.length; j++) {
                if (hand[i] != null && hand[j] != null && hand[i].number == hand[j].number) {
                    var newHand = new Hand();
                    hand[i] = null;
                    hand[j] = null;
                    var l = 0;
                    for (var k = 0; k < hand.length; k++) {
                        if (hand[k] != null) {
                            newHand.hand[l] = hand[k];
                            newHand.length++;
                            l++;
                        }
                    }
                    person.hand = newHand;
                    person.points++;
                    //alert(person.name + " had a pair removed."); // DO SOMETHING SO THIS ALERT DOESNT HAPPEN BEFORE THE GAME LOADS 
                    console.log('YEAH!!!!!');
                    escape = true;
                }
                if (escape){
                    break;
                }
            } 
        }
        checkPairs(person.hand.hand, person);
    }
}

function play(player) {
    var winner = checkGame();
    while (!gameDone) {
        document.getElementById("ask").style.display = 'block';
        go(player);
        // sleep(500);
        checkGame();
        // sleep(500);
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        draw();
        for (var i = 0; i < CPUs.length; i++) {
            document.getElementById("ask").style.display = 'none';
            //sleep(500);
            go(CPUs[i]);
            // sleep(500);
            checkGame();
            // sleep(500);
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            draw();
        }
        //sleep(500);
    }
    status = winner + " won the game!";
}

function sleep(milliseconds) {
    // stack overflow credited
    var start = new Date().getTime();
    for (var i = 0; i < 1e7; i++) {
        if ((new Date().getTime() - start) > milliseconds) {
            break;
        }
    }
}

function checkGame() {
    if (player.points >= 10) {
        gameDone = true;
        return player;
    }
    for (var i = 0; i < CPUs.length; i++) {
        if (CPUs[i].points >= 10) {
            gameDone = true;
            return CPU[i];
        }
    }
    return;
}

function go(person) {
    var numWant, target;
    if (person.name == "You") {
        numWant = person.cardNumWanted;
        target = CPUs[person.target - 1];
    } else {
        numWant = person.getCardWanted();
        target = person.setTarget(numWant);
        if (target == -1) {
            target = player;
        } else {
            target = CPUs[person.target - 1]; // IS THIS RIGHT?
        }
    }
    console.log(target);
    if (target.cardInHand(numWant)) {
        var num = numWant;
        if (numWant == 1) {
            num = "A";
        } else if (numWant == 11) {
            num = "J";
        } else if (numWant == 12) {
            num = "Q";
        } else if (numWant == 13) {
            num = "K";
        }
        if (person.name == "You") {
            alert("You recieved a " + num + " from CPU" + person.target);
            status =  "You recieved a " + num + " from CPU" + person.target;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            draw();
        } else {
            var who = "you";
            if (person.target != -1){
                who = "CPU" + person.target;
            }
            alert(person.name + " recieved a " + num + " from " + who);
            status = person.name + " recieved a " + num + " from " + who;
            ctx.clearRect(0, 0, canvas.width, canvas.height);
            draw();
        }
        var suit = target.giveCard(numWant);
        if (num == "A") {
            num = 1;
        } else if (num == "J") {
            num = 11;
        } else if (num == "Q") {
            num = 12;
        } else if (num == "K") {
            num = 13;
        }
        var card = new Card(num, suit);
        person.recieveCard(card);
        turnStart = false;
        checkPairs(person.hand.hand, person);
        //sleep(500);
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        draw();
        //sleep(500);
        return;
    } else {
        var numSay1 = numWant;
        if (numWant == 1) {
            numSay1 = "A";
        } else if (numWant == 11) {
            numSay1 = "J";
        } else if (numWant == 12) {
            numSay1 = "Q";
        } else if (numWant == 13) {
            numSay1 = "K";
        }
        var num = Math.floor((Math.random() * 13) + 1);
        var numSay2 = num;
        if (num == 1) {
            numSay2 = "A";
        } else if (num == 11) {
            numSay2 = "J";
        } else if (num == 12) {
            numSay2 = "Q";
        } else if (num == 13) {
            numSay2 = "K";
        }
        // DONT LET IT WORK UNTIL THEY PICK A NUM AND PERSONNAME
        if (person.target == -1){
            alert("You did not have a " + numSay1 + ". " + person.name + " picked up a " + numSay2 + ".");
            status = "You did not have a " + numSay1 + ". " + person.name + " picked up a " + numSay2 + ".";
        } else {
            alert("CPU" + person.target + " did not have a " + numSay1 + ". " + person.name + " picked up a " + numSay2 + ".");
            status = "CPU" + person.target + " did not have a " + numSay1 + ". " + person.name + " picked up a " + numSay2 + ".";
        }
        var suit = SUITS[Math.floor(Math.random() * SUITS.length)];
        var card = new Card(Math.floor(Math.random() * 13) + 1, suit);
        person.recieveCard(card);
        turnStart = false;
        checkPairs(person.hand.hand, person);
        //sleep(500);
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        draw();
        //sleep(500);
        return;
    }
}

function loadAllImgs() {
    backgroundImg = new Image();
    backgroundImg.src = "images/bg2.png";

    var back = new Image();
    back.src = "images/cards/back.png";
    cards['back'] = back;

    for (var i = 2; i <= 10; i++) {
        var img = new Image();
        img.src = "images/cards/clubs" + i + ".png";
        cards['clubs' + i] = img;
    }
    img = new Image();
    img.src = "images/cards/clubs" + "J" + ".png";
    cards['clubs11'] = img;
    img = new Image();
    img.src = "images/cards/clubs" + "Q" + ".png";
    cards['clubs12'] = img;
    img = new Image();
    img.src = "images/cards/clubs" + "K" + ".png";
    cards['clubs13'] = img;

    for (var i = 2; i <= 10; i++) {
        var img = new Image();
        img.src = "images/cards/diamonds" + i + ".png";
        cards['diamonds' + i] = img;
    }
    img = new Image();
    img.src = "images/cards/diamonds" + "J" + ".png";
    cards['diamonds11'] = img;
    img = new Image();
    img.src = "images/cards/diamonds" + "Q" + ".png";
    cards['diamonds12'] = img;
    img = new Image();
    img.src = "images/cards/diamonds" + "K" + ".png";
    cards['diamonds13'] = img;

    for (var i = 2; i <= 10; i++) {
        var img = new Image();
        img.src = "images/cards/hearts" + i + ".png";
        cards['hearts' + i] = img;
    }
    var img = new Image();
    img.src = "images/cards/hearts" + "J" + ".png";
    cards['hearts11'] = img;
    img = new Image();
    img.src = "images/cards/hearts" + "Q" + ".png";
    cards['hearts12'] = img;
    img = new Image();
    img.src = "images/cards/hearts" + "K" + ".png";
    cards['hearts13'] = img;

    for (var i = 2; i <= 10; i++) {
        var img = new Image();
        img.src = "images/cards/spades" + i + ".png";
        cards['spades' + i] = img;
    }
    img = new Image();
    img.src = "images/cards/spades" + "J" + ".png";
    cards['spades11'] = img;
    img = new Image();
    img.src = "images/cards/spades" + "Q" + ".png";
    cards['spades12'] = img;
    img = new Image();
    img.src = "images/cards/spades" + "K" + ".png";
    cards['spades13'] = img;

    // ACES - lol forgot them
    img = new Image();
    img.src = "images/cards/clubs" + "A" + ".png";
    cards['clubs1'] = img;
    img = new Image();
    img.src = "images/cards/hearts" + "A" + ".png";
    cards['hearts1'] = img;
    img = new Image();
    img.src = "images/cards/diamonds" + "A" + ".png";
    cards['diamonds1'] = img;
    img = new Image();
    img.src = "images/cards/spades" + "A" + ".png";
    cards['spades1'] = img;
}

function draw() {
    ctx.fillStyle = '#870000';
    ctx.fillRect(0, 0, canvas.width, canvas.height);
    //ctx.clearRect(0, 0, canvas.width, canvas.height);
    // if game over
    // draw game over in center
    // return;
    // else
    // do drawy stuff
    // draw player at bottom of screen
    // draw cpus
    // draw them based on num cpus
    var i = 10;
    var all = player.hand.hand;
    for (var c of all) {
        if (c != null) {
            var name = c.name;
            var img = cards[c.name];
            ctx.drawImage(img, 540 + i, 500, img.width / 4, img.height / 4);
            i += 25;
        }
    }
    if (CPUs.length == 1) {
        all = CPUs[0].hand.hand;
        var x = 418;
        for (var c of all) {
            if (c != null) {
                var name = c.name;
                var img = cards[c.name];
                ctx.drawImage(img, x + i, 75, img.width / 4, img.height / 4);
                //ctx.drawImage(cards['back'], x + i, 75, cards['back'].width / 6, cards['back'].height / 6)
                i += 25;
            }
        }
        ctx.font = "18px Times New Roman";
        ctx.fillStyle = "white";
        ctx.fillText("Points: " + CPUs[0].points, 640, 50);
    } else if (CPUs.length == 2) {
        for (var j = 0; j < CPUs.length; j++) {
            all = CPUs[j].hand.hand;
            var x;
            if (j == 0) {
                x = 145;
            } else {
                x = 550;
            }
            for (var c of all) {
                if (c != null) {
                    var name = c.name;
                    var img = cards[c.name];
                    ctx.drawImage(img, x + i, 75, img.width / 4, img.height / 4);
                    //ctx.drawImage(cards['back'], x + i, 75, cards['back'].width / 6, cards['back'].height / 6)
                    i += 25;
                }
            }
            ctx.font = "18px Times New Roman";
            ctx.fillStyle = "white";
            if (j == 0) {
                ctx.fillText("Points: " + CPUs[j].points, 360, 50);
            } else {
                ctx.fillText("Points: " + CPUs[j].points, 900, 50);
            }
        }
    } else if (CPUs.length == 3) {
        for (var j = 0; j < CPUs.length; j++) {
            all = CPUs[j].hand.hand;
            var x;
            if (j == 0) {
                x = 20;
            } else if (j == 1) {
                x = 285;
            } else {
                x = 550;
            }
            for (var c of all) {
                if (c != null) {
                    var name = c.name;
                    var img = cards[c.name];
                    ctx.drawImage(img, x + i, 75, img.width / 4, img.height / 4);
                    // ctx.drawImage(cards['back'], x + i, 75, cards['back'].width / 6, cards['back'].height / 6)
                    i += 25;
                }
            }
            ctx.font = "18px Times New Roman";
            ctx.fillStyle = "white";
            if (j == 0) {
                ctx.fillText("Points: " + CPUs[j].points, 240, 50);
            } else if (j == 1) {
                ctx.fillText("Points: " + CPUs[j].points, 630, 50);
            } else {
                ctx.fillText("Points: " + CPUs[j].points, 1025, 50);
            }
        }
    }
    ctx.font = "18px Times New Roman";
    ctx.fillStyle = "white";
    ctx.fillText("Points: " + player.points, 780, 728);
    // var scope = true;
    // if (scope) {
    //     ctx.textAlign = "center";
    //     ctx.fillText(status, (canvas.width/2)-20, 385);
    // }
    ctx.fillText(status, (canvas.width / 2) - 57, 385);
    window.requestAnimationFrame(draw);
}