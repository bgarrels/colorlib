import colorLib.*;
import colorLib.webServices.*;

void setup(){
  size(400, 400);
  Colr colr = new Colr(this);

  color cl = color(255);
  String[] tags = colr.searchTags(cl);
  for(int i=0; i<tags.length; i++){
    print(tags[i]);
    //salt berg pure blanc snow cold ice holy blanco clear
    //ivory ipod glacier silence love sky winter plain blizzard 
    //god diaphanous white readymix pure notfunny table aquaclic thjy
  }
  
  println(" ");
  tags = colr.searchTags("000000");
  for(int i=0; i<tags.length; i++){
    print(tags[i]);
    //death hole darkness all midnight inner eyelid space 
    //vacuum empty goth traffic black tar absolute black emo black black
  }
}

