/*
Copyright 2023 Hamza Alkofahi

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

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

        String target = "samples/strong_qube.cmb";

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
