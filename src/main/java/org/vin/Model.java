package org.vin;

import java.io.*;


/**
 * Model class for the App Class, Controller Class, and app.fxml
 */
public class Model {

    /**
     * Instantiates the Model class
     * @param myController
     */
    public Model(Controller myController){
        this.myController = myController;
    }



    /**
     * Sets the controller to the private variable in Model class
     * @param myController
     * @return
     */
    protected void setController(Controller myController){
        this.myController = myController;
    }



    /**
     * Set the current viewable File
     * @param current
     */
    protected void setFile(File current){
        currentFile = current;
        originalFileContent = "";
        String filePath = currentFile.getAbsolutePath();
        try {
            BufferedReader br = new BufferedReader(new FileReader(filePath));
            while(br.ready()){
                originalFileContent += Character.toString(br.read());
            }
            br.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        myController.writeToTextArea(originalFileContent);
    }


    /**
     * Save new content into original file and replaces original string
     * @param newContent content taken from the TextArea
     * @return True if successfully save, False if failed to save
     */
    protected boolean saveFile(String newContent){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(currentFile));
            originalFileContent = newContent;
            bw.write(newContent);
            bw.close();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }


    /**
     * Save file to new location selected by the user and replaces original string
     * @param newFile the new file location stored in File Class
     * @param newContent content taken from the TextArea
     * @return True if successfully save, False if failed to save
     */
    protected boolean saveFile(File newFile, String newContent){
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(newFile));
            originalFileContent = newContent;
            bw.write(newContent);
            bw.close();
            return true;
        } catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }


    /**
     * Getter function that returns the currently viewable file
     * @return File of currently viewed file
     */
    protected File getCurrentFile(){
        return currentFile;
    }


    /**
     * Getter function that returns the currently viewable original file content
     * @return String file content of currently viewed file
     */
    protected String getOriginalFileContent(){
        return originalFileContent;
    }

    private String originalFileContent;
    private File currentFile;
    private Controller myController;
}
