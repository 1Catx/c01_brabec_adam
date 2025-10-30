package controller;

import view.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import rasterize.FilledLineRasterizer;

public class Controller2D { //řídící třída, která zpracovává uživatelský vstup (myš) a řídí kreslení
    private final Panel panel;

    
    int x1 = -1;
    int y1 = -1;
    int x2 = -1;
    int y2 = -1;
    private boolean dragging = false;
    

    public Controller2D(Panel panel) {
        this.panel = panel;

        initListeners();
    }

    private void initListeners() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x1 = e.getX();
                y1 = e.getY();
                x2 = x1;
                y2 = y1;
                dragging = true;

                drawScene();
            }

            public void mouseDragged(MouseEvent e) {
                if (!dragging) return;
                x2 = e.getX();
                y2 = e.getY();
                dragging = false;
                
                drawScene();
            }

        });

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!dragging) return;
                x2 = e.getX();
                y2 = e.getY();
                drawScene();
            }
        });
    }


    private void drawScene() {
        panel.getRaster().clear();

        model.Line l = new model.Line(x1, y1, x2, y2, 0xFFFF0000);
        
        FilledLineRasterizer raster = new FilledLineRasterizer (panel.getRaster());
        raster.rasterize(l);

        panel.repaint();
    }
}
