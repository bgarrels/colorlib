Swatch s1;
Swatch s2;

void setup()
{
	size(200, 200);
	noStroke();

	s1 = new Swatch(this, color(0, 0, 200));
	s2 = new Swatch(this, color(0, 128, 0));
			
	fill(s1.getColor());
	rect(0, 0, width/2, height/2);
	s1.lighten();
	fill(s1.getColor());
	rect(width/2, 0, width/2, height/2);
	fill(s2.getColor());
	rect(0, height/2, width/2, height/2);
	s2.lighten(30);
	fill(s2.getColor());
	rect(width/2, height/2, width/2, height/2);
}