class ExplorerImpl implements Explorer {
    private int x;
    private int y;
    public int getX() {return x;}
    public int getY() {return y;}
    public void goNorth() {y++;}
    public void goSouth() {y--;}
    public void goEast() {x++;}
    public void goWest() {x--;}
}
