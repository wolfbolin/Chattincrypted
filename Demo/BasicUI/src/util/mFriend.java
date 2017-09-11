package util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

/**
 * Created by James Bamboo on 2016/12/29.
 */
public class mFriend {
    private ImageView ivLSD = new ImageView("images/LSD.jpg");
    private ImageView ivLZQ = new ImageView("images/LZQ.jpg");
    private ImageView ivXYM = new ImageView("images/XYM.jpg");
    private ImageView ivZW= new ImageView("images/ZW.jpg");
    private ImageView ivFXS = new ImageView("images/FXS.jpg");
    private ImageView ivBMCD = new ImageView("images/BMCD.jpg");
    private ImageView ivCR = new ImageView("images/CR.jpg");
    private ImageView ivFiv = new ImageView("images/fifthClass.jpg");

    private boolean group;
    private String name;
    private String sign;
    private String remark;
    private ImageView headIcon = null;


    /** 创建一个不包含任何信息的好友 */
    public mFriend() {

    }

    /** 创建一个只有用户名的好友 */
    public mFriend(String name) {

    }

    /** 创建一个有用户名和个性签名的好友 */
    public mFriend(String name, String sign) {

    }

    /**创建一个有用户名、个性签名和头像的好友 */
    public mFriend(String name, String sign, ImageView headIcon) {

    }

    /** 该好友是LSD */
    public void isLSD() {
        name = "¥";
        remark = "刘绍栋";
        sign = "且行且珍惜";
        headIcon = ivLSD;
    }

    /** 该好友是LZQ */
    public void isLZQ() {
        name = "矢志不☀ 渝";
        remark = "罗昭钦";
        sign = "玩提莫的冬瓜皮";
        headIcon = ivLZQ;
    }

    /** 该好友是XYM */
    public void isXYM() {
        name = "轨迹";
        remark = "徐一明";
        sign = " ";
        headIcon = ivXYM;
    }

    /** 该好友是ZW */
    public void isZW() {
        name = "Jake";
        remark = "张炜";
        sign = "南国的雨，沾湿了人的思绪，让我难过这春风化雨，欲拒还羞";
        headIcon = ivZW;
    }

    /** 该好友是FXS */
    public void isFXS() {
        name = "Aragorn II";
        remark = "樊相帅";
        sign = "真金不一定闪闪发光，并非浪子都迷失方向";
        headIcon = ivFXS;
    }

    /** 该好友是BMCD */
    public void isBMCD() {
        name = "miss、彝风梦忆";
        remark = "比木赤达";
        sign = "他和她的故事……";
        headIcon = ivBMCD;
    }

    /** 该好友是CR */
    public void isCR() {
        name = "白云青山花草香";
        remark = "陈榕";
        sign = "加油，你是最胖的。";
        headIcon = ivCR;
    }

    /** 该好友是5班班群 */
    public void is5Clss() {
        name = "吾心安处即五班";
        remark = " ";
        sign = "(200人群)同事,朋友-同学";
        headIcon = ivFiv;
    }

    /** 设定这个好友是不是一个群 */
    public void setGroup(boolean group) {
        this.group = group;
    }

    /** 得知这个好友是不是一个群 */
    public boolean isGroup() {
        return group;
    }

    /** 获得用户名 */
    public String getName() { return name; }

    /** 设定用户名 */
    public void setName(String name) { this.name = name; }

    /** 获得备注 */
    public String getRemark() { return remark; }

    /** 设定备注 */
    public void setRemark(String remark) { this.remark = remark; }

    /** 获得个新签名 */
    public String getSign() { return sign; }

    /** 设定个新签名 */
    public void setSign(String sign) { this.sign = sign; }

    /** 获得头像 */
    public ImageView getHeadIcon() { return headIcon; }

    /** 设定头像 */
    public void setHeadIcon(ImageView newIcon) { headIcon = newIcon; }

    public HBox getFriendBig() {
        HBox hbox = new HBox(10);
        VBox vbox = new VBox(4);
        HBox namePane = new HBox();
        Text txtName;
        Text txtRemark;
        if (isGroup()) {
            txtName = new Text("");
            txtRemark = new Text(name);
        } else {
            txtName = new Text("(" + name + ")");
            txtRemark = new Text(remark);
        }
        Text txtSign = new Text();
        if (sign.length() > 15) {
            txtSign.setText(sign.substring(0, 15) + "...");
        } else txtSign.setText(sign);

        headIcon.setFitHeight(42);
        headIcon.setFitWidth(42);

        txtName.setFont(Font.font("Microsoft YaHei", 14));
        txtRemark.setFont(Font.font("Microsoft YaHei", 14));
        txtSign.setFont(Font.font("Microsoft YaHei", 12));
        txtSign.setFill(Color.GRAY);
        txtName.setFill(Color.GRAY);

        namePane.getChildren().addAll(txtRemark, txtName);
        vbox.getChildren().addAll(namePane, txtSign);
        hbox.getChildren().addAll(headIcon, vbox);
        hbox.setPadding(new Insets(3, 5, 3, 5));
        vbox.setAlignment(Pos.CENTER_LEFT);
        return hbox;
    }
}
