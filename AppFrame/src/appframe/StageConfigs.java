package appframe;



import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.property.Property;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class StageConfigs extends Application {

    Stage stage;
    Scene scene;

    public StageConfigs(Stage st, Scene sn) {
        stage = st;
        scene = sn;
    }

    double duWidth = Screen.getPrimary().getVisualBounds().getWidth();
    double duHeight = Screen.getPrimary().getVisualBounds().getHeight();

    double minimumOffset = 20;

    double MinWidth = 600 + minimumOffset;
    double MinHeight = 400 + minimumOffset;

    double StageWidth = 800 + minimumOffset;
    double StageHeight = 500 + minimumOffset;

    double fillerWidth = 800;
    double fillerHeight = 500;

    double dragAnchorX;
    double dragAnchorY;

    double widthCorrection = 1;

    double timer = 200;
    double bounceDelay = 400;
    double bounceMeter = 5;

    double stageX = 390;
    double stageY = 140;

    double topOffset = minimumOffset + (140 - stageY);
    double leftOffset = minimumOffset + (390 - stageX);
    double rightOffset = minimumOffset + (StageWidth - fillerWidth - leftOffset);
    double bottomOffset = minimumOffset + (StageHeight - fillerHeight - topOffset);

    double spaceing = 10;

    double minimizeXFixer = 0;
    double minimizeYFixer = 0;

    double minimizeXFixer1 = 0;
    double minimizeYFixer1 = 0;

    double fadeTimer = 600;
    boolean isFader = false;
    boolean wasFader = false;

    boolean isMinimize = false;

    double returnHeight = 0.0;

    double WidthDragCorrection = StageWidth - ((leftOffset - 10) + (rightOffset - 10));
    double HeightDragCorrection = StageHeight - ((topOffset - 10) + (bottomOffset - 10));

    Rectangle filler = new Rectangle(00, 00);

    Rectangle topBar = new Rectangle(00, 60);
    Rectangle leftBar = new Rectangle(20, 00);
    Rectangle rightBar = new Rectangle(20, 00);
    Rectangle bottomBar = new Rectangle(00, 20);

    Rectangle topDrag = new Rectangle(00, 03);
    Rectangle bottomDrag = new Rectangle(00, 03);
    Rectangle leftDrag = new Rectangle(03, 00);
    Rectangle rightDrag = new Rectangle(03, 00);

    Rectangle topSqLeftDrag = new Rectangle(05, 05);
    Rectangle topSqRightDrag = new Rectangle(05, 05);
    Rectangle bottomSqLeftDrag = new Rectangle(05, 05);
    Rectangle bottomSqRightDrag = new Rectangle(05, 05);

    ImageView minimizeImage = new ImageView();
    Rectangle minimizeButton = new Rectangle(30, 30);

    ImageView maximizeImage = new ImageView();
    Rectangle maximizeButton = new Rectangle(30, 30);

    ImageView closeImage = new ImageView();
    Rectangle closeButton = new Rectangle(30, 30);

    ImageView titleImage = new ImageView();

    ImageView seeImage = new ImageView();
    Ellipse see = new Ellipse();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage bufferStage) throws Exception {
        scene.getStylesheets().add(getClass().getResource("/css/Main.css").toExternalForm());
        stage.setWidth(StageWidth);
        stage.setHeight(StageHeight);
        stage.setX(stageX);
        stage.setY(stageY);
        stage.setScene(scene);
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.getIcons().add(new Image("/images/icon.png"));
        scene.setFill(Color.TRANSPARENT);
        StageConfigElementLister();
        see.setOpacity(0);
        seeImage.setOpacity(0);

        // ((Pane)scene.getRoot()).getChildren().add(scroll);
        masterFader();
    }

    public void StageConfigCreator(Rectangle element, double layoutX, double layoutY, double Width, double Height, String id, String cssID) {
        element.setWidth(Width);
        element.setHeight(Height);
        element.setLayoutX(layoutX);
        element.setLayoutY(layoutY);
        element.getStyleClass().add(cssID);
        element.setId(id);
        ((Pane) scene.getRoot()).getChildren().add(element);
        eventHandler(element);
        stage.show();
    }

    public void StageConfigCreator(ImageView element, double layoutX, double layoutY, double fitWidth, String id, Image elementImage) {
        element.setFitWidth(fitWidth);
        element.setLayoutX(layoutX);
        element.setLayoutY(layoutY);
        element.setPreserveRatio(true);
        element.setSmooth(true);
        element.setCache(true);
        element.setImage(elementImage);
        element.setId(id);
        ((Pane) scene.getRoot()).getChildren().add(element);
        eventHandler(element);
        stage.show();
    }

    public void StageConfigCreator(Ellipse element, double layoutX, double layoutY, double radX, double radY, String id, String cssID) {

        element.setRadiusX(radX);
        element.setRadiusY(radY);
        element.setLayoutX(layoutX);
        element.setLayoutY(layoutY);
        element.getStyleClass().add(cssID);
        element.setId(id);
        ((Pane) scene.getRoot()).getChildren().add(element);
        eventHandler(element);
        stage.show();
    }

    public void StageConfigElementLister() {
        StageConfigCreator(filler,
                (leftOffset - 2.5),
                (topOffset - 2.5),
                ((stage.getWidth() + 5) - (rightOffset + leftOffset)),
                ((stage.getHeight() + 5) - (bottomOffset + topOffset)),
                ("filler"),
                ("filler"));

        StageConfigCreator(topBar,
                (leftOffset),
                (topOffset),
                ((stage.getWidth() - rightOffset - leftOffset)),
                (topBar.getHeight()),
                ("topBar"),
                ("bar"));
        StageConfigCreator(leftBar,
                (leftOffset),
                ((topOffset + topBar.getHeight() + spaceing)),
                (leftBar.getWidth()),
                ((stage.getHeight() - (bottomOffset + bottomBar.getHeight() + spaceing + topOffset + topBar.getHeight() + spaceing))),
                ("leftBar"),
                ("bar"));
        StageConfigCreator(rightBar,
                ((stage.getWidth() - rightOffset - rightBar.getWidth())),
                ((topOffset + topBar.getHeight() + spaceing)),
                (rightBar.getWidth()),
                ((stage.getHeight() - (bottomOffset + bottomBar.getHeight() + spaceing + topOffset + topBar.getHeight() + spaceing))),
                ("rightBar"),
                ("bar"));
        StageConfigCreator(bottomBar,
                (leftOffset),
                ((stage.getHeight() - bottomOffset - bottomBar.getHeight())),
                ((stage.getWidth() - rightOffset - leftOffset)),
                (bottomBar.getHeight()),
                ("bottomBar"),
                ("bar"));

        StageConfigCreator(topDrag,
                (leftOffset),
                ((topOffset - 3)),
                ((stage.getWidth() - rightOffset - leftOffset)),
                (topDrag.getHeight()),
                ("topDrag"),
                ("dragTopBottom"));
        StageConfigCreator(leftDrag,
                ((leftOffset - 3)),
                ((topOffset + topDrag.getHeight() - 3)),
                (leftDrag.getWidth()),
                (stage.getHeight() - ((bottomOffset - 3) + bottomDrag.getHeight() + (topOffset - 3) + topDrag.getHeight())),
                ("leftDrag"),
                ("dragLeftRight"));
        StageConfigCreator(rightDrag,
                ((scene.getWidth() - (rightOffset - 3) - rightDrag.getWidth())),
                ((topOffset + topDrag.getHeight() - 3)),
                (rightDrag.getWidth()),
                ((stage.getHeight() - ((bottomOffset - 3) + bottomDrag.getHeight() + (topOffset - 3) + topDrag.getHeight()))),
                ("rightDrag"),
                ("dragLeftRight"));
        StageConfigCreator(bottomDrag,
                (leftOffset),
                ((stage.getHeight() - (bottomOffset - 3) - bottomDrag.getHeight())),
                ((stage.getWidth() - rightOffset - leftOffset)),
                (bottomDrag.getHeight()),
                ("bottomDrag"),
                ("dragTopBottom"));

        StageConfigCreator(topSqLeftDrag,
                ((leftOffset - 3)),
                ((topOffset - 3)),
                (topSqLeftDrag.getWidth()),
                (topSqLeftDrag.getHeight()),
                ("topSqLeftDrag"),
                ("dragLeftDiag"));
        StageConfigCreator(topSqRightDrag,
                ((stage.getWidth() - rightOffset - 2)),
                ((topOffset - 3)),
                (topSqRightDrag.getWidth()),
                (topSqRightDrag.getHeight()),
                ("topSqRightDrag"),
                ("dragRightDiag"));
        StageConfigCreator(bottomSqLeftDrag,
                ((leftOffset - 3)),
                ((scene.getHeight() - bottomOffset - 2)),
                (bottomSqLeftDrag.getWidth()),
                (bottomSqLeftDrag.getHeight()),
                ("bottomSqLeftDrag"),
                ("dragRightDiag"));
        StageConfigCreator(bottomSqRightDrag,
                ((stage.getWidth() - rightOffset - 2)),
                ((scene.getHeight() - bottomOffset - 2)),
                (bottomSqRightDrag.getWidth()),
                (bottomSqRightDrag.getHeight()),
                ("bottomSqRightDrag"),
                ("dragLeftDiag"));

        StageConfigCreator(minimizeButton,
                ((stage.getWidth() - rightOffset - rightBar.getWidth() - closeButton.getWidth() - maximizeButton.getWidth() - 35)),
                ((topOffset + 15)),
                (minimizeButton.getWidth()),
                (minimizeButton.getHeight()),
                ("minimizeButton"),
                ("button"));
        StageConfigCreator(minimizeImage,
                ((stage.getWidth() - rightOffset - rightBar.getWidth() - closeButton.getWidth() - maximizeButton.getWidth() - 31)),
                ((topOffset + 25)),
                (22),
                ("minimizeImage"),
                (new Image("/images/minimize.png")));

        StageConfigCreator(maximizeButton,
                ((stage.getWidth() - rightOffset - rightBar.getWidth() - closeButton.getWidth() - 30)),
                ((topOffset + 15)),
                (maximizeButton.getWidth()),
                (maximizeButton.getHeight()),
                ("maximizeButton"),
                ("button"));
        StageConfigCreator(maximizeImage,
                ((stage.getWidth() - rightOffset - rightBar.getWidth() - closeButton.getWidth() - 26)),
                ((topOffset + 20.4)),
                (22),
                ("maximizeImage"),
                (new Image(ImageChooser("minimize"))));

        StageConfigCreator(closeButton,
                ((stage.getWidth() - rightOffset - rightBar.getWidth() - 25)),
                ((topOffset + 15)),
                (closeButton.getWidth()),
                (closeButton.getHeight()),
                ("closeButton"),
                ("button"));
        StageConfigCreator(closeImage,
                ((stage.getWidth() - rightOffset - rightBar.getWidth() - 21)),
                ((topOffset + 19.6)),
                (22),
                ("closeImage"),
                (new Image("/images/close.png")));

        StageConfigCreator(titleImage,
                ((leftOffset + 7)),
                ((topOffset + 7)),
                (50),
                ("titleImage"),
                (new Image("/images/logo.png")));

        StageConfigCreator(see,
                ((stage.getWidth() - rightOffset)),
                ((60 + topOffset)),
                (17),
                (15),
                ("see"),
                ("see"));
        StageConfigCreator(seeImage,
                (stage.getWidth() - rightOffset - rightBar.getWidth()),
                ((60)),
                (38),
                ("seeImage"),
                (new Image(ImageChooser("hideUnhide"))));
    }

    public String ImageChooser(String buttonImage) {
        if (buttonImage.equals("minimize")) {
            if ((stage.getWidth() == duWidth + leftOffset + rightOffset - 10) && (stage.getHeight() == duHeight + bottomOffset + topOffset + 30)) {
                return ("/images/maximize_makeSmall.png");
            } else {
                return ("/images/maximize_makeBig.png");
            }
        } else if (buttonImage.equals("hideUnhide")) {
            if (isFader == false) {
                return ("/images/hide.png");
            } else {
                return ("/images/unhide.png");
            }
        }
        return ("error");

    }

    public void leftPressed(MouseEvent t) {
        scene.getRoot().getStyleClass().add("bg");
        masterFader();
        dragAnchorX = t.getSceneX();
    }

    public void leftDragged(MouseEvent t) {
        eyeHider();
        if ((stage.getWidth() >= MinWidth) || (stage.getX() - t.getScreenX() >= 0)) {
            double diff = stage.getX();
            stage.setX(t.getScreenX() - dragAnchorX);
            diff = diff - stage.getX();
            stage.setWidth(stage.getWidth() + (diff));
        }
    }

    public void leftRightReleased(MouseEvent t) {
        scene.getRoot().getStyleClass().remove("bg");
        scene.setFill(Color.TRANSPARENT);
        leftRightDraggerProp();
        StageWidth = stage.getWidth();
    }

    public void rightDragged(MouseEvent t) {
        eyeHider();
        if ((stage.getWidth() >= MinWidth) || ((stage.getX() + stage.getWidth()) - t.getScreenX() <= 0)) {
            stage.setWidth(t.getSceneX() + rightOffset);
        }
    }

    public void topPressed(MouseEvent t) {
        scene.getRoot().getStyleClass().add("bg");
        masterFader();
        dragAnchorY = t.getSceneY();

    }

    public void topLeftPressed(MouseEvent t) {
        scene.getRoot().getStyleClass().add("bg");
        masterFader();
        dragAnchorY = t.getSceneY();
        dragAnchorX = t.getSceneX();
    }

    public void topDragged(MouseEvent t) {
        eyeHider();

        if ((stage.getHeight() >= MinHeight) || (stage.getY() - t.getScreenY() >= 0)) {
            double diff = stage.getY();
            stage.setY(t.getScreenY() - dragAnchorY);
            diff = diff - stage.getY();
            stage.setHeight(stage.getHeight() + (diff));
            if (stage.getHeight() < 700) {
                returnHeight = stage.getHeight();
            }
            System.out.println(returnHeight);
            if ((stage.getY() + topOffset) <= 5) {
                stage.setY(-topOffset + 5);
                stage.setHeight(duHeight + topOffset + 8.5);
            } else {
                stage.setHeight(returnHeight);
            }

        }

    }

    public void topBottomReleased(MouseEvent t) {
        scene.getRoot().getStyleClass().remove("bg");
        scene.setFill(Color.TRANSPARENT);
        if ((stage.getY() + topOffset) <= 5) {

            stage.setY(-topOffset + 5);
            stage.setHeight(duHeight + topOffset + 8.5);
            topBottomDraggerProp();
        } else {
            StageHeight = stage.getHeight();
            topBottomDraggerProp();
        }
    }

    public void bottomDragged(MouseEvent t) {
        eyeHider();
        if ((stage.getHeight() >= MinHeight + topOffset + bottomOffset) || ((stage.getY() + stage.getHeight()) - t.getScreenY() <= 0)) {
            stage.setHeight(t.getSceneY() + bottomOffset);

        }
    }

    public void maximizer() {
        eyeHider();
        maximizeImage.setImage(new Image("maximize_makeSmall.png"));
        minimizeXFixer = stage.getX();
        minimizeYFixer = stage.getY();
        widthCorrection = 2;
        stage.setX(-leftOffset + 5);
        stage.setWidth(duWidth + leftOffset + rightOffset - 10);
        leftRightDraggerProp();
        stage.setY(-topOffset + 5);
        stage.setHeight(duHeight + bottomOffset + topOffset + 30);
        topBottomDraggerProp();

    }

    public void reset() {
        masterFader();
        maximizeImage.setImage(new Image("maximize_makeBig.png"));
        stage.setX(minimizeXFixer1);
        stage.setHeight(StageHeight);
        stage.setY(minimizeYFixer1);
        stage.setWidth(StageWidth);
        topBottomDraggerProp();
        leftRightDraggerProp();
        masterFader();
    }

    public void halfMaximizerLeft() {
        eyeHider();
        widthCorrection = 4;
        stage.setX(-leftOffset + 5);
        stage.setWidth(duWidth / 2 + leftOffset + rightOffset - 5);
        leftRightDraggerProp();
        stage.setY(-topOffset + 5);
        stage.setHeight(duHeight + bottomOffset + topOffset - 10);
        topBottomDraggerProp();
    }

    public void halfMaximizerRight() {
        eyeHider();
        widthCorrection = 4;
        stage.setX(duWidth / 2 - leftOffset - 5);
        stage.setWidth(duWidth / 2 + leftOffset + rightOffset);
        leftRightDraggerProp();
        stage.setY(-topOffset + 5);
        stage.setHeight(duHeight + bottomOffset + topOffset - 10);
        topBottomDraggerProp();
    }

    public void minimizer() {
        minimizeXFixer = stage.getX();
        minimizeYFixer = stage.getY();
        stage.setWidth(400);
        stage.setX(duWidth - leftOffset - 375);
        leftRightDraggerProp();
        stage.setHeight(125);
        stage.setY(duHeight - topOffset - 92);
        topBottomDraggerProp();
    }

    public void eventHandler(final Rectangle element) {
        element.setOnMousePressed(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {

                if ((element.getId() + "").equals("leftDrag") || (element.getId() + "").equals("bottomSqLeftDrag")) {
                    leftPressed(t);
                } else if ((element.getId() + "").equals("topDrag") || (element.getId() + "").equals("topSqRightDrag")) {
                    topPressed(t);
                } else if ((element.getId() + "").equals("topSqLeftDrag")) {
                    topLeftPressed(t);
                }
                if ((element.getId() + "").equals("rightDrag") || (element.getId() + "").equals("bottomDrag") || (element.getId() + "").equals("bottomSqRightDrag")) {
                    scene.getRoot().getStyleClass().add("bg");
                    masterFader();
                }

                if ((element.getId() + "").equals("topBar")) {
                    if (t.getSceneX() > stage.getWidth() * 4 / 5) {
                        dragAnchorX = t.getSceneX() - (stage.getWidth() - StageWidth) * 1.00;
                    } else if (t.getSceneX() > stage.getWidth() * 3 / 5) {
                        dragAnchorX = t.getSceneX() - (stage.getWidth() - StageWidth) * 0.76;
                    } else if (t.getSceneX() > stage.getWidth() * 2 / 5) {
                        dragAnchorX = t.getSceneX() - (stage.getWidth() - StageWidth) * 0.52;
                    } else if (t.getSceneX() > stage.getWidth() * 1 / 5) {
                        dragAnchorX = t.getSceneX() - (stage.getWidth() - StageWidth) * 0.13;
                    } else {
                        dragAnchorX = t.getSceneX();
                    }
                    dragAnchorY = t.getSceneY();

                }
            }
        });
        element.setOnMouseDragged(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if ((element.getId() + "").equals("leftDrag")) {
                    leftDragged(t);
                } else if ((element.getId() + "").equals("rightDrag")) {
                    rightDragged(t);
                } else if ((element.getId() + "").equals("topDrag")) {
                    topDragged(t);
                } else if ((element.getId() + "").equals("bottomDrag")) {
                    bottomDragged(t);
                } else if ((element.getId() + "").equals("topSqLeftDrag")) {
                    topDragged(t);
                    leftDragged(t);
                } else if ((element.getId() + "").equals("topSqRightDrag")) {
                    topDragged(t);
                    rightDragged(t);
                } else if ((element.getId() + "").equals("bottomSqLeftDrag")) {
                    bottomDragged(t);
                    leftDragged(t);
                } else if ((element.getId() + "").equals("bottomSqRightDrag")) {
                    bottomDragged(t);
                    rightDragged(t);
                } else if ((element.getId() + "").equals("topBar")) {

                    stage.setX(t.getScreenX() - dragAnchorX);
                    stage.setY(t.getScreenY() - dragAnchorY);

                    if (isMinimize == false) {
                        if ((stage.getHeight() != StageHeight) || (stage.getWidth() != StageWidth)) {
                            masterFader();
                            stage.setHeight(StageHeight);
                            stage.setWidth(StageWidth);
                            topBottomDraggerProp();
                            leftRightDraggerProp();
                            masterFader();
                        }
                    }

                    if ((stage.getY() + topOffset) <= 5) {
                        if (wasFader == false) {
                            masterFader();
                            wasFader = true;
                        }
                        scene.getRoot().getStyleClass().add("bg");
                        stage.setX(-leftOffset + 5);
                        stage.setWidth(duWidth + leftOffset + rightOffset - 10);
                        stage.setY(-topOffset + 5);
                        stage.setHeight(duHeight + bottomOffset + topOffset + 30);
                    } else if ((stage.getX() + leftOffset) <= 5) {
                        if (wasFader == false) {
                            masterFader();
                            wasFader = true;
                        }
                        scene.getRoot().getStyleClass().add("bg");
                        stage.setX(-leftOffset + 5);
                        stage.setWidth(duWidth / 2 + leftOffset + rightOffset - 5);
                        stage.setY(-topOffset + 10);
                        stage.setHeight(duHeight + bottomOffset + topOffset - 10);
                    } else if (t.getScreenX() + 250 >= duWidth) {
                        if (wasFader == false) {
                            masterFader();
                            wasFader = true;
                        }
                        scene.getRoot().getStyleClass().add("bg");
                        stage.setX(duWidth / 2 - leftOffset - 5);
                        stage.setWidth(duWidth / 2 + leftOffset + rightOffset);
                        stage.setY(-topOffset + 10);
                        stage.setHeight(duHeight + bottomOffset + topOffset - 10);
                    } else {
                        scene.getRoot().getStyleClass().remove("bg");
                        scene.setFill(Color.TRANSPARENT);
                        if (wasFader == true) {
                            masterFader();
                            wasFader = false;
                        }
                    }

                }

            }
        });
        element.setOnMouseReleased(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {

                if ((element.getId() + "").equals("leftDrag") || (element.getId() + "").equals("rightDrag")) {
                    masterFader();
                    leftRightReleased(t);
                } else if ((element.getId() + "").equals("topDrag") || (element.getId() + "").equals("bottomDrag")) {
                    masterFader();
                    topBottomReleased(t);
                } else if ((element.getId() + "").equals("topSqLeftDrag") || (element.getId() + "").equals("topSqRightDrag") || (element.getId() + "").equals("bottomSqLeftDrag") || (element.getId() + "").equals("bottomSqRightDrag")) {
                    masterFader();
                    topBottomReleased(t);
                    leftRightReleased(t);
                }
                if ((element.getId() + "").equals("topBar")) {

                    scene.getRoot().getStyleClass().remove("bg");
                    scene.setFill(Color.TRANSPARENT);
                    if (widthCorrection != 1) {
                        widthCorrection = 1;
                    }
                    if ((stage.getY() + topOffset) <= 5) {
                        masterFader();
                        wasFader = false;
                        maximizer();
                    } else if ((stage.getX() + leftOffset) <= 5) {
                        masterFader();
                        wasFader = false;
                        halfMaximizerLeft();
                    } else if (t.getScreenX() + 250 >= duWidth) {
                        masterFader();
                        wasFader = false;
                        halfMaximizerRight();
                    }

                }
            }

        });
        element.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if ((element.getId() + "").equals("topBar") || (element.getId() + "").equals("maximizeButton")) {
                    if (((t.getClickCount() == 2) && (element.getId() + "").equals("topBar")) || (element.getId() + "").equals("maximizeButton")) {
                        eyeHider();
                        if ((stage.getWidth() >= duWidth + leftOffset + rightOffset - 10) && (stage.getHeight() >= duHeight + bottomOffset + topOffset + 30)) {

                            reset();
                        } else {
                            maximizer();
                            minimizeXFixer1 = minimizeXFixer;
                            minimizeYFixer1 = minimizeYFixer;

                        }
                    }
                }
                if ((element.getId() + "").equals("minimizeButton")) {
                    minimizeButton.getStyleClass().remove("hover");
                    minimizeButton.getStyleClass().add("clicked");

                    if (isMinimize == false) {
                        see.setOpacity(100);
                        seeImage.setOpacity(100);
                        isMinimize = true;
                    } else {
                        eyeHider();
                    }
                    if (stage.getHeight() == 125 && stage.getWidth() == 400) {
                        reset();
                    } else {
                        minimizer();
                        minimizeXFixer1 = minimizeXFixer;
                        minimizeYFixer1 = minimizeYFixer;
                    }
                }
                if ((element.getId() + "").equals("closeButton")) {
                    eyeHider();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            masterFader();
                        }
                    };
                    Runnable r2 = new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(600L);
                                System.exit(0);
                            } catch (InterruptedException iex) {
                                System.err.print(iex.getMessage());
                            }
                        }
                    };
                    new Thread(r1).start();
                    new Thread(r2).start();
                }
            }
        });
        element.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if ((element.getId() + "").equals("minimizeButton") || (element.getId() + "").equals("maximizeButton") || (element.getId() + "").equals("closeButton")) {

                    element.getStyleClass().add("hover");
                }
            }
        });
        element.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if ((element.getId() + "").equals("minimizeButton") || (element.getId() + "").equals("maximizeButton") || (element.getId() + "").equals("closeButton")) {

                    element.getStyleClass().remove("hover");
                    element.getStyleClass().add("normal");
                }
            }
        });
    }

    public void eventHandler(final ImageView element) {
        element.setOnMouseEntered(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if ((element.getId() + "").equals("minimizeImage")) {
                    minimizeButton.getStyleClass().remove("clicked");
                    minimizeButton.getStyleClass().add("hover");
                } else if ((element.getId() + "").equals("maximizeImage")) {
                    maximizeButton.getStyleClass().remove("clicked");
                    maximizeButton.getStyleClass().add("hover");
                } else if ((element.getId() + "").equals("closeImage")) {
                    closeButton.getStyleClass().remove("clicked");
                    closeButton.getStyleClass().add("hover");
                }
            }
        });
        element.setOnMouseExited(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if ((element.getId() + "").equals("minimizeImage")) {
                    minimizeButton.getStyleClass().remove("clicked");
                    minimizeButton.getStyleClass().remove("hover");
                    minimizeButton.getStyleClass().add("normal");
                }
                if ((element.getId() + "").equals("maximizeImage")) {
                    maximizeButton.getStyleClass().remove("clicked");
                    maximizeButton.getStyleClass().remove("hover");
                    maximizeButton.getStyleClass().add("normal");
                }
                if ((element.getId() + "").equals("closeImage")) {
                    closeButton.getStyleClass().remove("clicked");
                    closeButton.getStyleClass().remove("hover");
                    closeButton.getStyleClass().add("normal");
                }
            }
        });
        element.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if ((element.getId() + "").equals("maximizeImage")) {
                    eyeHider();
                    if ((stage.getWidth() >= duWidth + leftOffset + rightOffset - 10) && (stage.getHeight() >= duHeight + bottomOffset + topOffset + 30)) {
                        reset();
                    } else {
                        maximizer();
                        minimizeXFixer1 = minimizeXFixer;
                        minimizeYFixer1 = minimizeYFixer;
                    }
                }
                if ((element.getId() + "").equals("minimizeImage")) {
                    element.getStyleClass().remove("hover");

                    if (isMinimize == false) {
                        see.setOpacity(1);
                        seeImage.setOpacity(1);
                        isMinimize = true;
                    } else {
                        eyeHider();
                    }
                    if (stage.getHeight() == 125 && stage.getWidth() == 400) {
                        reset();
                    } else {
                        minimizer();
                        minimizeXFixer1 = minimizeXFixer;
                        minimizeYFixer1 = minimizeYFixer;
                    }
                }
                if ((element.getId() + "").equals("closeImage")) {
                    eyeHider();
                    Runnable r1 = new Runnable() {
                        public void run() {
                            masterFader();
                        }
                    };
                    Runnable r2 = new Runnable() {
                        public void run() {
                            try {
                                Thread.sleep(600L);
                                System.exit(0);
                            } catch (InterruptedException iex) {
                                System.err.print(iex.getMessage());
                            }
                        }
                    };
                    new Thread(r1).start();
                    new Thread(r2).start();
                }
                if ((element.getId()).equals("seeImage")) {
                    if (isFader == true) {
                        seeImage.setImage(new Image("unhide.png"));
                        masterFader();
                    } else {
                        seeImage.setImage(new Image("hide.png"));
                        masterFader();
                    }
                }
            }
        });
    }

    public void eventHandler(final Ellipse element) {
        element.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent t) {
                if ((element.getId() + "").equals("see")) {
                    if (isFader == true) {
                        seeImage.setImage(new Image("unhide.png"));
                        masterFader();

                    } else {
                        seeImage.setImage(new Image("hide.png"));
                        masterFader();
                    }
                }

            }
        });
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    public void StageConfigAnimator(Property p, double changeFactor, double bounceCorrection) {
        final Timeline timeline = new Timeline();
        final KeyValue Movement1 = new KeyValue(p, changeFactor + bounceMeter + bounceCorrection);
        final KeyFrame Movement1KeyFrame = new KeyFrame(Duration.millis(timer), Movement1);
        final KeyValue Movement2 = new KeyValue(p, changeFactor);
        final KeyFrame Movement2KeyFrame = new KeyFrame(Duration.millis(timer + bounceDelay), Movement2);
        timeline.getKeyFrames().add(Movement1KeyFrame);
        timeline.getKeyFrames().add(Movement2KeyFrame);
        timeline.play();
    }

    public void Fader(Rectangle element) {
        FadeTransition ft = new FadeTransition(Duration.millis(fadeTimer));
        ft.setNode(element);
        if (isFader == false) {
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
        } else {
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
        }
        ft.play();

    }

    public void Fader(ImageView element) {
        FadeTransition ft = new FadeTransition(Duration.millis(fadeTimer));
        ft.setNode(element);
        if (isFader == false) {
            ft.setFromValue(0.0);
            ft.setToValue(1.0);
        } else {
            ft.setFromValue(1.0);
            ft.setToValue(0.0);
        }
        ft.play();

    }

    public void topBottomDraggerProp() {

        StageConfigAnimator(topBar.yProperty(), 0, 0);
        StageConfigAnimator(bottomBar.yProperty(), (stage.getHeight() + bottomBar.getHeight()) - (topOffset + bottomOffset + HeightDragCorrection), 5);
        StageConfigAnimator(leftBar.yProperty(), 0, 0);
        StageConfigAnimator(rightBar.yProperty(), 0, 0);
        StageConfigAnimator(filler.yProperty(), 0, 0);

        StageConfigAnimator(leftBar.heightProperty(), ((stage.getHeight()) - (topOffset + bottomOffset + bottomBar.getHeight() + topBar.getHeight() + spaceing + spaceing)), 0);
        StageConfigAnimator(rightBar.heightProperty(), ((stage.getHeight()) - (topOffset + bottomOffset + bottomBar.getHeight() + topBar.getHeight() + spaceing + spaceing)), 0);
        StageConfigAnimator(filler.heightProperty(), ((stage.getHeight()) - (topOffset + bottomOffset + 5)), 0);

        StageConfigAnimator(topDrag.yProperty(), 0, 0);
        StageConfigAnimator(bottomDrag.yProperty(), (stage.getHeight() + bottomBar.getHeight()) - (topOffset + bottomOffset + HeightDragCorrection), 5);
        StageConfigAnimator(leftDrag.yProperty(), 0, 0);
        StageConfigAnimator(rightDrag.yProperty(), 0, 0);

        StageConfigAnimator(leftDrag.heightProperty(), ((stage.getHeight()) - (topOffset + bottomOffset)), 0);
        StageConfigAnimator(rightDrag.heightProperty(), ((stage.getHeight()) - (topOffset + bottomOffset)), 0);

        StageConfigAnimator(topSqLeftDrag.yProperty(), 0, 0);
        StageConfigAnimator(bottomSqLeftDrag.yProperty(), (stage.getHeight() + bottomBar.getHeight()) - (topOffset + bottomOffset + HeightDragCorrection), 5);
        StageConfigAnimator(bottomSqRightDrag.yProperty(), (stage.getHeight() + bottomBar.getHeight()) - (topOffset + bottomOffset + HeightDragCorrection), 5);
        StageConfigAnimator(topSqRightDrag.yProperty(), 0, 0);

        StageConfigAnimator(closeButton.yProperty(), 0, -2.5);
        StageConfigAnimator(closeImage.yProperty(), 0, -2.5);

        StageConfigAnimator(see.layoutYProperty(), (60 + topOffset), -2.5);
        StageConfigAnimator(seeImage.yProperty(), 0, -2.5);

        StageConfigAnimator(maximizeButton.yProperty(), 0, -2.5);
        StageConfigAnimator(maximizeImage.yProperty(), 0, -2.5);

        StageConfigAnimator(minimizeButton.yProperty(), 0, -2.5);
        StageConfigAnimator(minimizeImage.yProperty(), 0, -2.5);

        StageConfigAnimator(titleImage.yProperty(), 0, -2.5);

    }

    public void leftRightDraggerProp() {

        StageConfigAnimator(topBar.xProperty(), 0, 0);
        StageConfigAnimator(topBar.widthProperty(), ((stage.getWidth()) - (rightOffset + leftOffset)), 0);

        StageConfigAnimator(bottomBar.xProperty(), 0, 0);
        StageConfigAnimator(bottomBar.widthProperty(), ((stage.getWidth()) - (rightOffset + leftOffset)), 0);

        StageConfigAnimator(leftBar.xProperty(), 0, 0);

        StageConfigAnimator(rightBar.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), 5);

        StageConfigAnimator(filler.xProperty(), 0, 0);
        StageConfigAnimator(filler.widthProperty(), ((stage.getWidth() + 5) - (rightOffset + leftOffset)), 0);

        StageConfigAnimator(topDrag.xProperty(), 0, 0);
        StageConfigAnimator(topDrag.widthProperty(), ((stage.getWidth()) - (rightOffset + leftOffset)), 0);

        StageConfigAnimator(bottomDrag.xProperty(), 0, 0);
        StageConfigAnimator(bottomDrag.widthProperty(), ((stage.getWidth()) - (rightOffset + leftOffset)), 0);

        StageConfigAnimator(leftDrag.xProperty(), 0, 0);

        StageConfigAnimator(rightDrag.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), 5);

        StageConfigAnimator(topSqLeftDrag.xProperty(), 0, 0);

        StageConfigAnimator(bottomSqLeftDrag.xProperty(), 0, 0);

        StageConfigAnimator(bottomSqRightDrag.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), 5);

        StageConfigAnimator(topSqRightDrag.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), 5);

        StageConfigAnimator(closeButton.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), -7.5);
        StageConfigAnimator(closeImage.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), -7.5);

        StageConfigAnimator(see.layoutXProperty(), ((stage.getWidth() - rightOffset)), -7.5);
        StageConfigAnimator(seeImage.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), -7.5);

        StageConfigAnimator(maximizeButton.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), -7.5);
        StageConfigAnimator(maximizeImage.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), -7.5);

        StageConfigAnimator(minimizeButton.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), -7.5);
        StageConfigAnimator(minimizeImage.xProperty(), ((stage.getWidth() + rightBar.getWidth()) - (rightOffset + leftOffset + WidthDragCorrection)), -7.5);

        StageConfigAnimator(titleImage.xProperty(), 0, -2.5);

    }

    public void masterFader() {
        Fader(filler);
        Fader(topBar);
        Fader(bottomBar);
        Fader(rightBar);
        Fader(leftBar);
        Fader(minimizeButton);
        Fader(minimizeImage);
        Fader(maximizeButton);
        Fader(maximizeImage);
        Fader(closeButton);
        Fader(closeImage);
        Fader(titleImage);
        if (isFader == false) {
            isFader = true;
        } else {
            isFader = false;
        }
    }

    public void eyeHider() {
        if (isMinimize == true) {
            see.setOpacity(0);
            seeImage.setOpacity(0);
            isMinimize = false;
        }
    }
}
