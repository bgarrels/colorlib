Swatch s;

void setup()
{
	size(200, 200);
	noStroke();

	s = new Swatch(this, color(0, 0, 200));
		
	fill(s.getColor());
	rect(0, 0, 100, 200);
	s.lighten(50);
	fill(s.getColor());
	rect(100, 0, 100, 200);
}