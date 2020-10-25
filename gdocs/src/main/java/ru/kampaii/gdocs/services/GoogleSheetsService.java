package ru.kampaii.gdocs.services;

import java.io.IOException;
import java.util.List;

public interface GoogleSheetsService {

    List<List<Object>> getSheetValue(String sheetId) throws IOException;
}
