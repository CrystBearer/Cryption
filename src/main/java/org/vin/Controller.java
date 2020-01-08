package org.vin;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.vin.ciphers.Decrypt;
import org.vin.ciphers.Encrypt;

import java.io.File;

/**
 * Controller class for the App Class, Model Class, and app.fxml
 */
public class Controller {

    /**
     * Sets the data model
     * @param data
     */
    protected void setModel(Model data){
        myData = data;
    }


    /**
     * Encrypts the text shown on the gui
     */
    protected void encryptAction(){
        if(ciphers.getSelectedToggle() != null) {
            RadioMenuItem selectedRadioBtn = (RadioMenuItem) ciphers.getSelectedToggle();
            String cipherName = selectedRadioBtn.getText().toLowerCase();
            System.out.println(cipherName);
            switch(cipherName) {
                case "caesar":
                    String output = encrypter.encrypt(textAreaContent.getText(), cipherName, enterRotation());
//                    writeToTextArea(encrypter.encrypt(textAreaContent.getText(), cipherName, enterRotation()));
                    writeToTextArea(output);
                    break;
                default:
                    writeToTextArea( encrypter.encrypt(textAreaContent.getText(), cipherName));
                    break;
            }
        }
    }


    /**
     * Decrypts the text shown on the gui
     */
    protected void decryptAction(){
        if(ciphers.getSelectedToggle() != null) {
            RadioMenuItem selectedRadioBtn = (RadioMenuItem) ciphers.getSelectedToggle();
            String cipherName = selectedRadioBtn.getText().toLowerCase();
            switch(cipherName) {
                case "caesar":
                    writeToTextArea(decrypter.decrypt(textAreaContent.getText(), cipherName, enterRotation()));
                    break;
                default:
                    writeToTextArea( decrypter.decrypt(textAreaContent.getText(), cipherName));
                    break;
            }
        }
    }



    /**
     * Writes to the text area with the given file content
     * @param fileContent
     */
    protected void writeToTextArea(String fileContent){
        textAreaContent.clear();
        textAreaContent.setText(fileContent);
    }



    /**
     *
     */
    @FXML
    protected String enterRotation(){
        TextInputDialog rotation = new TextInputDialog("23");
        rotation.showAndWait();
        String key = rotation.getEditor().getText();
        System.out.println(key);
        return key;
    }

    /**
     * Cancel button to turns back to the original file content as it first started
     * @param e
     */
    @FXML
    protected void revertAction(ActionEvent e){
        writeToTextArea(myData.getOriginalFileContent());
    }


    /**
     * Encrypts/Decrypts the file content
     * @param e event
     */
    @FXML
    protected void cryptAction(ActionEvent e){
        RadioMenuItem selectedRadioBtn = (RadioMenuItem) modes.getSelectedToggle();
        if(selectedRadioBtn.getText().equals("Encrypt")){
            encryptAction();
        } else if (selectedRadioBtn.getText().equals("Decrypt")){
            decryptAction();
        }
    }

    /**
     * Saves text area to original file location
     */
    @FXML
    protected void saveMenuAction(ActionEvent e){
        myData.saveFile(textAreaContent.getText());
    }


    /**
     * Saves text area to original file location
     */
    @FXML
    protected void saveAsMenuAction(ActionEvent e){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File As");
        Stage primaryStage = (Stage) menuBar.getScene().getWindow();
        File selectedFile = fileChooser.showSaveDialog(primaryStage);
        if(selectedFile != null){
            myData.saveFile(selectedFile, textAreaContent.getText());
        }
    }


    /**
     * Handles the "Open" and the "Add File" option
     * @param e
     */
    @FXML
    protected void openMenuAction(ActionEvent e) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open File");
        fileChooser.getExtensionFilters().addAll(
          new FileChooser.ExtensionFilter("Text Files", "*.txt", "*.docx")
        );
        Stage primaryStage = (Stage) menuBar.getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(primaryStage);
        if(selectedFile != null){
            myData.setFile(selectedFile);
        }
    }



    /**
     * Exits the application
     * @param e
     */
    @FXML
    protected void exitMenuAction(ActionEvent e) {
        Stage owner = (Stage) menuBar.getScene().getWindow();
        owner.close();
        return;
    }

    Encrypt encrypter = new Encrypt();
    Decrypt decrypter = new Decrypt();
    private Model myData;
    @FXML
    private TextArea textAreaContent;
    @FXML
    private MenuItem openButton, saveButton, saveAsButton, exitButton;
    @FXML
    private MenuBar menuBar;
    @FXML
    private ToggleGroup modes, ciphers;
    @FXML
    private Button applyBtn, cancelBtn;
}
