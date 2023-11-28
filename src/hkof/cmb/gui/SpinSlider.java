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

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinSlider extends JPanel {

    ChangeListener listener;
    JSpinner spinner;
    JSlider slider;
    String headerLabel;
    SpinnerNumberModel numberModel;

    public SpinSlider(int max, String headLabel) {

        this.setBorder(BorderFactory.createEtchedBorder(EtchedBorder.RAISED));

        this.headerLabel = headLabel;
        //panel = new JPanel();

        this.setPreferredSize(new Dimension(90, 250));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        spinner = new JSpinner();
        slider = new JSlider(JSlider.VERTICAL);

        slider.setMaximum(max);
        slider.setValue(0);
        slider.setMajorTickSpacing(max/3);
        slider.setMinorTickSpacing(0);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSlider s = (JSlider) e.getSource();
                spinner.setValue(s.getValue());
                if(listener != null){
                    listener.stateChanged(e);
                }

            }
        });
        JLabel title = new JLabel(headerLabel);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        add(title);
        add(slider);
        add(new JLabel(" "));
        numberModel = new SpinnerNumberModel(0, 0, max -1, 1);
        spinner.setModel(numberModel);
        spinner.setEditor(new JSpinner.NumberEditor(spinner, "0"));
        spinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                JSpinner s = (JSpinner) e.getSource();
                slider.setValue((Integer) s.getValue());

                if(listener != null){
                    listener.stateChanged(e);
                }
            }
        });
        add(spinner);
    }

    public void setChangeListener(ChangeListener lis){
        this.listener = lis;
    }

    public void setMaximumValue(int max){
        slider.setLabelTable(null);
        slider.setMajorTickSpacing(max/3);
        slider.setMaximum(max -1);


        spinner.setValue(new Integer(0));numberModel.setMaximum(max -1);
        slider.setValue(0);

    }


}