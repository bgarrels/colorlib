Palette p1;
Palette p2;
Palette p3;
PImage  img;
	
void setup()
{
	size(400, 400);
	noStroke();
	
	background(255);
	
	translate(20, 20);
	
	p1 = new Palette(this, "test.act");
	p1.drawSwatches();

	translate(0, 80);
		
	p2 = new Palette(this, "Pumpkin.cs");
	p2.drawSwatches();

	translate(0, 80);
		
	img = loadImage("test32.gif");
	p3 = new Palette(this, img);
	p3.drawSwatches();
}