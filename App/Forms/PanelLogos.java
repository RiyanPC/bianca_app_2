package Forms;

import java.awt.*;
import javax.swing.*;

public class PanelLogos extends JPanel {
    public PanelLogos() {
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
    }

    public void mostrarLogos(byte[]... imagenes) {
        removeAll();
        for (byte[] img : imagenes) {
            if (img != null && img.length > 0) {
                JLabel label = new JLabel();
                ImageIcon icon = new ImageIcon(img);
                Image scaled = icon.getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
                label.setIcon(new ImageIcon(scaled));
                label.setAlignmentX(Component.CENTER_ALIGNMENT);
                add(label);
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
