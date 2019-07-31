package hkof.gui.cmb;

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