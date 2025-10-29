package controller;

import view.Panel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import rasterize.LineRasterizerTrivial;

public class Controller2D { //řídící třída, která zpracovává uživatelský vstup (myš) a řídí kreslení
    private final Panel panel;


    public Controller2D(Panel panel) {
        this.panel = panel;

        drawScene();
        initListeners();
    }

    private void initListeners() {
        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {


            }

        });
    }

    private void drawScene() {
        panel.getRaster().clear();


        model.Line l = new model.Line(100, 100, 120, 160, 0xFFFF0000);
        
        LineRasterizerTrivial raster = new LineRasterizerTrivial (panel.getRaster());
        raster.rasterize(l);


        panel.repaint();
    }
}
