package main;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        PropertyStorage prop;
        try {
            prop = new PropertyStorage("./app.properties", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        SharedFolderAccessor accessor = new SharedFolderAccessor(prop.getPropertyString("stdout_charset"));

        String driveLetter = accessor.searchDriveLetter();
        if (!accessor.assignNetworkDrive(driveLetter, prop.getPropertyString("shared_dir_path"), prop.getPropertyString("user_id"), prop.getPropertyString("password"))) {
           return;
        }
        if (!accessor.deleteNetworkDrive(driveLetter)) {
            return;
         }
    }
}
