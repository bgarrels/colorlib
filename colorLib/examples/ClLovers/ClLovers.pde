import colorLib.*;
import colorLib.webServices.*;

ClLovers Cl;
ClLoversTheme ClTheme;

void setup()
{
    size(400, 400);
    Cl = new ClLovers(this);
    Cl.getColors("fruit");
}

void draw()
{

}
