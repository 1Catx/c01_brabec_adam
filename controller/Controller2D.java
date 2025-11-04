package controller;

import view.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import model.Point;
import model.Polygon;
import rasterize.FilledLineRasterizer;

public class Controller2D { //řídící třída, která zpracovává uživatelský vstup (myš) a řídí kreslení
    private final Panel panel;

    private final Polygon poly = new Polygon();
    private Point preview = null; //dočasná pozice při tažení
    private boolean dragging = false;
    
    private final FilledLineRasterizer lr;

    public Controller2D(Panel panel) {
        this.panel = panel;
        this.lr = new FilledLineRasterizer(panel.getRaster());

        initListeners();
    }

    private void initListeners() {
        panel.addMouseListener(new MouseAdapter() {
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

        panel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (!dragging) return;
                preview = new Point(e.getX(), e.getY());

                drawScene();
            }
        });
    }

    private void drawScene() {
        panel.getRaster().clear();

        List<Point> pts = poly.points(); //hotové hrany
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

        panel.repaint();
    }

    private void drawLine(Point a, Point b) {
        lr.rasterize(a.getX(), a.getY(), b.getX(), b.getY());

        // lr.rasterize(new model.Line(a.getX(), a.getY(), b.getX(), b.getY(), 0xFFFF0000));
    }
}
