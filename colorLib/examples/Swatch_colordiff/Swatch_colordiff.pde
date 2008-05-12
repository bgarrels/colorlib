import colorLib.*;

Swatch s1;
Swatch s2;
color  c;

void setup() {
	size(200, 200);
	noStroke();
			
	s1 = new Swatch(this, color(2, 125, 3));
	s2 = new Swatch(this, color(255, 230, 26));
	c  = color(35, 78, 199);
			
	fill(s1.getColor());
	rect(0, 0, width/2, height);
	fill(s2.getColor());
	rect(width/2, 0, width/2, height/2);
	fill(c);
	rect(width/2, height/2, width/2, height/2);
			
	println("Color Difference between s1 and s2: " + s1.colorDiff(s2));
	println("Color Difference between s1 and c : " + s1.colorDiff(c));
}
