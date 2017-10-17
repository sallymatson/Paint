//
//  Created by:
//      ad1229  Aissatou Diallo
//      pj202   Peter Johnston
//      sam439  Sally Matson
//
//  Disclosures are in Layout.java
//

package guiStuff;

import java.awt.*;

interface Shape {
    public int adjustHandleRadius = 6;
    public double getArea();
    public double getPerimeter();
    public void drawShape(Graphics g);
    public Color getColor();
    public boolean contains(int x, int y);
    public boolean isSelected();
    public void setSelected(boolean selected);
    public boolean adjustHandlesContain(int x, int y);
    public void adjust(int x, int y);
}
