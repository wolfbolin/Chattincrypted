package windows;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * Created by James Bamboo on 2016/12/26.
 */
public class WindowsLogin {
    private Stage stageLogin = new Stage();
    private VBox vBox = new VBox(15);
    private Pane root = new Pane(vBox);
    private ImageView ivBanner = new ImageView("images/loginTop.png");
    //private ImageView ivHeadIcon = new ImageView("images/headIcon.png");
    private ImageView ivMinusBt = new ImageView("images/minus_white.png");
    private ImageView ivCloseBt = new ImageView("images/close_white.png");
    private TextField tfAccountInput = new TextField();
    //private PasswordField pfPasswordInput = new PasswordField();
    //private CheckBox cbxRememberPassword = new CheckBox("记住密码");
    //private CheckBox cbxAutoLogin = new CheckBox("自动登录");
    //private Text txtRegister = new Text("注册账号");
    //private Text txtFindPassword = new Text("找回密码");
    private Button btFind = new Button("查   找");

    private double xOffset;
    private double yOffset;

    private ImageView testImage = new ImageView("images/loginTop.png");
    
    private void test(ImageView Image, double width,double height) {
    	//ImageView testImage = new ImageView("images/close_white.png");
    	Image.setFitWidth(width);
    	Image.setFitHeight(height);
    	//return testImage;
    }
    
    /** 初始化最小化按钮与关闭按钮 */
    private void InitTop() {
        ivMinusBt.setX(490);
        ivMinusBt.setY(10);
        ivCloseBt.setX(520);
        ivCloseBt.setY(10);
        ivMinusBt.setOnMouseClicked(e -> {
            stageLogin.setIconified(true);
        });
        ivCloseBt.setOnMouseClicked(e -> {
            stageLogin.close();
            System.exit(0);
        });
    }

    /** 初始化Banner图片 */
    private void InitBanner() {
        ivBanner.setFitWidth(540);
        ivBanner.setFitHeight(180);
        ivBanner.setOnMousePressed(e -> {
            e.consume();
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        ivBanner.setOnMouseDragged(e -> {
            e.consume();
            stageLogin.setX(e.getScreenX() - xOffset);
            if (e.getScreenY() - yOffset < 0) {
                stageLogin.setY(0);
            } else {
                stageLogin.setY(e.getScreenY() - yOffset);
            }
        });
    }

    /** 初始化头像 */
    /*private void InitHeadIcon() {
        ivHeadIcon.setFitWidth(84);
        ivHeadIcon.setFitHeight(84);
    }*/


    /** 初始化输入区域 */
    private void InitInputArea() {
        tfAccountInput.setText("1142696500");
        tfAccountInput.setPrefHeight(28);
        tfAccountInput.setPrefWidth(190);
        /*pfPasswordInput.setText("wangyuchawo1k5!");
        pfPasswordInput.setPrefHeight(28);
        pfPasswordInput.setPrefWidth(190);*/
        //cbxRememberPassword.setSelected(true);
        //cbxAutoLogin.setSelected(true);
    }

    /** 初始化右侧文本 */
    /*private void InitRightText() {
        txtRegister.setWrappingWidth(60);
        txtRegister.setStyle("-fx-text-fill: #0099FF;");
        txtRegister.setFill(Color.valueOf("#0099FF"));
        txtFindPassword.setWrappingWidth(60);
        txtFindPassword.setFill(Color.valueOf("#0099FF"));
    }*/

    /** 初始化查询按钮 */
    private void InitButtonFind() {
        btFind.setStyle("-fx-base: #0099FF; -fx-text-fill: white;");
        btFind.setPrefSize(80, 30);
    }

    /** 初始化面板 */
    private void InitPane() {

        HBox inputPane = new HBox(15);
        GridPane textPane = new GridPane();
        //HBox midPane = new HBox(10);
        //HBox cbxPane = new HBox(50);

        vBox.getChildren().addAll(ivBanner, inputPane, testImage);
        root.getChildren().addAll(ivMinusBt, ivCloseBt);
        inputPane.getChildren().addAll(textPane, btFind);
        //midPane.getChildren().addAll(textPane, btFind);
        //cbxPane.getChildren().addAll(cbxRememberPassword, cbxAutoLogin);
        textPane.add(tfAccountInput, 0, 0);
        //textPane.add(txtRegister, 1, 0);
        //textPane.add(pfPasswordInput, 0, 1);
        //textPane.add(txtFindPassword, 1, 1);

        textPane.setHgap(15);
        vBox.setAlignment(Pos.TOP_CENTER);
        inputPane.setAlignment(Pos.TOP_CENTER);

        Scene scene = new Scene(root, 540, 440);
        stageLogin.setScene(scene);
        stageLogin.initStyle(StageStyle.UNDECORATED);//设定窗口无边框
        // stageLogin.setAlwaysOnTop(true);
        stageLogin.setTitle("Chat Login");
        stageLogin.show();
    }

    /** 初始化焦点 */
    private void InitFocus() {
        btFind.requestFocus();
    }

    /** 获得插入按钮的控制权限 */
    public Button getBtFind() {
        return btFind;
    }

    /** 获得Stage的控制权限 */
    public Stage getStage() {
        return stageLogin;
    }

    /** 显示页面 */
    public void show() {
        InitTop();
        InitBanner();
        test(testImage, 540, 180);
        //InitHeadIcon();
        InitInputArea();
        //InitRightText();
        InitButtonFind();
        InitPane();
        InitFocus();
    }
}
