import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

public class MainForm extends JDialog {

    private DrawPanel drawPanel;
    private JPanel MainPane;
    private JPanel ModelPane;
    private JPanel ButtonPane;
    private JSlider sliderRotateX;
    private JSlider sliderRotateY;
    private JSlider sliderScaling;
    private JSlider sliderMoveZ;
    private JSlider sliderMoveX;
    private JButton doButton;
    private JSlider sliderMoveY;
    private JSlider sliderRotateZ;
    private JLabel LabelRotateX;
    private JLabel LabelRotateY;
    private JLabel LabelRotateZ;
    private JLabel LabelScaling;
    private JLabel LabelMoveZ1;
    private JLabel LabelMoveX;
    private JLabel LabelMoveY;
    private JButton AddObject;

    public MainForm() throws HeadlessException, IOException, SurfaceException {
        drawPanel = new DrawPanel();
        this.setContentPane(MainPane);
        MainPane.add(drawPanel);
        this.setSize(1170, 940);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setVisible(true);
        LabelRotateX.setText("0");
        LabelRotateY.setText("0");
        LabelRotateZ.setText("0");
        LabelScaling.setText("1");
        LabelMoveZ1.setText("0");
        LabelMoveX.setText("0");
        LabelMoveY.setText("0");

        doButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double ratio = 1.0 / (double) drawPanel.width;
                List<Transformation> transforms = drawPanel.getTransformations();
                for (Transformation transformation : transforms) {
                    transformation.moveModel(sliderMoveX.getValue() * ratio, sliderMoveZ.getValue() * ratio, sliderMoveY.getValue() * ratio);
                    transformation.rotateModel(sliderRotateX.getValue(), sliderRotateY.getValue(), sliderRotateZ.getValue());
                    double scaling = (double) sliderScaling.getValue() / 10;
                    transformation.scaleModel(scaling, scaling, scaling);
                }
                drawPanel.repaint();
            }
        });

        sliderRotateX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                LabelRotateX.setText(String.valueOf(sliderRotateX.getValue()));
            }
        });

        sliderRotateY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                LabelRotateY.setText(String.valueOf(sliderRotateY.getValue()));
            }
        });

        sliderRotateZ.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                LabelRotateZ.setText(String.valueOf(sliderRotateZ.getValue()));
            }
        });


        AddObject.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Model model = new Model(ObjectParsingService.readFromFile("src/main/resources/african_head.obj"));
                    drawPanel.listOfModel.add(model);
                    drawPanel.transformations.add(new Transformation(model));
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                } catch (SurfaceException surfaceException) {
                    surfaceException.printStackTrace();
                }
                drawPanel.repaint();
            }
        });

        sliderMoveX.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                LabelMoveX.setText(String.valueOf(sliderMoveX.getValue()));
            }
        });


        sliderMoveZ.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                LabelMoveZ1.setText(String.valueOf(sliderMoveZ.getValue()));
            }
        });


        sliderMoveY.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                LabelMoveY.setText(String.valueOf(sliderMoveY.getValue()));
            }
        });


        sliderScaling.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                LabelScaling.setText(String.valueOf((double) sliderScaling.getValue() / 10));
            }
        });


    }

    public static void main(String[] args) throws IOException, SurfaceException {
        MainForm dialog = new MainForm();

    }


}
