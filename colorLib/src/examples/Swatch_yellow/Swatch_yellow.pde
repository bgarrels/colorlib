import colorLib.*;

Swatch s;

void setup()
{
	size(200, 200);
	noStroke();

	s = new Swatch(this, color(2, 128, 200));

	fill(s.getColor());
	rect(0, 0, 100, 200);
		
	colorMode(RGB, 1.0);
	fill(1 - s.yellow());
	rect(100, 0, 100, 200);
}
