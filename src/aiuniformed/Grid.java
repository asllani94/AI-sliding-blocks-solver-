/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aiuniformed;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import java.util.ArrayList;

/**
 *
 * @author arnold
 */
public class Grid implements Serializable {

    public int[][] gridArray;
    private int counterHash;
    private int m;
    private int n;
    private ArrayList<Block> blocks;
    private Grid parent;
    private Action actionTaken;

    public Grid(int m, int n) {
        gridArray = new int[m][n];

        this.m = m;
        this.n = n;
        counterHash = 1;
        this.blocks = new ArrayList<>();
        this.parent = null;
    }

    public Grid() {
        this.m = 6;
        this.n = 6;
        counterHash = 1;
        this.blocks = new ArrayList<>();
        this.parent = null;
    }

    /**
     * @return the actionTaken
     */
    public Action getActionTaken() {
        return actionTaken;
    }

    /**
     * @param actionTaken the actionTaken to set
     */
    public void setActionTaken(Action actionTaken) {
        this.actionTaken = actionTaken;
    }

  

    public void addBlock(Block b) {

        if (!hasCollission(b.getHead(), b.getTail(), b.isVertical())) {

            b.setKey(getNextHash());

            if (b.isVertical()) {
                for (int x = b.getHead().getX(); x <= b.getTail().getX(); x++) {

                    gridArray[x][b.getHead().getY()] = b.getKey();

                }
            } else {
                for (int y = b.getHead().getY(); y <= b.getTail().getY(); y++) {

                    gridArray[b.getHead().getX()][y] = b.getKey();

                }

            }
            this.blocks.add(b);

        } else {
            System.out.println("Collission found!! Cant overlap blocks");

        }

    }

    private boolean hasCollission(Point p1, Point p2, boolean isVertical) {

        if (isVertical) {
            for (int x = p1.getX(); x <= p2.getX(); x++) {
                if (!isEmptyCell(x, p1.getY())) {
                    return true;

                }

            }
            return false;

        } else {
            for (int y = p1.getY(); y <= p2.getY(); y++) {
                if (!isEmptyCell(p1.getX(), y)) {
                    return true;

                }

            }
            return false;
        }

    }

    public boolean moveBlock(int key, Direction d, Grid g) {
        Block b = this.getBlockByKey(key);

        switch (d) {
            case UP:
                if (!b.isVertical()) {
                    return false;
                } else {
                    if (this.canMoveUp(b, g)) {
                        this.moveBlockUp(b, g);
                        return true;
                    } else {
                        return false;
                    }

                } //move b up man
            case DOWN:
                if (!b.isVertical()) {
                    return false;
                } else {
                    if (this.canMoveDown(b, g)) {
                        this.moveBlockDown(b, g);
                        return true;

                    } else {
                        return false;
                    }

                } // move b down man
            case LEFT:
                if (b.isVertical()) {
                    return false;
                } else {
                    if (this.canMoveLeft(b, g)) {
                        this.moveBlockLeft(b, g);
                        return true;

                    } else {
                        return false;
                    }

                } //move b left man
            case RIGHT:
                if (b.isVertical()) {
                    return false;
                } else {

                    if (this.canMoveRight(b, g)) {
                        this.moveBlockRight(b, g);
                        return true;

                    } else {
                        return false;
                    }
                }// move b right man

        }

        return false;
    }

    public ArrayList<Block> getBlockList() {
        return this.blocks;
    }

    private boolean isEmptyCell(int x, int y) {
        return gridArray[x][y] == 0;
    }

    private int getNextHash() {
        return this.counterHash++;
    }

    private boolean canMoveLeft(Block b, Grid g) {
        int x = b.getHead().getX();
        int y = b.getHead().getY() - 1;

        if (x < 0 | y < 0) {
            return false;
        } else if (x > 5 | y > 5) {
            return false;
        } else {
            return g.gridArray[x][y] == 0;
        }
    }

    private boolean canMoveRight(Block b, Grid g) {
        int x = b.getTail().getX();
        int y = b.getTail().getY() + 1;
        if (x < 0 | y < 0) {
            return false;
        } else if (x > 5 | y > 5) {
            return false;
        } else {
            return g.gridArray[x][y] == 0;
        }
    }

    private boolean canMoveUp(Block b, Grid g) {
        int x = b.getHead().getX() - 1;
        int y = b.getHead().getY();
        if (x < 0 | y < 0) {
            return false;
        } else if (x > 5 || y > 5) {
            return false;
        } else {
            return g.gridArray[x][y] == 0;
        }
    }

    private boolean canMoveDown(Block b, Grid g) {
        int x = b.getTail().getX() + 1;
        int y = b.getTail().getY();
        if (x < 0 | y < 0) {
            return false;
        } else if (x > 5 | y > 5) {
            return false;
        } else {
            return g.gridArray[x][y] == 0;
        }
    }

    private void moveBlockLeft(Block b, Grid g) {

        Point newHead = new Point(b.getHead().getX(), b.getHead().getY() - 1);
        Point newTail = new Point(b.getTail().getX(), b.getTail().getY() - 1);
        g.gridArray[b.getTail().getX()][b.getTail().getY()] = 0;
        g.gridArray[b.getHead().getX()][b.getHead().getY() - 1] = b.getKey();

        b.setHead(newHead);
        b.setTail(newTail);

    }

    private void moveBlockRight(Block b, Grid g) {
        Point newHead = new Point(b.getHead().getX(), b.getHead().getY() + 1);
        Point newTail = new Point(b.getTail().getX(), b.getTail().getY() + 1);
        g.gridArray[b.getHead().getX()][b.getHead().getY()] = 0;
        g.gridArray[b.getTail().getX()][b.getTail().getY() + 1] = b.getKey();
        b.setHead(newHead);
        b.setTail(newTail);

    }

    private void moveBlockUp(Block b, Grid g) {
        Point newHead = new Point(b.getHead().getX() - 1, b.getHead().getY());
        Point newTail = new Point(b.getTail().getX() - 1, b.getTail().getY());

        g.gridArray[b.getTail().getX()][b.getTail().getY()] = 0;
        g.gridArray[b.getHead().getX() - 1][b.getHead().getY()] = b.getKey();
        b.setHead(newHead);
        b.setTail(newTail);

    }

    private void moveBlockDown(Block b, Grid g) {
        Point newHead = new Point(b.getHead().getX() + 1, b.getHead().getY());
        Point newTail = new Point(b.getTail().getX() + 1, b.getTail().getY());

        g.gridArray[b.getHead().getX()][b.getHead().getY()] = 0;
        g.gridArray[b.getTail().getX() + 1][b.getTail().getY()] = b.getKey();

        b.setHead(newHead);
        b.setTail(newTail);

    }

    public Block getBlockByKey(int key) {
        for (Block b : this.blocks) {
            if (b.getKey() == key) {
                return b;
            }
        }
        return null;

    }

    public void display() {
        for (int i = 0; i < this.m; i++) {
            String row = "";
            for (int j = 0; j < this.n; j++) {
                row += gridArray[i][j] + " ";
            }
            System.out.println(row.trim());
        }

    }

    public Grid getParent() {
        return this.parent;
    }

    public void setParent(Grid d) {
        this.parent = d;
    }

    public Object deepClone(Object object) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            ByteArrayInputStream bais = new ByteArrayInputStream(baos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
