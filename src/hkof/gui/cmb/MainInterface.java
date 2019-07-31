package hkof.gui.cmb;

import com.util.parser.cmb_parser.Layer;
import com.util.parser.cmb_parser.WayPointType1;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MainInterface extends JFrame {

    public MainInterface() {

        initUI();
    }

    private void initUI() {

        String target = "samples/strong_qube_4_draft3_scale0.65_solid.cmb";

        float [][] points = processData(target);

        add(new Surface());

        setTitle("Lines");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public float [][] processData(String path){
        float [][] result;// = new float[][];
        ArrayList<Layer> layers = null;//"samples\\Question.cmb");//Exclamation.cmb");
        try {
            layers = Layer.parseLayers(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Layer first_layer =  layers.get(33);
        for(int i = 0; i <first_layer.getSize(); i++) {
            if(first_layer.getCommand(i) instanceof WayPointType1){

            }
        }
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {

                MainInterface ex = new MainInterface();
                ex.setVisible(true);
            }
        });
    }
}
