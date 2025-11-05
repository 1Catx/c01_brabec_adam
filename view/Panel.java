package view;

import raster.RasterBufferedImage;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel { //panel zobrazuje raster - RasterBufferedImage. Raster drží obrazová data

    private final RasterBufferedImage raster; //"digitální plátno" v paměti, kam kreslíme pixely, než se celý obrázek zobrazí na obrazovce

    public Panel(int width, int height) {
        setPreferredSize(new Dimension(width, height));
        setFocusable(true);
        requestFocusInWindow();

        raster = new RasterBufferedImage(width, height);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(raster.getImage(), 0, 0, null);
    }

    public RasterBufferedImage getRaster() {
        return raster;
    }
}

