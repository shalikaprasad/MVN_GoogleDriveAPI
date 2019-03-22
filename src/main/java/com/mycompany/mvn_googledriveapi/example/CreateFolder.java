package com.mycompany.mvn_googledriveapi.example;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
 
 
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.File;
import com.mycompany.mvn_googledriveapi.utils.GoogleDriveUtils;
 
public class CreateFolder {
 
    public static final File createGoogleFolder(String folderIdParent, String folderName) throws IOException {
 
        File fileMetadata = new File();
 
        fileMetadata.setName(folderName);
        fileMetadata.setMimeType("application/vnd.google-apps.folder");
 
        if (folderIdParent != null) {
            List<String> parents = Arrays.asList(folderIdParent);
 
            fileMetadata.setParents(parents);
        }
        Drive driveService = GoogleDriveUtils.getDriveService();
 
        // Create a Folder.
        // Returns File object with id & name fields will be assigned values
        File file = driveService.files().create(fileMetadata).setFields("id, name").execute();
 
        return file;
    }
 
    public static void main(String[] args) throws IOException {
 
        // Create a Root Folder
        File folder = createGoogleFolder(null, "WarrantySystem");
         
        System.out.println("Created folder with id= "+ folder.getId());
        System.out.println("                    name= "+ folder.getName());
 
        System.out.println("Done!");
    }
     
}