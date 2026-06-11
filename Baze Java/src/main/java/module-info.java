module artikli {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.base;
    requires java.desktop;
    requires java.sql;

    requires org.mongodb.driver.sync.client;
    requires org.mongodb.bson;
    requires org.mongodb.driver.core;

    exports app;
    exports app.controller;
    exports app.classes;
    opens app to javafx.base;
    opens app.controller to javafx.base;
    opens app.classes to javafx.base;
}
