package officelog;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Zooty
 */
public class AddPersonController implements Initializable {

    private static Model model;
    @FXML
    private AnchorPane MainWindow;
    @FXML
    private Label lbName;
    @FXML
    private TextField tfName;
    @FXML
    private Button btnSelPic;
    @FXML
    private CheckBox cbEmp;
    @FXML
    private ListView<Room> lvLeft;
    private ObservableList<Room> lvLeftItems = FXCollections.observableArrayList();
    @FXML
    private ListView<Room> lvRight;
    ObservableList<Room> lvRightItems = FXCollections.observableArrayList();
    @FXML
    private Button btnToLeft;
    @FXML
    private Button btnToRight;
    @FXML
    private Label lbLeft;
    @FXML
    private Label lbRight;
    @FXML
    private Button btnCancel;
    @FXML
    private Button btnSubmit;
    @FXML
    private TextField tfJob;
    @FXML
    private Label lbJob;
    @FXML
    private ImageView ivIcon;
    private BufferedImage NewImg = null;
    
    @FXML
    private void handleAction(ActionEvent event) {
        if (event.getSource() == cbEmp){
            lvLeft.setVisible(cbEmp.selectedProperty().get());
            lvRight.setVisible(cbEmp.selectedProperty().get());
            btnToRight.setVisible(cbEmp.selectedProperty().get());
            btnToLeft.setVisible(cbEmp.selectedProperty().get());
            lbLeft.setVisible(cbEmp.selectedProperty().get());
            lbRight.setVisible(cbEmp.selectedProperty().get());
            lbJob.setVisible(cbEmp.selectedProperty().get());
            tfJob.setVisible(cbEmp.selectedProperty().get());
        }
        if (event.getSource() == btnToRight){
            lvRightItems.addAll(lvLeft.getSelectionModel().getSelectedItems());
            lvLeftItems.removeAll(lvLeft.getSelectionModel().getSelectedItems());
        }
        if (event.getSource() == btnToLeft){            
            lvLeftItems.addAll(lvRight.getSelectionModel().getSelectedItems());
            lvRightItems.removeAll(lvRight.getSelectionModel().getSelectedItems());
        }
        if (event.getSource() == btnSelPic){
            FileChooser fileChooser = new FileChooser();
            FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
            FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
            fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG);
            File selectedFile = fileChooser.showOpenDialog(null);
            if (selectedFile != null)
                try {
                    if(ImageIO.read(selectedFile).getWidth() == ImageIO.read(selectedFile).getHeight()){
                        NewImg = ImageIO.read(selectedFile);
                        ivIcon.setImage(SwingFXUtils.toFXImage(NewImg, null));
                    }
                    else{
                        Alert alert = new Alert(AlertType.ERROR);
                        alert.setTitle("Error Dialog");
                        alert.setHeaderText("Look, an Error Dialog");
                        alert.setContentText("Icon is not NxN!");
                        alert.showAndWait();
                    }                    
            } catch (IOException ex) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Ooops, there was an error!");
                alert.showAndWait();
            }
        }
        if (event.getSource() == btnCancel){
            ((Stage)(btnCancel.getScene().getWindow())).close();
        }
        if (event.getSource() == btnSubmit) {
            if ("".equals(tfName.getText())) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle("Error Dialog");
                alert.setHeaderText("Look, an Error Dialog");
                alert.setContentText("Ooops, there was an error!");
                alert.showAndWait();
            } else if (cbEmp.selectedProperty().get()) {
                if (tfJob.getText().equals("")) {
                    Alert alert = new Alert(AlertType.WARNING);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Look, an Error Dialog");
                    alert.setContentText("Ooops, there was an error!");
                    alert.showAndWait();
                } else {
                    Room[] per = new Room[lvRightItems.size()];
                    int i = 0;
                    for (Room room : lvRightItems) {
                        per[i++] = room;                        
                    }
                    if (NewImg == null) {
                        model.getPeople().getPerson(model.getPeople().addEmployee(tfName.getText(), tfJob.getText(), per)).setLocation(model.getRoom("Outside"));
                        ((Stage)(btnSubmit.getScene().getWindow())).close();
                    }else{
                        model.getPeople().getPerson(model.getPeople().addEmployee(tfName.getText(), NewImg, tfJob.getText(), per)).setLocation(model.getRoom("Outside"));
                        ((Stage)(btnSubmit.getScene().getWindow())).close();
                    }
                }
            }else{// if Person (not employee)
                if(NewImg == null){
                    model.getPeople().getPerson(model.getPeople().addPerson(tfName.getText())).setLocation(model.getRoom("Outside"));
                    ((Stage)(btnSubmit.getScene().getWindow())).close();
                }else{
                    model.getPeople().getPerson(model.getPeople().addPerson(tfName.getText(),NewImg)).setLocation(model.getRoom("Outside"));
                    ((Stage)(btnSubmit.getScene().getWindow())).close();
                }
            }
        }
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        lvRight.setItems(lvRightItems);
        lvLeft.setItems(lvLeftItems);
        lvRight.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        lvLeft.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        for (Room room : model.getOffice()) {
            lvLeftItems.add(room); // TODO language            
        }
        lvLeftItems.remove(model.getRoom("Outside"));
        // TODO language
    }  

    public static void setModel(Model model) {
        AddPersonController.model = model;
    }
}