package jp.dip.oyasirazu.mikutterj;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import org.jruby.Ruby;
import org.jruby.RubyInstanceConfig;
import org.jruby.ast.Node;
import org.jruby.internal.runtime.GlobalVariable.Scope;
import org.jruby.javasupport.JavaUtil;
import org.jruby.runtime.GlobalVariable;
import org.jruby.runtime.builtin.IRubyObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mikutter を JavaFX で表示する。
 */
public class MikutterJFX extends Application {

    // Mikutter スクリプトパス
    private final static String SCRIPT_PATH = "./src/main/resource/script/mikutter/mikutter.rb";

    // Mikutter プラグインパス
    private final static String PLUGIN_PATH = "./src/main/resource/.mikutter";

    // Mikutter の gems パス
    private final static String GEMS_PATH = "./src/main/resource/gems/vendor/jruby/2.2.0/gems";

    // Mikutter 用コントローラークラス
    private static MikutterJFXController controller;

    private static Logger logger = LoggerFactory.getLogger(MikutterJFX.class);

    public static void main(String[] args) throws IOException {
        // GUI(JavaFX) を別スレッドで実行
        new Thread(() -> { Application.launch(MikutterJFX.class, args); }).start();

        // Mikutter 実行開始

        // Ruby のロードパス設定
        final List<String> loadPaths = getLoadPathList(GEMS_PATH);
        RubyInstanceConfig config = new RubyInstanceConfig();
        config.setLoadPaths(loadPaths);

        // Ruby インスタンス作成
        Ruby ruby = Ruby.newInstance(config);

        // コマンドライン引数を無理やり設定して confroot を設定
        ruby.evalScriptlet("ARGV.replace [" + "'--confroot=" + PLUGIN_PATH + "']");

        // controller, logger を Ruby のグローバル変数として定義、
        IRubyObject rController = JavaUtil.convertJavaToRuby(ruby, controller);
        IRubyObject rLogger = JavaUtil.convertJavaToRuby(ruby, logger);
        GlobalVariable controller = new GlobalVariable(ruby, "$controller", rController);
        GlobalVariable logger = new GlobalVariable(ruby, "$logger", rLogger);
        ruby.defineVariable(controller, Scope.GLOBAL);
        ruby.defineVariable(logger, Scope.GLOBAL);

        // Mikutter スクリプト実行
        Path p = Paths.get(SCRIPT_PATH);
        Node node = ruby.parseFile(
                Files.newInputStream(p),
                p.toString(),
                ruby.getCurrentContext().getCurrentScope(),
                0);
        ruby.runNormally(node);
    }

    @Override
    public void start(Stage stage) throws IOException {

        // ウィンドウの x ボタンを押したときの挙動を設定。
        // JavaFX を exit してプログラム自体も終了する
        stage.setOnCloseRequest(event -> {
            Platform.exit();
            System.exit(0);
        });

        // FXML 読み込み
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/layout/MikutterJFX.fxml"));
        Pane root = loader.load();

        // ListView に設定した ObservableList を取得する。
        controller = loader.getController();
        controller.setRootStage(stage);

        // 適当にウィンドウ設定
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("MikutterJFX");
    }

    /**
     * Mikutter 用の loadPath リストを探す。
     */
    private static List<String> getLoadPathList(String gemPathStr) throws IOException {
        final Path gemPath = Paths.get(gemPathStr);
        final List<String> loadPaths = new ArrayList<>();

        // 
        Files.walkFileTree(gemPath, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult preVisitDirectory(Path file, BasicFileAttributes attrs) throws IOException {
                String fileStr = file.toAbsolutePath().toString();
                if (fileStr.endsWith("lib")) {
                    loadPaths.add(fileStr);
                    return FileVisitResult.SKIP_SUBTREE;
                }
                return FileVisitResult.CONTINUE;
            }
        });

        return loadPaths;
    }
}
