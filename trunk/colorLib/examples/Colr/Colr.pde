import colorLib.*;
import colorLib.webServices.*;

void setup(){
  size(500, 500);
  background(255);
  smooth();
  noFill();
  Colr colr = new Colr(this);
  ColrTheme colrTheme = colr.searchColors("spring");
  int[] clrs = colrTheme.getColors();
  for(int i = 0; i < clrs.length; i++){
    fill(clrs[i]);
    int rad = random(10, 30);
    ellipse(random(width), random(height), rad, rad);
  }
}

