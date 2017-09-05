package windows;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import util.mFriend;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by James Bamboo on 2016/12/26.
 */
public class WindowsChat {
    private Stage stage = new Stage();
    private GridPane root = new GridPane();

    private ImageView ivLSD = new ImageView("images/LSD.jpg");
    private ImageView ivLZQ = new ImageView("images/LZQ.jpg");
    private ImageView ivXYM = new ImageView("images/XYM.jpg");
    private ImageView ivZW= new ImageView("images/ZW.jpg");
    private ImageView ivFXS = new ImageView("images/FXS.jpg");
    private ImageView ivBMCD = new ImageView("images/BMCD.jpg");
    private ImageView ivCR = new ImageView("images/CR.jpg");
    private ImageView ivFiv = new ImageView("images/fifthClass.jpg");
    private ImageView ivTopBt = new ImageView("images/chatTopBt.png");
    private ImageView ivChatShow = new ImageView("images/chatShow.png");
    private ImageView ivTopBtG = new ImageView("images/topGroupBt.png");
    private ImageView ivChatShowG = new ImageView("images/rightTab.png");
    private ImageView ivControl = new ImageView("images/chatControl.png");
    private ImageView ivTop = null;
    private ImageView ivRight = null;

    private Button btClose = new Button("关闭(C)");
    private Button btSend = new Button("发送(S)");

    private TextArea taOut = new TextArea();
    private TextArea taIn = new TextArea();

    private double xOffset;
    private double yOffset;

    public WindowsChat() {
    }

    public WindowsChat(mFriend friend) {
        InitTop(friend);
        InitBottom(friend);
        InitPane(friend);
    }

    private void InitTop(mFriend friend) {
        VBox topVBox = new VBox(5);
        VBox vbName = new VBox(3);
        HBox hbName = new HBox(6);
        Text txtRemark = new Text();
        Text txtSign = new Text();
        ImageView iv = null;

        txtRemark.setText(friend.getRemark());
        txtRemark.setFont(Font.font("Microsoft YaHei", 16));
        txtSign.setText(friend.getSign());
        txtSign.setFont(Font.font("Microsoft YaHei", 12));

        if (friend.getRemark().equals("刘绍栋")) iv = ivLSD;
        if (friend.getRemark().equals("罗昭钦")) iv = ivLZQ;
        if (friend.getRemark().equals("徐一明")) iv = ivXYM;
        if (friend.getRemark().equals("张炜")) iv = ivZW;
        if (friend.getRemark().equals("樊相帅")) iv = ivFXS;
        if (friend.getRemark().equals("比木赤达")) iv = ivBMCD;
        if (friend.getRemark().equals("陈榕")) iv = ivCR;
        if (friend.getName().equals("吾心安处即五班")) {
            txtRemark.setText(friend.getName());
            iv = ivFiv;
        }
        iv.setFitWidth(42);
        iv.setFitHeight(42);
        if (friend.isGroup()) ivTop = ivTopBtG;
        else ivTop = ivTopBt;

        hbName.setPadding(new Insets(3, 0, 0, 6));
        vbName.setAlignment(Pos.CENTER_LEFT);
        vbName.getChildren().addAll(txtRemark, txtSign);
        hbName.getChildren().addAll(iv, vbName);
        topVBox.getChildren().addAll(hbName, ivTop);

        root.add(topVBox, 0, 0);

        ImageView ivMinusBt = new ImageView("images/minus_gray.png");
        ImageView ivCloseBt = new ImageView("images/close_gray.png");
        HBox hbWinControl = new HBox(15);
        StackPane spMin = new StackPane(ivMinusBt);
        StackPane spClose= new StackPane(ivCloseBt);
        hbWinControl.setPadding(new Insets(0, 0, 55, 90));
        hbWinControl.getChildren().addAll(spMin, spClose);
        spMin.setOnMouseClicked(e -> {
            stage.setIconified(true);
        });
        spClose.setOnMouseClicked(e -> {
            stage.close();
        });

        root.add(hbWinControl, 1, 0);
    }

    private void InitBottom(mFriend friend) {
        VBox vbInput = new VBox();
        HBox hbButton = new HBox(5);
        Text adv = new Text();
        taOut.setEditable(false);
        taOut.setPrefHeight(280);
        taOut.setWrapText(true);
        taIn.setPrefHeight(80);
        taIn.setWrapText(true);
        btClose.setStyle("-fx-base: #2B2B2B;");
        btClose.setPrefSize(80, 25);
        btSend.setStyle("-fx-base: #2B2B2B;");
        btSend.setPrefSize(80, 25);

        adv.setText("[广告]JavaFX版QQ—Chat强势登场");
        adv.setFill(Color.valueOf("#696969"));
        adv.setFont(Font.font(12));
        adv.setWrappingWidth(220);



        hbButton.setPadding(new Insets(5, 0, 0, 0));
        hbButton.setAlignment(Pos.CENTER_RIGHT);
        hbButton.getChildren().addAll(adv, btClose, btSend);

        vbInput.getChildren().addAll(taOut, ivControl, taIn, hbButton);

        root.add(vbInput, 0, 1);
        if (friend.isGroup()) ivRight = ivChatShowG;
        else ivRight = ivChatShow;
        root.add(ivRight, 1, 1);

        SimpleDateFormat df = new SimpleDateFormat("  yyyy/MM/dd HH:mm:ss\n");//设置日期格式
        btSend.setOnAction(e -> {
            if (!taIn.getText().equals("")) {
                String time = df.format(new Date());
                Text txt = new Text("\n邦布" + time);
                txt.setFill(Color.GREEN);
                taOut.appendText(txt.getText() + "  " + taIn.getText() + "\n");
                taIn.clear();
            }
        });
    }

    private void InitPane(mFriend friend) {
        Scene scene = new Scene(root, 530, 505);
        root.setStyle("-fx-background-color: #e9ecf8;");
        stage.setScene(scene);
        stage.setTitle(friend.getRemark());
        stage.initStyle(StageStyle.UNDECORATED);
        if (friend.isGroup())
            stage.setTitle(friend.getName());
        root.setOnMousePressed(e -> {
            e.consume();
            xOffset = e.getSceneX();
            yOffset = e.getSceneY();
        });
        root.setOnMouseDragged(e -> {
            e.consume();
            stage.setX(e.getScreenX() - xOffset);
            if (e.getScreenY() - yOffset < 0) {
                stage.setY(0);
            } else {
                stage.setY(e.getScreenY() - yOffset);
            }
        });
        btClose.setOnAction(e -> {
            stage.close();
        });
    }

    public void show() {
        stage.show();
    }

    public boolean isShowing() {
        return stage.isShowing();
    }

    public void requestFocus() {
        stage.requestFocus();
    }
}
