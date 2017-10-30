ArrayList<Vertex> vert = new ArrayList<Vertex>();
ArrayList<Vertex> path = new ArrayList<Vertex>();
int margin = 20; // margins where we dont draw

void setup() 
{
  size(400, 400);
  frameRate(30);
  readIn();
  background(255);
  strokeWeight(4);
  drawEdges();
  //drawPath();
  strokeWeight(1);
  drawVerts();
  //save("out.png");
}

void draw() { 
  
} 

void readIn() {
  String[] lines = loadStrings("GraphInput.txt");
  int idstart = 1;
  for (int i = 2; i < lines.length-2; i++) {
    System.out.print(lines[i]);
    if (!lines[i].contains("\t"))
    {
      Vertex v = stringtoVert(lines[i]);
      v.setId(idstart);
      idstart++;
      vert.add(v);
      System.out.printf(" id = %d",idstart);
    }
    System.out.printf("\n");
  }
  int vertNum = 0;
  for (int i = 2; i < lines.length-2; i++) {
    if (!lines[i].contains("\t"))
    {
      Vertex parent = vert.get(vertNum);
      int j = i + 1;
      while (lines[j].contains("\t")) {
        parent.addN(stringtoVert(lines[j]));
        j++;
      }
      vertNum++;
    }
  }
  processPath(lines[lines.length-2]);
}

Vertex stringtoVert(String str) {
  str = str.replace("\t", "");
  str = str.replace("<", "");
  str = str.replace(">", "");
  str = str.replace(" ", "");
  int comma = str.indexOf(",");
  int x = int (str.substring(0, comma));
  int y = int (str.substring(comma+1));
  return new Vertex(x, y);
}  

void processPath(String str) {
  System.out.println(str);
  str = str.replace("[", "");
  str = str.replace("<", "");
  str = str.replace(">", "");
  str = str.replace(" ", "");
  str = str.replace("[", "");
  str = str.replace("]", "");
  while(str.length() > 0) {
    int comma = str.indexOf(",");
    int x = int (str.substring(0, comma));
    str = str.substring(comma+1);
    comma = str.indexOf(",");
    int y;
    if (comma != -1) {
    y = int (str.substring(0, comma));
    str = str.substring(comma+1);
    } else { 
    y = int (str);
    str = "";
    }
    path.add(new Vertex(x,y));
  }
}

boolean isInPath(Vertex in) {
  for (Vertex v : path) {
    if (v.equals(in)) return true;
  }
 return false; 
}


void drawVerts() {
  for (int i = 0; i < vert.size(); i++) {
    Vertex v = vert.get(i);
    //int xpos = v.x * ((width-20)/100)+20;
    int xpos = scaleX(v.x);
    //int ypos = v.y * ((height-20)/100)+20;
    int ypos = scaleY(v.y);
    stroke(0);
    if (i==0) {
      fill(53, 232, 118); // Green
    } else if (i==vert.size()-1) {
      fill(127, 167, 232); // Yellow
    } else if (isInPath(v)) {
      fill(246, 249, 62); // Blue
    } else
      fill(150);
    ellipse(xpos, ypos, 24, 24);
    fill(0);
    textAlign(CENTER,CENTER);
    text(v.id,xpos+2,ypos-1);
  }
}

void drawEdges() {
  stroke(0);
  for (Vertex v : vert) {
    for (Vertex n : v.getN()) {
      drawEdge(v,n);
    }
  }
}

void drawEdge(Vertex v, Vertex n){
  int x0 = scaleX(v.x);
      int y0 = scaleY(v.y);
      int x1 = scaleX(n.x);
      int y1 = scaleY(n.y);
      line(x0, y0, x1, y1);
}

void drawPath() {
  stroke(255,0,0);
   for (int i = 0; i < path.size()-1; i++) {
     drawEdge(path.get(i),path.get(i+1));
   }
}

int scaleX(int in) {

  return (int) map(in, 0, 100, margin, width-margin);
}

int scaleY(int in) {
  return (int) map(in, 0, 100, margin, height-margin);
}

void connect(Vertex a, Vertex b) {
  a.addN(b);
  b.addN(a);
}  

class Vertex { 
  int x, y, id;
  ArrayList<Vertex> n = new ArrayList<Vertex>();
  Vertex (int x_, int y_) {  
    x = x_; 
    y = y_;
  }
  void setId(int id_) {
    id = id_;
  }
  void addN(Vertex v) {
    n.add(v);
  }
  ArrayList<Vertex> getN() {
    return n;
  }
  boolean equals(Vertex v) {
    return(x == v.x && y == v.y);
  }
} 