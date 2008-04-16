Palette p;
PImage  img;

void setup()
{
	size(200, 200);
	noStroke();

	img = loadImage("test32.gif");
		
	p = new Palette(this, img);
		
	p.drawSwatches();
}