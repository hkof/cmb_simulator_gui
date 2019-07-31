package hkof.gui.cmb;

import com.util.parser.cmb_parser.Layer;
import com.util.parser.cmb_parser.WayPointType1;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainInterface extends JFrame {

    List<Layer> layers;
    int currLayer = 0;

    public MainInterface() {

        initUI();
    }

    private void initUI() {

        String target = "samples/strong_qube_4_draft3_scale0.65_solid.cmb";

        layers = processData(target);

        Surface surface = new Surface();
        surface.setCurrentLayer(layers.get(currLayer));
        final JButton b=new JButton("Prev.");
        final JButton b2 =new JButton("Next");
        b.setBounds(10,10,95,30);
        add(b);
        b.addActionListener(new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(currLayer > 0){
                    currLayer--;
                    surface.setCurrentLayer(layers.get(currLayer));
                }
                surface.repaint();
                b.repaint();
                b2.repaint();

            }
        });

        b2.setBounds(120,10,95,30);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if(currLayer < layers.size()){
                    currLayer++;
                    surface.setCurrentLayer(layers.get(currLayer));
                }
                surface.repaint();
                b2.repaint();
                b.repaint();
            }
        });
        add(b2);




        add(surface);

        setTitle("Lines");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public List<Layer> processData(String path){
        List<Layer> layers = null;//"samples\\Question.cmb");//Exclamation.cmb");
        try {
            layers = Layer.parseLayers(path);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return layers;
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
