package GUI;

import javafx.geometry.Point2D;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;

import java.util.ArrayList;
import java.util.List;

public abstract class DisplayView extends ImageView {

    public static final String BASIC_TURTLE_NAME = "Basic Turtle Image";
    public static final String ADVANCED_TURTLE_NAME = "Advanced Turtle Image";
    private List<String> possibleImages = List.of(BASIC_TURTLE_NAME, ADVANCED_TURTLE_NAME);

    public static final String TURTLE_IMAGE = "file:/resources_images/turtle1.jpg";
    public static final int IMAGE_HEIGHT = 25;
    public static final int IMAGE_WIDTH = 20;

    private Canvas myCanvas;
    private Pen myPen;
    private GraphicsContext myContext;
    protected List<Move> myMoveHistory;

    public DisplayView(){
        this(new Image(TURTLE_IMAGE));
    }

    public DisplayView(Image image){
        super(image);
        setFitHeight(IMAGE_HEIGHT);
        setFitWidth(IMAGE_WIDTH);
        myPen = new Pen(true, Color.BLACK, PenStyle.DASHED, 2.0);
        myMoveHistory = new ArrayList<>();
        this.managedProperty().bind(this.visibleProperty());
        setRotate(90);
    }

    public DisplayView(Canvas canvas){
        this();
        myCanvas = canvas;
        myContext = myCanvas.getGraphicsContext2D();
    }

    public DisplayView(Image image, Canvas canvas){
        this(image);
        myCanvas = canvas;
        myContext = myCanvas.getGraphicsContext2D();
    }

    public DisplayView(DisplayView displayView, Image image){
        this(image, displayView.myCanvas);
        copyMoveHistoryAndPen(displayView);
        copyPositionAndOrientation(displayView);
    }

    private void copyPositionAndOrientation(DisplayView displayView) {
        setTranslateX(displayView.getTranslateX());
        setTranslateY(displayView.getTranslateY());
        setRotate(displayView.getRotate());
    }

    private void copyMoveHistoryAndPen(DisplayView displayView) {
        this.myMoveHistory = displayView.myMoveHistory;
        this.myPen = displayView.myPen;
    }

    public void addMove(Move turtleMove){
        myMoveHistory.add(turtleMove);
    }

    public void addAllMoves(List<Move> turtleMoves){
        myMoveHistory.addAll(turtleMoves);
    }

    public void clearMoves(){
        myMoveHistory.clear();
    }

    public void makeMove(Move move){
        updatePen(move);
        drawPath(move);
    }

    private void updatePosition(Move move) {
        setTranslateX(getTranslateX() + move.getDisplacement().getX());
        setTranslateY(getTranslateY() + move.getDisplacement().getY());
    }

    public void drawPath(Move move) {
        myContext.setLineWidth(move.getPenWidth());
        myContext.setStroke(move.getPenColor());
        myContext.beginPath();
        myContext.moveTo(this.getTranslateX() + myCanvas.getWidth()/2, this.getTranslateY() + myCanvas.getHeight()/2);
        updatePosition(move);
        myContext.lineTo(this.getTranslateX() + myCanvas.getWidth()/2, this.getTranslateY() + myCanvas.getHeight()/2);
        myContext.stroke();
        myContext.closePath();
    }

    public void drawPath(){
        for (Move move : myMoveHistory){
            makeMove(move);
        }
    }

    private void updatePen(Move move) {
        myPen.setDown(move.isPenDown());
        myPen.setMyColor(move.getPenColor());
        myPen.setMyStyle(move.getPathStyle());
        myPen.setMyWidth(move.getPenWidth());
    }

    public List<String> getPossibleImages() {
        return possibleImages;
    }

    public Pen getMyPen() {
        return myPen;
    }

    public void turtleMove(double pixels) {
        double orientation = Math.toRadians(getRotate());
        double deltaX = pixels * Math.sin(orientation);
        double deltaY = - pixels * Math.cos(orientation);
        moveTo(new Point2D(getTranslateX() + deltaX, getTranslateY() + deltaY));
    }

    private void moveTo(Point2D newLocation){
        Move move = new Move(new Pen(getMyPen()), newLocation);
        addMove(move);
        drawPath(move);
    }

    public void towards(double x, double y) {
        double deltaX = x - getTranslateX();
        double deltaY = y - getTranslateY();
        if (deltaX == 0){
            setRotate(Double.POSITIVE_INFINITY * deltaY);
        } else{
            setRotate(90 - Math.atan(deltaY/deltaX));
        }
    }

    public void setLocation(double x, double y) {
        moveTo(new Point2D(x, y));
    }

    public void goHome() {
        moveTo(new Point2D(0,0));
        setRotate(0);
    }

    public void turn(double degrees) {
        setRotate(getRotate() + degrees);
    }
}
