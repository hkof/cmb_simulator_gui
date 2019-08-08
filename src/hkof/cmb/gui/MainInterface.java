package hkof.cmb.gui;

import hkof.cmb.Layer;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.io.File;
import java.util.List;
import hkof.cmb.parser.CmbParser;
public class MainInterface extends JFrame {

    List<Layer> layers;
    int currLayer = 0;

    public MainInterface() {

        initUI();
    }

    private void initUI() {

        String target = "samples/strong_qube_4_draft3_scale0.65_solid.cmb";

        layers = processData(target);

        JLabel currCommandLabel = new JLabel("Current Command: -");
        Surface surface = new Surface();
        surface.setCurrentLayer(layers.get(currLayer));

        add(surface, BorderLayout.CENTER);
        Panel sliders = new Panel(new FlowLayout());
        SpinSlider layerSlider = new SpinSlider(layers.size() -1, "layers");
        SpinSlider layerDrawingSlider = new SpinSlider(layers.get(currLayer).getCommands().size() -1, " commands ");
        layerSlider.setChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {

                if(changeEvent.getSource() instanceof JSlider){
                    currLayer = ( (JSlider) changeEvent.getSource()).getValue();
                }else if(changeEvent.getSource() instanceof JSpinner){
                    currLayer = ((Integer) ((JSpinner) changeEvent.getSource()).getValue());
                }
                surface.setCurrentLayer(layers.get(currLayer));
                layerDrawingSlider.setMaximumValue(layers.get(currLayer).getCommands().size());
                currCommandLabel.setText("Current Command: -");
                surface.repaint();
            }
        });

        layerDrawingSlider.setChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                int numberOfCommandsToExecute = 0;
                if(changeEvent.getSource() instanceof JSlider){
                    numberOfCommandsToExecute = ( (JSlider) changeEvent.getSource()).getValue();
                }else if(changeEvent.getSource() instanceof JSpinner){
                    numberOfCommandsToExecute = ((Integer) ((JSpinner) changeEvent.getSource()).getValue());
                }
                surface.setNumberOfCommandToExecute(numberOfCommandsToExecute);
                try {
                    currCommandLabel.setText(String.format("Current Command: %s", layers.get(currLayer).getCommands().get(numberOfCommandsToExecute ).toString()));
                }catch (IndexOutOfBoundsException ex){
                    ex.printStackTrace();
                }
                surface.repaint();
            }
        });

        sliders.add(layerSlider);
        sliders.add(layerDrawingSlider);
        //sliders.setBounds(61, 11, 81, 140);

        add(sliders, BorderLayout.LINE_END);
        add(currCommandLabel, BorderLayout.PAGE_END);

        setTitle("CMB Parser GUI");
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public List<Layer> processData(String path){
        List<Layer> layers = null;//"samples\\Question.cmb");//Exclamation.cmb");
        try {
            layers = CmbParser.fromFile(new File(path)).getLayers();
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
