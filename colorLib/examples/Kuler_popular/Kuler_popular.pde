import colorLib.*;
import colorLib.webServices.*;

void setup(){
  size(400, 500);
  background(255);
  smooth();
  fill(0);
  textFont(loadFont("myFont.vlw"));
  Kuler kuler = new Kuler(this);;
  KulerTheme[] kulerThemes = kuler.getPopular();
  for(int i=0; i<kulerThemes.length; i++){
    pushMatrix();
    translate(10+(i%4)*85 , 10+floor(i/4)* 85);
    kulerThemes[i].drawWheel();
    fill(kulerThemes[i].getAverage());
    text(int( kulerThemes[i].getThemeRating()), 40, 65);
    popMatrix();
  }
}
