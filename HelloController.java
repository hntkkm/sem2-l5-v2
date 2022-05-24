package com.third;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class HelloController {
    int figuraAktywna = 0;
    final Paint[] paint = new Paint[1];
    double punktPrzesunieciaX = 0;
    double punktPrzesunieciaY = 0;
    double przesuniecie0;
    double przesuniecie1;
    double przesuniecie2;
    double przesuniecie3;
    double przesuniecie4;
    double przesuniecie5;

    @FXML
    private ColorPicker chooseColor;

    @FXML
    private MenuItem chooseDowolne;

    @FXML
    private MenuItem chooseKolo;

    @FXML
    private MenuItem chooseKwadrat;

    @FXML
    private MenuItem chooseLinia;

    @FXML
    private MenuItem chooseTrojkat;


    @FXML
    private Pane drawPlace;
//
//    public void nwm() {
//        drawPlace.borderProperty().bindBidirectional(new Property<Border>() {
//            @Override
//            public void bind(ObservableValue<? extends Border> observableValue) {
//
//            }
//
//    }

    // todo zmienic kolory zeby zobaczyc co jest wyzej a co nizej, ladny border

    @FXML
    void drawDowolne(ActionEvent event) {
        Canvas canvas = new Canvas(580,550);
        final GraphicsContext graphicsContext = canvas.getGraphicsContext2D();
        canvas.setOnMousePressed( new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent event) {
                graphicsContext.setStroke(chooseColor.getValue());
                graphicsContext.beginPath();
                graphicsContext.moveTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        });
        canvas.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                graphicsContext.lineTo(event.getX(), event.getY());
                graphicsContext.stroke();
            }
        });
        canvas.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                canvas.setOnMousePressed(null);
                canvas.setOnMouseDragged(null);
                canvas.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(canvas);
    }

    @FXML
    void drawLinia(ActionEvent event) {
        Line line = new Line();
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                line.setStroke(chooseColor.getValue());
                line.setVisible(false);
                line.setEndX(mouseEvent.getX());
                line.setEndY(mouseEvent.getY());
                System.out.println("konieczne " + line.getStartX() + line.getStartY() + line.getEndX() + line.getEndY());
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                line.setVisible(true);
                line.setStartX(mouseEvent.getX());
                line.setStartY(mouseEvent.getY());
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawPlace.setOnMouseDragged(null);
                System.out.println("poczatek");
                line.setStartX(mouseEvent.getX());
                line.setStartY(mouseEvent.getY());
                System.out.println("koord "+line.getStartX() +" " +line.getStartY() + line.getEndX() + line.getEndY());
                drawPlace.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(line);
    }
    static long lastClicked = 0;
    @FXML
    void drawKolo(ActionEvent event) {
        // todo kolo zeby miescilo sie w drawPlace
        Circle kolo = new Circle();
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                kolo.setStroke(Color.BLACK);
                kolo.setFill(chooseColor.getValue());
                kolo.setCenterX(mouseEvent.getX());
                kolo.setCenterY(mouseEvent.getY());
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                kolo.setVisible(true);
                kolo.setRadius(Math.sqrt(Math.pow(kolo.getCenterX() - mouseEvent.getX(),2) + Math.pow(kolo.getCenterY() - mouseEvent.getY(), 2)));
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawPlace.setOnMouseDragged(null);
                System.out.println("koniec rysowania kola");
                kolo.setRadius(Math.sqrt(Math.pow(kolo.getCenterX() - mouseEvent.getX(),2) + Math.pow(kolo.getCenterY() - mouseEvent.getY(), 2)));
                drawPlace.setOnMouseReleased(null);
            }
        });

        drawPlace.getChildren().add(kolo);
        /////////////////////////////////////////////

