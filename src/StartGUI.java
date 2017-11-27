import Acq.IBusiness;
import Acq.IData;
import Acq.IUI;
import Business.BusinessFacade;
import Data.DataFacade;
import Presentation.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class StartGUI extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("Presentation/FXML/View.fxml"));
        IBusiness businessFacade = new BusinessFacade();
        IData dataFacade = new DataFacade();
        IUI controller = new Controller();

        controller.injectBusiness(businessFacade);
        businessFacade.injectData(dataFacade);
        loader.setController(controller);

        VBox root = loader.load();
        Scene scene = new Scene(root, root.getPrefWidth(), root.getPrefHeight());

        primaryStage.setTitle("WorkshopOpgave");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}
