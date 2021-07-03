var colorA = [], colorB = [];
var rows, cols;
var nextColor = 0;

// Spielfeldgrösse initilisieren
const initBoard = (numRows, numCols) => {
  for (let i = 0; i < numRows; i += 1) {
    colorA[i] = 0;
    colorB[i] = 0;
  }
  rows = numRows;
  cols = numCols;
};

// Spielfeld anzeigen
const showBoard = () => {
  var cell;
  $('.board').empty();
  for (let i = 0; i < rows; i += 1) {
    for (let j = 0; j < cols; j += 1) {
      cell = $('<div>').addClass('field ' + j);
      if ((colorA[i] >> j) & 1) {
        $('<div>').addClass('piece').addClass('color-a').appendTo(cell);
      }
      if ((colorB[i] >> j) & 1) {
        $('<div>').addClass('piece').addClass('color-b').appendTo(cell);
      }
      cell.appendTo('.board');
    }
  }
}

// Farbe im Feld setzen und überprüfen ob es leer ist
const dropPiece = (col, type) => {
  for (let i = rows - 1; i >= 0; i -= 1) {
    if (((colorA[i] | colorB[i]) & (1 << col)) == 0) {
      if (type == 0) {
        colorA[i] |= (1 << col); return true;
      } else {
        colorB[i] |= (1 << col); return true;
      }
    }
  }
  return false;
}

window.onload = () => {
  initBoard(6, 7);

  startingColor = firstPlay()
  alert(startingColor + " macht den ersten Spielzug")

  showBoard(6, 7);
}

// Onclicker Evenhandler bei Klick aufs Spielfeld
const setColor = () => {
  colclicked = event.target.classList[1]
  dropPiece(colclicked, nextColor)
  if (nextColor == 0) {
    nextColor = 1;
  }
  else {
    nextColor = 0;
  }
  showBoard(rows, cols);
}


// Randomisiert ersten Spielzug
const firstPlay = () => {
  starter = Math.floor(Math.random() * 2);
  nextColor = starter
  if (starter == 0) {
    return "Blau"
  }

  if (starter == 1) {
    return "Rot"
  }
}

