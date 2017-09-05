package windows;

import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.Define;
import util.mFriend;

/**
 * Created by James Bamboo on 2016/12/26.
 */
public class WindowsMain extends Application {
    private WindowsLogin wLogin = new WindowsLogin();

    private VBox root = new VBox();
    private HBox hbLogo = new HBox();
    private HBox hbHeadIcon = new HBox(5);
    private GridPane gpDetail = new GridPane();
    private VBox hbName = new VBox(1);
    private HBox hbTopBtLeft = new HBox(5);
    private HBox hbTopBtRight = new HBox(5);

    private HBox hbTab = new HBox();
    private ScrollPane spTabBasic = new ScrollPane();
    private VBox vbContacts = new VBox();
    private VBox vbGroup = new VBox();
    private VBox vbChat = new VBox();
    private HBox hbBottom = new HBox();

    private ImageView ivLogo = new ImageView("images/logo.png");
    private ImageView ivHeadIcon = new ImageView("images/headIcon.png");
    private ImageView ivTick = new ImageView("images/tick.png");
    private ImageView ivZone = new ImageView("images/zone.png");
    private ImageView ivMail = new ImageView("images/mail.png");
    private ImageView ivShop = new ImageView("images/shoppingCar.png");
    private ImageView ivSkin = new ImageView("images/skin.png");
    private ImageView ivMsg = new ImageView("images/messageBox.png");
    private ImageView ivWeather = new ImageView("images/sun.png");
    private ImageView ivContacts = new ImageView("images/contacts.png");
    private ImageView ivGroup = new ImageView("images/group.png");
    private ImageView ivChat = new ImageView("images/chat.png");
    private ImageView ivContactsMove = new ImageView("images/contactsMove.png");
    private ImageView ivGroupMove = new ImageView("images/groupMove.png");
    private ImageView ivChatMove = new ImageView("images/chatMove.png");
    private ImageView ivContactsSelected = new ImageView("images/contactsSelected.png");
    private ImageView ivGroupSelected = new ImageView("images/groupSelected.png");
    private ImageView ivChatSelected = new ImageView("images/chatSelected.png");
    private ImageView ivButtomBt = new ImageView("images/bottomBt.png");

    private Label lbTop = new Label();
    private Label lbName = new Label();
    private Text txtSign = new Text();
    private TextField tfSearch= new TextField();
    private ListView<Node> lvContacts = new ListView<>();
    private ListView<Node> lvGroup = new ListView<>();
    private ListView<Node> lvChat = new ListView<>();

    private mFriend frdLSD = new mFriend();
    private mFriend frdLSD2 = new mFriend();
    private mFriend frdLZQ = new mFriend();
    private mFriend frdXYM = new mFriend();
    private mFriend frdZW = new mFriend();
    private mFriend frdFXS = new mFriend();
    private mFriend frdBMCD = new mFriend();
    private mFriend frdCR = new mFriend();
    private mFriend frdFIV = new mFriend();

    private double xOffset;
    private double yOffset;

    /** TODO: 初始化标题、最小化按钮与最大化按钮 */
    private void InitTop(Stage primaryStage) {
        ImageView ivMinusBt = new ImageView("images/minus_white.png");
        ImageView ivCloseBt = new ImageView("images/close_white.png");
        HBox hbWinControl = new HBox(10);

        ivLogo.setFitWidth(18);
        ivLogo.setFitHeight(18);
        lbTop.setText("Chat");
        lbTop.setFont(Font.font("Microsoft YaHei", 14));
        lbTop.setTextFill(Color.WHITE);
        lbTop.setGraphic(ivLogo);
        lbTop.setGraphicTextGap(3);
        hbLogo.getChildren().addAll(lbTop, hbWinControl);
        hbLogo.setPadding(new Insets(3, 0, 0, 5));

        StackPane spMin = new StackPane(ivMinusBt);
        StackPane spClose= new StackPane(ivCloseBt);
        hbWinControl.setPadding(new Insets(0, 0, 0, 180));
        hbWinControl.getChildren().addAll(spMin, spClose);
        spMin.setOnMouseClicked(e -> {
            primaryStage.setIconified(true);
        });
        spClose.setOnMouseClicked(e -> {
            primaryStage.close();
            System.exit(0);
        });
    }

