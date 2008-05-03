import colorLib.*;

Palette p;

void setup(){
  size(200,300);
  textFont(loadFont("myFont.vlw"));
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
  for (int i=0; i<5; i++){
    p.addColor(color(random(255), random(255), random(255)));
  }
 
  translate(40, 20);

  p.drawSwatches();
  translate(0, 90);
  fill(p.getDarkest());
  text("DARKEST", 0,0);
  translate(0, 50);
  fill(p.getLightest());
  text("LIGHTEST", 0,0);
  translate(0, 50);
  fill(p.getAverage());
  text("AVERAGE", 0,0);
}
