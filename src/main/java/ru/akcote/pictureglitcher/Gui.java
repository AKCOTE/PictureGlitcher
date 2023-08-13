package ru.akcote.pictureglitcher;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Gui {
    private static final int WINDOW_WIDTH = 350;
    private static final int WINDOW_HEIGHT = 200;
    private static final Gui instance = new Gui();
    private static JFrame frame;
    private static GridBagConstraints xConstraints;
    private static GridBagConstraints yConstraints;
    private static GridBagConstraints zConstraints;
    private static GridBagConstraints xFirstFieldConstraints;
    private static GridBagConstraints xSecondFieldConstraints;
    private static GridBagConstraints yFirstFieldConstraints;
    private static GridBagConstraints ySecondFieldConstraints;
    private static GridBagConstraints zFirstFieldConstraints;
    private static GridBagConstraints zSecondFieldConstraints;
    private static GridBagConstraints selectPictureButtonConstraints;
    private static GridBagConstraints generateImageButtonConstraints;
    private static JTextField xFirstField;
    private static JTextField xSecondField;
    private static JTextField yFirstField;
    private static JTextField ySecondField;
    private static JTextField zFirstField;
    private static JTextField zSecondField;
    private static JButton selectPictureButton;
    private static JButton generateImageButton;
    private File[] selectedImages;

    private Gui() {
        xConstraints = new GridBagConstraints();
        xConstraints.gridx = 0;
        xConstraints.gridy = 0;
        yConstraints = new GridBagConstraints();
        yConstraints.gridx = 1;
        yConstraints.gridy = 0;
        zConstraints = new GridBagConstraints();
        zConstraints.gridx = 2;
        zConstraints.gridy = 0;
        xFirstFieldConstraints = new GridBagConstraints();
        xFirstFieldConstraints.gridx = 0;
        xFirstFieldConstraints.gridy = 1;
        xSecondFieldConstraints = new GridBagConstraints();
        xSecondFieldConstraints.gridx = 0;
        xFirstFieldConstraints.gridy = 2;
        yFirstFieldConstraints = new GridBagConstraints();
        yFirstFieldConstraints.gridx = 1;
        yFirstFieldConstraints.gridy = 1;
        ySecondFieldConstraints = new GridBagConstraints();
        ySecondFieldConstraints.gridx = 1;
        yFirstFieldConstraints.gridy = 2;
        zFirstFieldConstraints = new GridBagConstraints();
        zFirstFieldConstraints.gridx = 2;
        zFirstFieldConstraints.gridy = 1;
        zSecondFieldConstraints = new GridBagConstraints();
        zSecondFieldConstraints.gridx = 2;
        zFirstFieldConstraints.gridy = 2;
        selectPictureButtonConstraints = new GridBagConstraints();
        selectPictureButtonConstraints.gridx = 0;
        selectPictureButtonConstraints.gridy = 4;
        selectPictureButtonConstraints.gridwidth = 3;
        generateImageButtonConstraints = new GridBagConstraints();
        generateImageButtonConstraints.gridx = 0;
        generateImageButtonConstraints.gridy = 5;
        generateImageButtonConstraints.gridwidth = 3;
        xFirstField = new JTextField(5);
        xSecondField = new JTextField(5);
        yFirstField = new JTextField(5);
        ySecondField = new JTextField(5);
        zFirstField = new JTextField(5);
        zSecondField = new JTextField(5);
        selectPictureButton = new JButton("SELECT IMAGE");
        selectPictureButton.addActionListener((event) -> {
            JFileChooser chooser = new JFileChooser();
            chooser.setDialogTitle("Select images");
            chooser.setFileFilter(new FileNameExtensionFilter("", new String[]{"jpeg", "png", "jpg"}));
            chooser.setMultiSelectionEnabled(true);
            chooser.showOpenDialog(frame);
            this.selectedImages = chooser.getSelectedFiles();
        });
        generateImageButton = new JButton("GENERATE IMAGE");
        generateImageButton.addActionListener((event) -> {
            if (this.selectedImages != null && this.selectedImages.length != 0) {
                int xx;
                int xy;
                int yx;
                int yy;
                int zx;
                int zy;
                try {
                    xx = Integer.parseInt(xFirstField.getText());
                    xy = Integer.parseInt(xSecondField.getText());
                    yx = Integer.parseInt(yFirstField.getText());
                    yy = Integer.parseInt(ySecondField.getText());
                    zx = Integer.parseInt(zFirstField.getText());
                    zy = Integer.parseInt(zSecondField.getText());
                } catch (NumberFormatException var22) {
                    JOptionPane.showMessageDialog(frame, "Write numbers into fields", "", 2);
                    return;
                }

                File folder = new File("PictureGlitcher");
                folder.mkdirs();
                File[] var9 = this.selectedImages;
                int var10 = var9.length;

                for(int var11 = 0; var11 < var10; ++var11) {
                    File file = var9[var11];

                    try {
                        BufferedImage main = ImageIO.read(file);
                        BufferedImage after = new BufferedImage(main.getWidth(), main.getHeight(), main.getType());

                        int x;
                        int y;
                        Color mainColor;
                        for(x = xx; x < main.getWidth() + xx; ++x) {
                            for(y = xy; y < main.getHeight() + xy; ++y) {
                                try {
                                    mainColor = new Color(main.getRGB(x - xx, y - xy));
                                    after.setRGB(x, y, (new Color(mainColor.getRed(), 0, 0)).getRGB());
                                } catch (ArrayIndexOutOfBoundsException var21) {
                                }
                            }
                        }

                        Color currentColor;
                        for(x = yx; x < main.getWidth() + yx; ++x) {
                            for(y = yy; y < main.getHeight() + yy; ++y) {
                                try {
                                    mainColor = new Color(main.getRGB(x - yx, y - yy));
                                    currentColor = new Color(after.getRGB(x, y));
                                    after.setRGB(x, y, (new Color(currentColor.getRed(), mainColor.getGreen(), 0)).getRGB());
                                } catch (ArrayIndexOutOfBoundsException var20) {
                                }
                            }
                        }

                        for(x = zx; x < main.getWidth() + zx; ++x) {
                            for(y = zy; y < main.getHeight() + zy; ++y) {
                                try {
                                    mainColor = new Color(main.getRGB(x - zx, y - zy));
                                    currentColor = new Color(after.getRGB(x, y));
                                    after.setRGB(x, y, (new Color(currentColor.getRed(), currentColor.getGreen(), mainColor.getBlue())).getRGB());
                                } catch (ArrayIndexOutOfBoundsException var19) {
                                }
                            }
                        }

                        System.out.println(ImageIO.write(after, "PNG", new File(folder, file.getName())));
                        JOptionPane.showMessageDialog(frame, "DONE", "", 1);
                    } catch (Exception var23) {
                        JOptionPane.showMessageDialog(frame, String.format("Can`t glitch image %s", file.getName()), "", 0);
                        var23.printStackTrace();
                    }
                }

            } else {
                JOptionPane.showMessageDialog(frame, "Select images", "", 2);
            }
        });
    }

    public static void showFrame() {
        if (frame != null) {
            frame.dispose();
        }

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame = new JFrame("PictureGlitcher by AKCOTE");
        frame.setSize(350, 200);
        frame.setLocation((int)(screenSize.getWidth() - 350.0) / 2, (int)(screenSize.getHeight() - 200.0) / 2);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(3);
        frame.setLayout(new GridBagLayout());
        frame.add(new JLabel("X"), xConstraints);
        frame.add(new JLabel("Y"), yConstraints);
        frame.add(new JLabel("Z"), zConstraints);
        frame.add(xFirstField, xFirstFieldConstraints);
        frame.add(xSecondField, xSecondFieldConstraints);
        frame.add(yFirstField, yFirstFieldConstraints);
        frame.add(ySecondField, ySecondFieldConstraints);
        frame.add(zFirstField, zFirstFieldConstraints);
        frame.add(zSecondField, zSecondFieldConstraints);
        frame.add(selectPictureButton, selectPictureButtonConstraints);
        frame.add(generateImageButton, generateImageButtonConstraints);
        frame.setVisible(true);
    }
}
