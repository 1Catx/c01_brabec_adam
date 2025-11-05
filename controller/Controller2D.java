package controller;

import view.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import model.Point;
import model.Polygon;
import rasterize.FilledLineRasterizer;

public class Controller2D { //řídící třída, která zpracovává uživatelský vstup (myš) a řídí kreslení
    private final Panel myPanel;

    private final Polygon poly = new Polygon();
    private Point preview = null; //dočasná pozice při tažení
    private boolean dragging = false;

    public Controller2D(Panel myPanel) {
        this.myPanel = myPanel;

        initListeners();
    }

    private void initListeners() {
        myPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                dragging = true;
                preview = new Point(e.getX(), e.getY());

                drawScene();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (!dragging) return;
                dragging = false;
                preview = null;
                poly.addPoint(new Point(e.getX(), e.getY()));

                drawScene();
            }
        });

        myPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!dragging) return;
                preview = new Point(e.getX(), e.getY());

                drawScene();
            }
        });

        myPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_C) {
                    clearAll();
                }
            }
        });
    }

    private void drawScene() {
        myPanel.getRaster().clear();

        List<Point> pts = poly.getMyPoints(); //hotové hrany
        
        for (int i = 0; i < pts.size() - 1; i++) {
            drawLine(pts.get(i), pts.get(i + 1));
        }

        if (!dragging && pts.size() >= 3) { //uzavření polygonu
            drawLine(pts.get(pts.size() - 1), pts.get(0));
        }

        if (dragging && preview != null) { //náhled při tažení
            if (pts.size() >= 1) {
                drawLine(pts.get(pts.size() - 1), preview);
            }

            if (pts.size() >= 2) {
                drawLine(preview, pts.get(0));
            }
        }

        myPanel.repaint();
    }

    private void drawLine(Point a, Point b) {
        FilledLineRasterizer raster = new FilledLineRasterizer (myPanel.getRaster());
        raster.rasterize(a.getX(), a.getY(), b.getX(), b.getY());
    }

    private void clearAll() {
        poly.clear();
        preview = null;
        dragging = false;

        myPanel.getRaster().clear();
        myPanel.repaint();
    }

}
