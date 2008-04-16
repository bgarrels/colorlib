Swatch s1;
Swatch s2;

void setup()
{
	size(200, 200);
	noStroke();
	
	s1 = new Swatch(this, color(123, 2, 48));
	s2 = new Swatch(this, color(3, 78, 255));
	
	println("Color Difference between s1 and s2: " + s1.colorDiff(s2));
}