    /** TODO: 初始化用户信息 */
    private void InitUserInfo() {
        lbName.setText("邦布");
        lbName.setGraphic(ivTick);
        lbName.setTextFill(Color.WHITE);
        lbName.setFont(Font.font("Microsoft YaHei", 16));
        lbName.setContentDisplay(ContentDisplay.RIGHT);
        //People change, things go wrong, shit happens, but life goes on.
        txtSign.setText("People change, things...");
        txtSign.setFill(Color.WHITE);
        txtSign.setFont(Font.font("Microsoft YaHei", 12));
        ivHeadIcon.setFitWidth(60);
        ivHeadIcon.setFitHeight(60);

        gpDetail.setHgap(15);
        gpDetail.add(hbName, 0, 0);
        gpDetail.add(ivWeather, 1, 0);
        gpDetail.add(hbTopBtLeft, 0, 1);
        gpDetail.add(hbTopBtRight, 1, 1);
        hbName.getChildren().addAll(lbName, txtSign);
        hbTopBtLeft.getChildren().addAll(ivZone, ivMail, ivShop);
        hbTopBtRight.getChildren().addAll(ivSkin, ivMsg);
        hbHeadIcon.getChildren().addAll(ivHeadIcon, gpDetail);
        hbHeadIcon.setPadding(new Insets(20, 13, 0, 10));
    }

    /** TODO: 初始化搜索栏 */
    private void InitSearch() {
        tfSearch.setText("搜索 : 联系人、多人聊天、群、企业");
        tfSearch.setStyle("-fx-background-color: #2B2B2B; -fx-text-fill: #D5D5D5;");
        tfSearch.setPrefHeight(40);
        tfSearch.setOnMouseClicked(e -> {
            tfSearch.setText("");
        });
        tfSearch.setOnMouseExited(e -> {
            tfSearch.setText("搜索 : 联系人、多人聊天、群、企业");
        });
    }

    /** TODO: 清空三个StackPane */
    private void SPClear(StackPane p1, StackPane p2, StackPane p3) {
        p1.getChildren().clear();
        p2.getChildren().clear();
        p3.getChildren().clear();
        p1.getChildren().add(ivContacts);
        p2.getChildren().add(ivGroup);
        p3.getChildren().add(ivChat);
    }

    /** TODO: 初始化横向ListView */
    private void InitTab() {
        StackPane p1 = new StackPane();
        StackPane p2 = new StackPane();
        StackPane p3 = new StackPane();
        p1.getChildren().add(ivContactsSelected);
        p2.getChildren().add(ivGroup);
        p3.getChildren().add(ivChat);
        p1.setPrefWidth(90);
        p2.setPrefWidth(90);
        p3.setPrefWidth(90);
        hbTab.setPrefHeight(36);
        hbTab.setAlignment(Pos.CENTER);
        hbTab.getChildren().addAll(p1, p2, p3);
        spTabBasic.setContent(vbContacts);


        spTabBasic.setMaxHeight(320);
        spTabBasic.setMinHeight(320);
        spTabBasic.setMaxWidth(280);
        spTabBasic.setMinWidth(280);
        spTabBasic.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        spTabBasic.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        hbTab.setStyle("-fx-background-color: #ECECEC;");
        p1.setOnMouseClicked(e -> {
            SPClear(p1, p2, p3);
            p1.getChildren().clear();
            p1.getChildren().add(ivContactsSelected);
            spTabBasic.setContent(vbContacts);
        });
        p2.setOnMouseClicked(e -> {
            SPClear(p1, p2, p3);
            p2.getChildren().clear();
            p2.getChildren().add(ivGroupSelected);
            spTabBasic.setContent(vbGroup);
        });
        p3.setOnMouseClicked(e -> {
            SPClear(p1, p2, p3);
            p3.getChildren().clear();
            p3.getChildren().add(ivChatSelected);
            spTabBasic.setContent(vbChat);
        });
    }

    /** 鼠标点击打开聊天窗口事件 */
    private void openWindow(MouseEvent e, WindowsChat wc) {
        if (e.getClickCount() >= 2) {
            if (!wc.isShowing()) wc.show();
            else wc.requestFocus();
        }
    }

