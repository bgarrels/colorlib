Swatch s1;
Color  c;

void setup()
{
	size(200, 200);
	noStroke();
	
	s1 = new Swatch(this, color(123, 2, 48));
	c  = color(35, 78, 199);
	
	println("Color Difference between s1 and c: " + s1.colorDiff(c));
}