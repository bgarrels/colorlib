Palette p;

void setup()
{
	size(200, 200);
	noStroke();
		
	p = new Palette(this, "Pumpkin.cs");
		
	p.drawSwatches();
}