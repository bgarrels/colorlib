import colorLib.*;

Palette p;

void setup(){
  size(200,300);
  p = new Palette (this);
  generate();

}

void draw(){
}

void keyPressed(){
  generate();
}

void generate(){
  background (70);
  translate(10,10);
  color cl = color(random(255),random(255),random(255));
  p.makeComplement(cl);
  p.drawSwatches();
  translate(0, 50);
  p.makeComplementary(cl);
  p.drawSwatches();
  translate(0, 50);
  p.makeSplittedComplementary(cl);
  p.drawSwatches();
  translate(0, 70);
  p.makeTriad(cl);
  p.drawSwatches();
  translate(0, 70);
  p.makeTetrad(cl);
  p.drawSwatches();
}
