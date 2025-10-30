package rasterize;

import model.Line;
import raster.RasterBufferedImage;

import java.awt.*;

public class FilledLineRasterizer extends LineRasterizer {
    public FilledLineRasterizer(RasterBufferedImage raster) {
        super(raster);
    }

    @Override
    public void rasterize(Line line) {
        rasterize(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    @Override
    public void rasterize(int x1, int y1, int x2, int y2) { //doplnit vysvětlení
        int dx = x2 - x1;
        int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xInc = dx / (float) steps;
        float yInc = dy / (float) steps;

        float x = x1;
        float y = y1;

        for (int i = 0; i <= steps; i++) {
            raster.setPixel(Math.round(x), Math.round(y), 0xFFFF0000);
            x += xInc;
            y += yInc;
        }
    }
}
