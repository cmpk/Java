package common;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class SharedFolderAccessorTest {
    private PropertyStorage prop = null;

    @BeforeEach
    public void beforeEach() throws FileNotFoundException, IOException {
        this.prop = new PropertyStorage("./app.properties", "UTF-8");
    }

    @Test
    @DisplayName("利用可能なドライブ文字列を検索する")
    public final void test_searchDriveLetter() {
        SharedFolderAccessor accessor = new SharedFolderAccessor(prop.getPropertyString("stdout_charset"));

        boolean hasEmptyString = false;
        List<String> driveLetters = new ArrayList<String>();
        List<String> failClosingDriveLetters = new ArrayList<String>();
        try {
            for (int i = 0; i < 26; i++) { //SUPPRESS CHECKSTYLE ignore magic number
                String driveLetter = accessor.searchDriveLetter(null);
                if (StringUtils.isEmpty(driveLetter)) {
                    hasEmptyString = true;
                    break;
                }
                boolean result = accessor.assignNetworkDrive(driveLetter, prop.getPropertyString("shared_dir_path"), prop.getPropertyString("user_id"), prop.getPropertyString("password"), null);
                if (result) {
                    driveLetters.add(driveLetter);
                }
            }

            for (String driveLetter : driveLetters) {
                boolean result = accessor.deleteNetworkDrive(driveLetter, null);
                if (!result) {
                    failClosingDriveLetters.add(driveLetter);
                }
            }
        } catch (Exception e) {
            fail("エラー発生. 接続成功文字列:" + String.join(",", driveLetters) + ", 切断失敗文字列:" + String.join(",", failClosingDriveLetters), e);
        }

        if (!hasEmptyString) {
            fail("空文字取得に未到達. 接続成功文字列:" + String.join(",", driveLetters) + ", 切断失敗文字列:" + String.join(",", failClosingDriveLetters));
        }
    }

    @Test
    @DisplayName("利用済みドライブ文字列に対し接続しようとした場合に、失敗する")
    public final void testNegative_assignNetworkDrive() {
        SharedFolderAccessor accessor = new SharedFolderAccessor(prop.getPropertyString("stdout_charset"));
        String driveLetter = null;
        boolean result = false;

        try {
            driveLetter = accessor.searchDriveLetter(null);
            accessor.assignNetworkDrive(driveLetter, prop.getPropertyString("shared_dir_path"), prop.getPropertyString("user_id"), prop.getPropertyString("password"), null);
            result = accessor.assignNetworkDrive(driveLetter, prop.getPropertyString("shared_dir_path"), prop.getPropertyString("user_id"), prop.getPropertyString("password"), null);

            accessor.deleteNetworkDrive(driveLetter, null);
        } catch (Exception e) {
            fail("実行中にエラーが発生したため、" + driveLetter + ":\\ への割り当てが解除できていない可能性あり.", e);
        }

        assertFalse(result);
    }

    @Test
    @DisplayName("未接続のドライブ文字列に対し切断しようとした場合に、失敗する")
    public final void testNegative_deleteNetworkDrive() {
        SharedFolderAccessor accessor = new SharedFolderAccessor(prop.getPropertyString("stdout_charset"));
        String driveLetter = null;
        boolean result = false;

        try {
            driveLetter = accessor.searchDriveLetter(null);
            result = accessor.deleteNetworkDrive(driveLetter, null);
        } catch (Exception e) {
            fail(e);
        }

        assertFalse(result);
    }
}
