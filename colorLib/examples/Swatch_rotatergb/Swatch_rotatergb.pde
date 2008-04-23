Swatch s;
float  angle = 120;

void setup()
{
	size(200, 200);
	noStroke();

	s = new Swatch(this, color(2, 128, 200));

	fill(s.getColor());
	rect(0, 0, 100, 200);
		
	s.rotateRGB(angle);
		
	fill(s.getColor());
	rect(100, 0, 100, 200);
}