package hkof.gui.cmb;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

class Surface extends JPanel {

    float [][] pointsArray;

    public Surface(float [][] pointsArr){
        this.pointsArray = pointsArr;
    }

    private void doDrawing(Graphics g) {

        Graphics2D g2d = (Graphics2D) g;

        //control lines color
        //g2d.setColor(Color.BLUE);
        //control lines width
        //g2d.setStroke(new BasicStroke(10));
        for(int i = 1; i < pointsArray.length;i++){

            Shape l = new Line2D.Float(pointsArray[i-1][0], pointsArray[i-1][1], pointsArray[i][0], pointsArray[i][1]);
            g2d.draw(l);
        }
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}