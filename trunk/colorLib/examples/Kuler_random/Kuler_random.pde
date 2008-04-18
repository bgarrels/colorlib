import colorLib.*;
import colorLib.webServices.*;

void setup(){
  size(400, 100);
  background(255);
  strokeWeight(2);
  Kuler kuler = new Kuler(this);
  KulerTheme[] kulerThemes = kuler.getRandom();
  int cnt=0;
  for(int i=0; i<kulerThemes.length; i++){
    int[]colors = kulerThemes[i].getColors();
    for(int j=0; j<colors.length; j++){
      stroke(colors[j]);
      line(cnt, 0, cnt, height);
      cnt+=4;
    }
  }
}

