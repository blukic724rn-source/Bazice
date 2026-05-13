module artikli {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    requires java.sql;

    exports app;
    opens app.classes to javafx.base;
}