//        kolo.setOnMousePressed(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent mouseEvent) {
//                if (lastClicked + 100 > System.currentTimeMillis()) {
//                    paint[0] = kolo.getFill();
//                    kolo.setFill(Color.YELLOW);
//                } else {
//                    paint[0] = kolo.getFill();
//                    kolo.setFill(Color.DARKGREEN);
//                }
//
//                lastClicked = System.currentTimeMillis();
//            }
//        });

        kolo.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) { ////////////////////////////////////////////

                if (figuraAktywna==0){
                    paint[0] = kolo.getFill();
                    kolo.setFill(Color.DARKGREEN);
                    figuraAktywna++;

                }
                else {
                    kolo.setFill(paint[0]);
                    figuraAktywna--;

                }
            }
        });

        kolo.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (figuraAktywna ==1) {
                    kolo.setCenterX(mouseEvent.getX());
                    kolo.setCenterY(mouseEvent.getY());
                }
            }
        });

        kolo.setOnScroll(new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent scrollEvent) {
                if (figuraAktywna ==1) {
                    System.out.println("scroll");
                    double scale = 0;
                    if (scrollEvent.getDeltaY() < 0) {
                        scale = -0.1;
                    } else {
                        scale = 0.1;
                    }
                    kolo.setScaleX(kolo.getScaleX() + scale);
                    kolo.setScaleY(kolo.getScaleY() + scale);
                }
            }
        });
        ////////////////////

    }




    @FXML
    void drawTrojkat(ActionEvent event) {
        //todo trojkat w druga strone
        Polygon trojkat = new Polygon();
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                trojkat.setFill(chooseColor.getValue());
                trojkat.getPoints().addAll(mouseEvent.getX(), mouseEvent.getY(),
                        mouseEvent.getX(), mouseEvent.getY(),
                        mouseEvent.getX(), mouseEvent.getY());
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                trojkat.getPoints().set(2, mouseEvent.getX());
                trojkat.getPoints().set(3, mouseEvent.getY());
                trojkat.getPoints().set(4, trojkat.getPoints().get(0) - Math.abs(mouseEvent.getX() - trojkat.getPoints().get(0)));
                trojkat.getPoints().set(5, mouseEvent.getY());
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawPlace.setOnMouseDragged(null);
                drawPlace.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(trojkat);
        final Paint[] paint = new Paint[1];

        trojkat.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                paint[0] = trojkat.getFill();
                trojkat.setFill(Color.DARKGREEN);
                przesuniecie0 = mouseEvent.getX() - trojkat.getPoints().get(0);
                przesuniecie1 = mouseEvent.getY() - trojkat.getPoints().get(1);
                przesuniecie2 = mouseEvent.getX() - trojkat.getPoints().get(2);
                przesuniecie3 = mouseEvent.getY() - trojkat.getPoints().get(3);
                przesuniecie4 = mouseEvent.getX() - trojkat.getPoints().get(4);
                przesuniecie5 = mouseEvent.getY() - trojkat.getPoints().get(5);
            }
        });
        trojkat.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                trojkat.getPoints().set(0, mouseEvent.getX() - przesuniecie0);
                trojkat.getPoints().set(2, mouseEvent.getX() - przesuniecie2);
                trojkat.getPoints().set(4, mouseEvent.getX() - przesuniecie4);
                trojkat.getPoints().set(1, mouseEvent.getY() - przesuniecie1);
                trojkat.getPoints().set(3, mouseEvent.getY() - przesuniecie3);
                trojkat.getPoints().set(5, mouseEvent.getY() - przesuniecie5);
            }
        });
        trojkat.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                trojkat.setFill(paint[0]);
            }
        });
        trojkat.setOnScroll(new EventHandler<ScrollEvent>() {                      // powiekszenie
            @Override
            public void handle(ScrollEvent scrollEvent) {
                double scale = 0;
                if (scrollEvent.getDeltaY() < 0){
                    scale = -0.1;
                } else {
                    scale = 0.1;
                }
                trojkat.setScaleX(trojkat.getScaleX() + scale);
                trojkat.setScaleY(trojkat.getScaleY() + scale);
            }
        });
    }

    @FXML                                                                           //KWADRAT
    void drawKwadrat(ActionEvent event) {
                                                                                    //todo Kwardat w lewo, w gore oraz w dol
        Rectangle kwadrat = new Rectangle();
        drawPlace.setOnMousePressed(new EventHandler<MouseEvent>(){                 //poczatek tworzenia figury
            @Override
            public void handle(MouseEvent mouseEvent) {
                kwadrat.setFill(chooseColor.getValue());
                kwadrat.setX(mouseEvent.getX());
                kwadrat.setY(mouseEvent.getY());
                drawPlace.setOnMousePressed(null);
            }
        });
        drawPlace.setOnMouseDragged(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                kwadrat.setWidth(Math.abs(kwadrat.getX() - mouseEvent.getX())); // abs(min(_ , _))
                kwadrat.setHeight(Math.abs(kwadrat.getY() - mouseEvent.getY()));
            }
        });
        drawPlace.setOnMouseReleased(new EventHandler<MouseEvent>() {               //koniec tworzenia figury
            @Override
            public void handle(MouseEvent mouseEvent) {
                drawPlace.setOnMouseDragged(null);
                kwadrat.setX(Math.min(kwadrat.getX(), mouseEvent.getX()));
                kwadrat.setWidth(Math.abs(kwadrat.getX() - mouseEvent.getX()));
                kwadrat.setY(Math.min(kwadrat.getY(), mouseEvent.getY()));
                kwadrat.setHeight(Math.abs(kwadrat.getY() - mouseEvent.getY()));
                System.out.println("poczatek");
                drawPlace.setOnMouseReleased(null);
            }
        });
        drawPlace.getChildren().add(kwadrat);
        kwadrat.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                paint[0] = kwadrat.getFill();
                kwadrat.setFill(Color.DARKGREEN);
                punktPrzesunieciaX = mouseEvent.getX() - kwadrat.getX();
                punktPrzesunieciaY = mouseEvent.getY() - kwadrat.getY();
            }
        });
        kwadrat.setOnMouseDragged(new EventHandler<MouseEvent>() {      //przesuniecie
            @Override
            public void handle(MouseEvent mouseEvent) {
                kwadrat.setX(mouseEvent.getX() - punktPrzesunieciaX);
                kwadrat.setY(mouseEvent.getY() - punktPrzesunieciaY);
            }
        });
        kwadrat.setOnMouseReleased(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                kwadrat.setFill(paint[0]);
            }
        });
        kwadrat.setOnScroll(new EventHandler<ScrollEvent>() {       // powiekszenie
            @Override
            public void handle(ScrollEvent scrollEvent) {
                double scale = 0;
                if (scrollEvent.getDeltaY() < 0){
                    scale = -0.1;
                } else {
                    scale = 0.1;
                }
                kwadrat.setScaleX(kwadrat.getScaleX() + scale);
                kwadrat.setScaleY(kwadrat.getScaleY() + scale);
            }
        });
    }
}
// todo zeby nie zmieniało kolor jak sie rysuje inna figure na poprzedniej

