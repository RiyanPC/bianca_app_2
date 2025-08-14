package Forms;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.*;

public class PanelLogos extends JPanel {

    public PanelLogos() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.WHITE);
        setBorder(new TitledBorder(
                new LineBorder(Color.LIGHT_GRAY, 1, true),
                "Logos",
                TitledBorder.LEFT,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                Color.DARK_GRAY
        ));
    }

    public void mostrarLogos(BufferedImage... imagenes) {
        limpiar();

        for (BufferedImage img : imagenes) {
            if (img != null) {
                ImageIcon icon = new ImageIcon(img.getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                JLabel lbl = new JLabel(icon);
                lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
                lbl.setBorder(new EmptyBorder(5, 5, 5, 5));
                add(lbl);
            }
        }
        revalidate();
        repaint();
    }
    public void mostrarLogos(byte[]... imagenesBytes) {
    limpiar();

    for (byte[] data : imagenesBytes) {
        if (data != null && data.length > 0) {
            try {
                BufferedImage img = ImageIO.read(new ByteArrayInputStream(data));
                if (img != null) {
                    ImageIcon icon = new ImageIcon(img.getScaledInstance(120, 120, Image.SCALE_SMOOTH));
                    JLabel lbl = new JLabel(icon);
                    lbl.setAlignmentX(Component.CENTER_ALIGNMENT);
                    lbl.setBorder(new EmptyBorder(5, 5, 5, 5));
                    add(lbl);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    revalidate();
    repaint();
}


    public void limpiar() {
        removeAll();
        revalidate();
        repaint();
    }
}
