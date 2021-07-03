(function () {

  var colorA=[], colorB=[];
  var rows, cols;
  var nextColor = 0;

  const initBoard = (numRows, numCols) => {
    for (let i=0; i<numRows; i+=1) {
      colorA[i] = 0;
      colorB[i] = 0;
    }
    rows = numRows;
    cols = numCols;
  };
  
  const showBoard = () => {
    var cell;
    $('.board').empty();
    for (let i=0; i<rows; i+=1) {
      for (let j=0; j<cols; j+=1) {
        cell = $('<div>').addClass('field');
        if ((colorA[i]>>j) & 1) {
          $('<div>').addClass('piece').addClass('color-a').appendTo(cell);
        }
        if ((colorB[i]>>j) & 1) {
          $('<div>').addClass('piece').addClass('color-b').appendTo(cell);
        }
        cell.appendTo('.board');
      }
    }
  }
  
  const dropPiece = (col, type) => {
    for (let i=rows-1; i>=0; i-=1) {
      if (((colorA[i]|colorB[i])&(1<<col))==0) {
        if (type==0) {
          colorA[i] |= (1<<col); return true;
        } else {
          colorB[i] |= (1<<col); return true;
        }
      }
    }
    return false;
  }
  
  window.onload = () => {
    initBoard(6, 7);

    dropPiece(3, 1);
    dropPiece(4, 0);
    dropPiece(4, 1);

    showBoard(6, 7);       
  }

})();
