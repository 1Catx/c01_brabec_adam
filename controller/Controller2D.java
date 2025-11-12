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
    private final FilledLineRasterizer lr;

    private final Polygon poly = new Polygon();
    private Point preview = null; //dočasná pozice při tažení
    private boolean dragging = false;
    
    /* barevný gradient */
    private boolean useGradient = false;
    private int c1 = 0xFFFF0000;
    private int c2 = 0xFF0000FF;

    public Controller2D(Panel myPanel) {
        this.myPanel = myPanel;

        this.lr = new FilledLineRasterizer(myPanel.getRaster());

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

                Point a = poly.getSize() > 0 ? poly.getPoint(poly.getSize() - 1) : new Point(e.getX(), e.getY());

                preview = shiftMode(a, e.getX(), e.getY(), e.isShiftDown());

                drawScene();
            }
        });

        myPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_C) {
                    clearAll();
                }
                if (e.getKeyCode() == java.awt.event.KeyEvent.VK_G) { 
                    useGradient = !useGradient;
                    drawScene(); }
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
        if (useGradient) lr.rasterizeGradient(a.getX(), a.getY(), b.getX(), b.getY(), c1, c2);
        else             lr.rasterize(a.getX(), a.getY(), b.getX(), b.getY());
    }

    private void clearAll() {
        poly.clear();
        preview = null;
        dragging = false;

        myPanel.getRaster().clear();
        myPanel.repaint();
    }

    private Point shiftMode(Point a, int mx, int my, boolean shift) {
        if (!shift) return new Point(mx, my);

        int ax = a.getX(), ay = a.getY();
        int dx = mx - ax, dy = my - ay;             //posuny se směrem
        int adx = Math.abs(dx), ady = Math.abs(dy); //velikost posunu

        if (adx > 2*ady) {                          //horizontála když delta "x" je větší jak 2* delta "y" 
            return new Point(mx, ay);
        } else if (ady > 2*adx) {                   //vertikála když delta "y" je větší jak 2* delta "x"
            return new Point(ax, my);
        } else {                                    //diagonála 45°
            int d  = Math.min(adx, ady);
            int sx = Integer.signum(dx);
            int sy = Integer.signum(dy);
            return new Point(ax + sx*d, ay + sy*d);
        }
    }
}
