package com.homework.testassignment.main.utils;

import com.homework.testassignment.main.models.Booking;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ExcelDataHelper {
    private static final String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";

    public static List<Booking> excelToBookingTransactions(MultipartFile multipartFile, String sheet, String range) throws IOException {
        List<Booking> bookings = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        Sheet workSheet = workbook.getSheet(sheet);
        Iterator<Row> rows = workSheet.iterator();
        String[] dataSheetRange = range.split(":");

        int rowNo = 0;
        while (rows.hasNext() && rowNo < Integer.parseInt(dataSheetRange[1].substring(1))) {
            Row currentRow = rows.next();
            if (rowNo < Integer.parseInt(dataSheetRange[0].substring(1))) {
                rowNo++;
                continue;
            }
            bookings.add(getBookingTransaction(currentRow.iterator()));
            rowNo++;
        }
        return bookings;
    }

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }

    private static Booking getBookingTransaction(Iterator<Cell> rowCells) {
        List<Cell> cells = new ArrayList<>();
        rowCells.forEachRemaining(cells::add);
        Booking booking = new Booking();
        booking.setCustomerName(cells.get(0).getStringCellValue());
        booking.setBookingDate(Utils.formatDate(cells.get(1).getLocalDateTimeCellValue()));
        booking.setOpportunityID(cells.get(2).getStringCellValue());
        booking.setBookingType(cells.get(3).getStringCellValue());
        booking.setTotal(cells.get(4).getNumericCellValue());
        booking.setAccountExecutive(cells.get(5).getStringCellValue());
        booking.setSalesOrganization(cells.get(6).getStringCellValue());
        booking.setTeam(cells.get(7).getStringCellValue());
        booking.setProduct(cells.get(8).getStringCellValue());
        booking.setRenewable(Utils.isRenewable(cells.get(9).getStringCellValue()));
        return booking;
    }
}
