package utils;

import javafx.animation.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;

public class WeatherAnimations {

    public static void applyBounce(ImageView icon) {
        icon.setScaleX(0);
        icon.setScaleY(0);

        ScaleTransition bounceIn = new ScaleTransition(Duration.millis(500), icon);
        bounceIn.setFromX(0);
        bounceIn.setFromY(0);
        bounceIn.setToX(1.1);
        bounceIn.setToY(1.1);

        ScaleTransition settle = new ScaleTransition(Duration.millis(150), icon);
        settle.setToX(1.0);
        settle.setToY(1.0);

        bounceIn.setOnFinished(e -> settle.play());
        bounceIn.play();
    }

    public static void applyPulse(ImageView icon) {
        ScaleTransition pulse = new ScaleTransition(Duration.millis(2500), icon);
        pulse.setFromX(1.0);
        pulse.setFromY(1.0);
        pulse.setToX(1.04);
        pulse.setToY(1.04);
        pulse.setCycleCount(Animation.INDEFINITE);
        pulse.setAutoReverse(true);
        pulse.play();
    }

    public static void applyIntro(StackPane root) {
        root.setOpacity(0);
        root.setTranslateY(40);

        FadeTransition fadeIn = new FadeTransition(Duration.millis(400), root);
        fadeIn.setToValue(1.0);

        TranslateTransition slideUp = new TranslateTransition(Duration.millis(400), root);
        slideUp.setToY(0);

        new ParallelTransition(fadeIn, slideUp).play();
    }
}
