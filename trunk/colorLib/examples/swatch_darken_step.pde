Swatch s;

void setup()
{
	size(200, 200);
	noStroke();

	s = new Swatch(this, color(255, 255, 0));
		
	fill(s.getColor());
	rect(0, 0, 100, 200);
	s.darken(30);
	fill(s.getColor());
	rect(100, 0, 100, 200);
}