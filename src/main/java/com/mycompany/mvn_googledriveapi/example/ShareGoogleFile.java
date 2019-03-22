package com.mycompany.mvn_googledriveapi.example;

import java.io.IOException;
 
 
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.model.Permission;
import com.mycompany.mvn_googledriveapi.utils.GoogleDriveUtils;
 
public class ShareGoogleFile {
 
    // Public a Google File/Folder.
    public static Permission createPublicPermission(String googleFileId) throws IOException {
        // All values: user - group - domain - anyone
        String permissionType = "anyone";
        // All values: organizer - owner - writer - commenter - reader
        String permissionRole = "reader";
 
        Permission newPermission = new Permission();
        newPermission.setType(permissionType);
        newPermission.setRole(permissionRole);
 
        Drive driveService = GoogleDriveUtils.getDriveService();
        return driveService.permissions().create(googleFileId, newPermission).execute();
    }
 
    public static Permission createPermissionForEmail(String googleFileId, String googleEmail) throws IOException {
        // All values: user - group - domain - anyone
        String permissionType = "user"; // Valid: user, group
        // organizer - owner - writer - commenter - reader
        String permissionRole = "reader";
 
        Permission newPermission = new Permission();
        newPermission.setType(permissionType);
        newPermission.setRole(permissionRole);
 
        newPermission.setEmailAddress(googleEmail);
 
        Drive driveService = GoogleDriveUtils.getDriveService();
        return driveService.permissions().create(googleFileId, newPermission).execute();
    }
 
    public static void main(String[] args) throws IOException {
 
        String googleFileId1 = "1DaTrshPdTs-7Z96A1uW_QJMRBd9JMS2s";
        String googleEmail = "sarcorganization1@gmail.com";
 
        // Share for a User
        createPermissionForEmail(googleFileId1, googleEmail);
 
        String googleFileId2 = "1APekCMEyFUcKE_hq8s38zs-OWKONQVn2";
 
        // Share for everyone
        createPublicPermission(googleFileId2);
 
        System.out.println("Done!");
    }
 
}