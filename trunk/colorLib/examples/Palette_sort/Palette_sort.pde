import colorLib.*;

Palette p;

void setup(){
  size(200,300);
  generate();
}

void draw(){
}

void keyPressed(){
  generate();
}

void generate(){
  background (70);
  p = new Palette (this);
  //fill the palette with 20 random colors
  for (int i=0; i<20; i++){
    p.addColor(color(random(255), random(255), random(255)));
  }
  translate(40, 20);
  p.drawSwatches();
  p.sortByHue();
  translate(0, 70);
  p.drawSwatches();
  p.sortBySaturation();
  translate(0, 70);
  p.drawSwatches();
  p.sortByLuminance();
  translate(0, 70);
  p.drawSwatches();
}