    /** TODO: 初始化联系人列表 */
    private void InitTabContacts() {
        frdLSD.isLSD();
        frdLZQ.isLZQ();
        frdXYM.isXYM();
        frdZW.isZW();
        frdFXS.isFXS();
        frdBMCD.isBMCD();
        frdCR.isCR();

        Node nodeLSD = frdLSD.getFriendBig();
        Node nodeLZQ = frdLZQ.getFriendBig();
        Node nodeXYM = frdXYM.getFriendBig();
        Node nodeZW = frdZW.getFriendBig();
        Node nodeFXS = frdFXS.getFriendBig();
        Node nodeBMCD = frdBMCD.getFriendBig();
        Node nodeCR = frdCR.getFriendBig();

        WindowsChat winLSD = new WindowsChat(frdLSD);
        WindowsChat winLZQ = new WindowsChat(frdLZQ);
        WindowsChat winXYM = new WindowsChat(frdXYM);
        WindowsChat winZW = new WindowsChat(frdZW);
        WindowsChat winFXS = new WindowsChat(frdFXS);
        WindowsChat winBMCD = new WindowsChat(frdBMCD);
        WindowsChat winCR = new WindowsChat(frdCR);

        vbContacts.setPrefWidth(280);
        lvContacts.setPrefHeight(318);
        lvContacts.getItems().addAll(nodeLSD, nodeLZQ, nodeXYM,
                nodeZW, nodeFXS, nodeBMCD, nodeCR);
        vbContacts.getChildren().addAll(lvContacts);

        nodeLSD.setOnMouseClicked(e -> { openWindow(e, winLSD); });
        nodeLZQ.setOnMouseClicked(e -> { openWindow(e, winLZQ); });
        nodeXYM.setOnMouseClicked(e -> { openWindow(e, winXYM); });
        nodeZW.setOnMouseClicked(e -> { openWindow(e, winZW); });
        nodeFXS.setOnMouseClicked(e -> { openWindow(e, winFXS); });
        nodeBMCD.setOnMouseClicked(e -> { openWindow(e, winBMCD); });
        nodeCR.setOnMouseClicked(e -> { openWindow(e, winCR); });
    }

    /** TODO: 初始化群聊列表 */
    private void InitTabGroup() {
        frdFIV.is5Clss();
        frdFIV.setGroup(true);
        Node nodeFIV = frdFIV.getFriendBig();
        WindowsChatGroup winFIV = new WindowsChatGroup(frdFIV);

        vbGroup.setPrefWidth(280);
        lvGroup.setPrefHeight(318);
        lvGroup.getItems().add(nodeFIV);
        vbGroup.getChildren().add(lvGroup);

        nodeFIV.setOnMouseClicked(e -> { openWindow(e, winFIV); });
    }

    /** TODO: 初始化会话列表 */
    private void InitTabChat() {
        frdLSD2.isLSD();
        frdLSD2.setSign("你头像丑啊！！！");
        Node nodeLSD2 = frdLSD2.getFriendBig();
        WindowsChat winLSD2 = new WindowsChat(frdLSD2);
        lvChat.getItems().add(nodeLSD2);
        vbChat.getChildren().add(lvChat);
        vbChat.setPrefWidth(280);
        lvChat.setPrefHeight(318);

        nodeLSD2.setOnMouseClicked(e -> { openWindow(e, winLSD2); });
    }

    /** TODO: 初始化底部按钮 */
    private void InitBottomButton() {
        hbBottom.setStyle("-fx-background-color: #d1d1d1;");
        hbBottom.setAlignment(Pos.BOTTOM_LEFT);
        hbBottom.setMinWidth(280);
        hbBottom.setMaxWidth(280);
        hbBottom.setMinHeight(50);
        hbBottom.setMaxHeight(50);
        hbBottom.getChildren().add(ivButtomBt);
    }

    /** TODO: 初始化面板 */
    private void InitPane(Stage primaryStage) {
        root.getChildren().addAll(hbLogo, hbHeadIcon, tfSearch, hbTab, spTabBasic, hbBottom);
        root.setStyle("-fx-background-color: #2B2B2B;");
        Scene scene = new Scene(root, Define.MAIN_MIN_WIDTH, Define.MAIN_MIN_HEIGHT);
        primaryStage.setScene(scene);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        // primaryStage.setAlwaysOnTop(true);
        wLogin.show();
        wLogin.getBtLogin().setOnAction(e -> {
            primaryStage.show();
            wLogin.getStage().close();
        });
        root.setOnMousePressed(e -> {
            e.consume();
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged(e -> {
            e.consume();
            primaryStage.setX(e.getScreenX() - xOffset);
            if (e.getScreenY() - yOffset < 0) {
                primaryStage.setY(0);
            } else {
                primaryStage.setY(e.getScreenY() - yOffset);
            }
        });
        primaryStage.setOnCloseRequest(e -> {
            primaryStage.close();
            System.exit(0);
        });
        primaryStage.setTitle("Chat");
    }

    /** TODO: Start at here */
    @Override
    public void start(Stage primaryStage) {
        InitTop(primaryStage);
        InitUserInfo();
        InitSearch();
        InitTab();
        InitTabContacts();
        InitTabGroup();
        InitTabChat();
        InitBottomButton();
        InitPane(primaryStage);
    }

    public static void main(String[] args) {
        Application.launch();
    }
}
