package ru.kampaii.gdocs.utils;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.kampaii.gdocs.config.GDocsConfiguration;
import ru.kampaii.gdocs.services.GoogleSheetsService;

import java.io.IOException;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = GDocsConfiguration.class)
public class SheetsTest {

    @Autowired
    private GoogleSheetsService service;

    private static String SPREADSHEET_ID = "1lwQDTx_DQPogr_bgBuPbD8Yqtk551Kkn-LKXPAUx7fc";

    @Test
    public void whenWriteSheet_thenReadSheetOk() throws IOException{

        List<List<Object>> values = service.getSheetValue(SPREADSHEET_ID);

        values.forEach(value -> soutValue(value));

    }

    private void soutValue(List<Object> value) {

        System.out.println("value: ");

        for (Object o : value) {
            System.out.println(o);
        }
    }
}
