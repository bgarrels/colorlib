Swatch s1;
Swatch s2;
Color  c;

void setup() {
	size(200, 200);
	noStroke();
		
	s1 = new Swatch(this, color(123, 2, 48));
	s2 = new Swatch(this, color(3, 78, 255));
	c  = color(35, 78, 199);
		
	fill(s1.getColor());
	rect(0, 0, width/2, height);
	fill(s2.getColor());
	rect(width/2, 0, width/2, height/2);
	fill(c);
	rect(width/2, height/2, width/2, height/2);
		
	println("Brightness Difference between s1 and s2: " + s1.brightnessDiff(s2));
	println("Brightness Difference between s1 and c: " + s1.brightnessDiff(c));
}