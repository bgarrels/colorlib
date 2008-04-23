import colorLib.*;
import colorLib.webServices.*;

void setup(){
  size(400, 500);
  background(255);
  smooth();
  fill(90);
  textFont(loadFont("myFont.vlw"));
  Kuler kuler = new Kuler(this);
  KulerTheme[] kulerThemes = kuler.getRecent();
  for(int i=0; i<kulerThemes.length; i++){
    pushMatrix();
    translate(10+(i%3)*130 , 10+floor(i/3)* 70);
    kulerThemes[i].drawSwatches();
    text( kulerThemes[i].getThemeTitle(), 5, 53);
    popMatrix();
  }
}
