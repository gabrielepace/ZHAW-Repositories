package ch.zhaw.ads;
public class LabyrinthNode<Edge> extends Node<Edge> {

    LabyrinthNode<Edge> previous;
    boolean mark = false;
    int x;
    int y;

    public LabyrinthNode() {
        super();
    }

    public boolean getMark() {
        return this.mark;
    }

    public void setMark(boolean mark) {
        this.mark = mark;
        return;
    }

    public LabyrinthNode<Edge> getPrevious() {
        return previous;
    }

    public void setPrevious(LabyrinthNode<Edge> previous) {
        this.previous = previous;
    }

    public int getX() {
        return this.x;
    }

    public void setX(int x) {
        this.x = x;
        return;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

}