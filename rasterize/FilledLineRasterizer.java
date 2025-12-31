package rasterize;

import model.Line;
import raster.RasterBufferedImage;

public class FilledLineRasterizer extends LineRasterizer {
    public FilledLineRasterizer(RasterBufferedImage raster) {
        super(raster);
    }

    @Override
    public void rasterize(Line line) {
        rasterize(line.getX1(), line.getY1(), line.getX2(), line.getY2());
    }

    //https://www.geeksforgeeks.org/computer-graphics/dda-line-generation-algorithm-computer-graphics
    @Override
    public void rasterize(int x1, int y1, int x2, int y2) { 
        int dx = x2 - x1; int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xInc = dx / (float) steps; float yInc = dy / (float) steps;
        float x = x1; float y = y1;

        for (int i = 0; i <= steps; i++) {
            raster.setPixel(Math.round(x), Math.round(y), 0xFFFF0000);
            x += xInc;
            y += yInc;
        }
    }

    /*
    DDA algoritmus
    Výhody: jednoduchý, univerzální pro všechny sklony, jednodušší implementace než Bresenham
    Nevýhody: používá float (zaokrouhlovací chyby a jejich kumulace), pomalejší než Bresenham 
        (Bresenham používá čistě celočíselné operace a jednoduché sčítání)
     */

    @Override
    public void rasterizeGradient(int x1, int y1, int x2, int y2, int c1, int c2) {
        int dx = x2 - x1; int dy = y2 - y1;

        int steps = Math.max(Math.abs(dx), Math.abs(dy));

        float xInc = dx / (float) steps; float yInc = dy / (float) steps;
        float x = x1; float y = y1;

        int r1 = (c1 >>> 16) & 0xFF, g1 = (c1 >>> 8) & 0xFF, b1 = c1 & 0xFF;
        int r2 = (c2 >>> 16) & 0xFF, g2 = (c2 >>> 8) & 0xFF, b2 = c2 & 0xFF; 

        for (int i = 0; i <= steps; i++) {
            float t = i / (float) steps;
            int r = Math.round(r1 + t * (r2 - r1));
            int g = Math.round(g1 + t * (g2 - g1));
            int b = Math.round(b1 + t * (b2 - b1));
            int color = (r << 16) | (g << 8) | b;

            raster.setPixel(Math.round(x), Math.round(y), color);
            x += xInc;
            y += yInc;
        }
    }
}
