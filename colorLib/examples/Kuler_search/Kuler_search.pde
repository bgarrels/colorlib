import colorLib.*;
import colorLib.webServices.*;

void setup(){
  size(400, 400);
  background(255);
   Kuler kuler = new Kuler(this);
  KulerTheme[] kulerThemes = kuler.search("tag", "germany");
  for(int i=0; i<kulerThemes.length; i++){
    pushMatrix();
    translate(10+(i%3)*130 , 10+floor(i/3)* 70);
    kulerThemes[i].drawSwatches();
    popMatrix();
  }
}
