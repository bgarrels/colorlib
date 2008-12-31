import colorLib.*;
import colorLib.webServices.*;

void setup(){
  size(400, 400);
  background(70);
  smooth();
  Colr colr = new Colr(this);
  ColrTheme[] colrThemes = colr.searchThemes("apple");
  for(int i=0; i<colrThemes.length; i++){
    pushMatrix();
    translate(10+(i%3)*130 , 10+floor(i/3)* 50);
    colrThemes[i].drawSwatches();
    popMatrix();
  }
}

