package com.example.lib;

import com.sun.corba.se.impl.orbutil.graph.Graph;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by DoDo on 4/16/18.
 */

public class Display {
    JFrame frame;
    JPanel panel;
    Canvas canvas;
    BufferStrategy bs;
    int WINDOWWIDTH = 480;
    int WINDOWHEIGHT = 640;

    Color[] color = {Color.BLACK, Color.BLUE, Color.CYAN, Color.RED};

    public Display(){
        frame = new JFrame();

        frame.setPreferredSize(new Dimension(WINDOWWIDTH, WINDOWHEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        panel = (JPanel) frame.getContentPane();
        panel.setPreferredSize(new Dimension(WINDOWWIDTH, WINDOWHEIGHT));
        panel.setLayout(null);
        canvas = new Canvas();
        canvas.setBounds(0,0, WINDOWWIDTH, WINDOWHEIGHT);
        panel.add(canvas);
        canvas.createBufferStrategy(2);
        bs = canvas.getBufferStrategy();

        IController con = new IController();


    }

    public Canvas getCanvas() {
        return canvas;
    }

    public void clearCanvas() {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        g.clearRect(0,0, WINDOWWIDTH,WINDOWWIDTH);
    }


    public void draw(Collideable a)  {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();

        g.setColor(color[a.getID()]);
        g.fillRect((int)a.getX(), (int)a.getY(), a.getWidth(), a.getHeight());
        g.dispose();
        //bs.show();
    }

    public void draw(ArrayList<? extends Collideable> e)  {
        Graphics2D g = (Graphics2D) bs.getDrawGraphics();
        for (Collideable ent : e)   {
            g.setColor(color[ent.getID()]);
            g.fillRect((int)ent.getX(), (int)ent.getY(), ent.getWidth(), ent.getHeight());
        }
        g.dispose();
    }

    public void paintCanvas()   {
        bs.show();
    }

    public Point getMouse() {
        Point mouse = MouseInfo.getPointerInfo().getLocation();
        Point screen = canvas.getLocationOnScreen();
        int cWidth = canvas.getWidth();
        int cHeight = canvas.getHeight();
        int x = (int)(mouse.getX() - screen.getX());
        int y = (int)(mouse.getY() - screen.getY());

        if (x <= 0)    {
            x = 0;
        } else if (x >= cWidth)   {
            x = cWidth;
        }
        if (y <= 0) {
            y = 0;
        } else if (y >= cHeight) {
            y = cHeight;
        }

        return new Point(x,y);
    }
}