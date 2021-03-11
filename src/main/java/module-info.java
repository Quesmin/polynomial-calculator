module com.utcn.dsrl {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.utcn.dsrl to javafx.fxml;
    exports com.utcn.dsrl;
}