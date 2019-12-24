package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

public final class Main {

    public static void main(final String[] args) {
        PropertyStorage prop;
        try {
            prop = new PropertyStorage("./app.properties", "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        SharedFolderAccessor accessor = new SharedFolderAccessor(prop.getPropertyString("stdout_charset"));
        List<String> outputs = new ArrayList<String>();
        boolean result = false;

        try {
            String driveLetter = accessor.searchDriveLetter(outputs);
            if (!StringUtils.isEmpty(driveLetter)) {
                outputs.forEach(line -> {System.out.println(line);});
            }
            else {
                outputs.forEach(line -> {System.err.println(line);});
                return;
            }

            result = accessor.assignNetworkDrive(driveLetter, prop.getPropertyString("shared_dir_path"), prop.getPropertyString("user_id"), prop.getPropertyString("password"), outputs);
            if (result) {
                outputs.forEach(line -> {System.out.println(line);});
            }
            else {
                outputs.forEach(line -> {System.err.println(line);});
                return;
            }

            result = accessor.deleteNetworkDrive(driveLetter, outputs);
            if (result) {
                outputs.forEach(line -> {System.out.println(line);});
            }
            else {
                outputs.forEach(line -> {System.err.println(line);});
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Main() {
    }
}
