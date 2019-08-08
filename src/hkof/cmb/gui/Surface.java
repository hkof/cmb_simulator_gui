package hkof.cmb.gui;


import hkof.cmb.Layer;
import hkof.cmb.commands.Command;
import hkof.cmb.commands.ToolPathWayPoint;
import hkof.cmb.commands.WayPointType3;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

class Surface extends JPanel {

    Layer currentLayer;
    int numberOfCommandsToExecute = 0;

    private void doDrawing(Graphics g) {
        if(currentLayer == null )
            return;

        Graphics2D g2d = (Graphics2D) g;


        AffineTransform at = new AffineTransform();
        //at.translate(anchorx, anchory);
        at.translate(-400, -400);
        //at.scale(50, 50);
       g2d.setTransform(at);

        //Stroke stroke = new BasicStroke(1, BasicStroke.CAP_BUTT,
         //       BasicStroke.JOIN_BEVEL, 0, new float[] { 3, 1 }, 0);
        //g2d.setStroke(stroke);

        ToolPathWayPoint prevWayPointT1 = null;


        //control lines color
        //g2d.setColor(Color.BLUE);
        //control lines width
        //g2d.setStroke(new BasicStroke());
        boolean skipNextCmd = false;
        int counter = 0;
        for(Command cmd : currentLayer.getCommands()){
            counter++;
            if(counter > numberOfCommandsToExecute ){
                break;
            }
            if(cmd instanceof ToolPathWayPoint){
                // detect if we are printing with width zero
                // if so that means we are moving the printing tip without printing
                // so the next two if conditions detect this case to avoid drawing these movements
                if(skipNextCmd){
                    skipNextCmd = false;
                    prevWayPointT1 = null;
                    continue;
                }
                if(cmd instanceof WayPointType3){
                    if(((WayPointType3)cmd).getWidth() == 0) {
                        skipNextCmd = true;
                        continue;
                    }
                }

                ToolPathWayPoint currWayPointT1 = (ToolPathWayPoint) cmd;
                if(prevWayPointT1 == null){
                    prevWayPointT1 = currWayPointT1;
                }else{

                    Shape l = new Line2D.Float(prevWayPointT1.getX_coordinate()*150, prevWayPointT1.getY_coordinate()*150, currWayPointT1.getX_coordinate()*150, currWayPointT1.getY_coordinate()*150);

                    g2d.draw(l);

                    prevWayPointT1 = currWayPointT1;
                }
            }
        }

        g2d.dispose();


    }

    public void setCurrentLayer(Layer layer){

        this.currentLayer = layer;
        this.numberOfCommandsToExecute = layer.getCommands().size();

    }

    public void setNumberOfCommandToExecute(int num){

        this.numberOfCommandsToExecute = num;

    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }
}