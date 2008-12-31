import colorLib.*;
import colorLib.webServices.*;

void setup(){
  size(400, 500);
  Kuler kuler = new Kuler(this);
  kuler.setMaxItems(100);
  KulerTheme[] kulerThemes1 = kuler.getRecent(); //get the 0 - 100 
  kuler.setStartIndex(1);
  KulerTheme[] kulerThemes2 = kuler.getRecent(); //get the result from 100 - 200
}
