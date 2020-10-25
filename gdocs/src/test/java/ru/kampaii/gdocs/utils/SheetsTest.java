package ru.kampaii.gdocs.utils;

import com.google.api.services.sheets.v4.Sheets;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

public class SheetsTest {

    private static Sheets sheetsService;
    private static String SPREADSHEET_ID = "1lwQDTx_DQPogr_bgBuPbD8Yqtk551Kkn-LKXPAUx7fc";

    @BeforeClass
    public static void setup() throws GeneralSecurityException, IOException {
        sheetsService = SheetsServiceUtil.getSheetsService();
    }

    @Test
    public void whenWriteSheet_thenReadSheetOk() throws IOException, GeneralSecurityException {
        List<List<Object>> values = sheetsService.spreadsheets().values().get(SPREADSHEET_ID, "A:F").execute().getValues();

        values.forEach(value -> soutValue(value));

    }

    private void soutValue(List<Object> value) {

        System.out.println("value: ");

        for (Object o : value) {
            System.out.println(o);
        }
    }
}
