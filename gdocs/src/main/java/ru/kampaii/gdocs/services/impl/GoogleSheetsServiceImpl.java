package ru.kampaii.gdocs.services.impl;

import com.google.api.services.sheets.v4.Sheets;
import org.springframework.stereotype.Service;
import ru.kampaii.gdocs.services.GoogleSheetsService;
import ru.kampaii.gdocs.utils.SheetsServiceUtil;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

@Service
public class GoogleSheetsServiceImpl implements GoogleSheetsService {

    private static Sheets sheetsService;

    public GoogleSheetsServiceImpl() throws GeneralSecurityException, IOException {
        sheetsService = SheetsServiceUtil.getSheetsService();
    }

    @Override
    public List<List<Object>> getSheetValue(String sheetId) throws IOException {
        return sheetsService.spreadsheets().values().get(sheetId, "A:F").execute().getValues();
    }
}
