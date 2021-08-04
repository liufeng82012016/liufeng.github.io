package music.controller;

import com.jfoenix.controls.JFXButton;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import music.Scanner;
import music.http.Response;
import music.modul.ProgressStage;
import music.util.RequestTask;

import java.util.Map;

/**
 * @Author Ailwyn
 * @Description: todo
 * @Date 2020/12/17 19:01
 */
public class LoginViewController {

    @FXML
    private JFXButton loginButton;
    @FXML
    private Label forgetLabel;
    @FXML
    private Label registerLabel;


    private static String login = "登录";
    private static String register = "注册";
    private static String forget = "忘记密码";


//    private StringProperty loginButtonContent;
//    private StringProperty forgetButtonContent;
//    private StringProperty registerButtonContent;

    private byte state;

    public LoginViewController() {
        state = 1;
    }

    public void login() {
        if (state == 1) {

        } else if (state == 2) {

        } else if (state == 3) {

        }
        ProgressStage.of(Scanner.getPrimaryStage(), new RequestTask(), "视频转码中...").show();
    }

    public void register() {
        if (state == 1) {
            state = 2;
            loginButton.setText(register);
            registerLabel.setText(login);
        } else if (state == 2) {
            state = 1;
            loginButton.setText(login);
            registerLabel.setText(register);
        } else if (state == 3) {
            state = 1;
            loginButton.setText(login);
            registerLabel.setText(register);
            forgetLabel.setVisible(true);
        }
    }

    public void forget() {
        if (state != 3) {
            state = 3;
            loginButton.setText(forget);
            registerLabel.setText(login);
            forgetLabel.setVisible(false);
            // 弹窗
        }
    }

    private class HttpRequestTask extends Task<Response> {

        private String url;
        // 暂时只支持Get/Post
        private Boolean isGet;
        private Map<String, ?> urlVariables;
        // 请求体
        private String body;

        @Override
        protected Response call() throws Exception {
            // todo 发送Http请求
            return null;
        }

    }

}
