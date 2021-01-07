package com.homework.testassignment.main.utils;

import com.homework.testassignment.main.models.BookingTransaction;
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

    private static BookingTransaction getBookingTransaction(Iterator<Cell> rowCells) {
        BookingTransaction bookingTransaction = new BookingTransaction();
        int index = 0;
        while (rowCells.hasNext()) {
            Cell cell = rowCells.next();
            switch (index) {
                case 0:
                    bookingTransaction.setCustomerName(cell.getStringCellValue());
                    break;
                case 1:
                    bookingTransaction.setBookingDate(Utils.formatDate(cell.getLocalDateTimeCellValue()));
                    break;
                case 2:
                    bookingTransaction.setOpportunityID(cell.getStringCellValue());
                    break;
                case 3:
                    bookingTransaction.setBookingType(cell.getStringCellValue());
                    break;
                case 4:
                    bookingTransaction.setTotal(cell.getNumericCellValue());
                    break;
                case 5:
                    bookingTransaction.setAccountExecutive(cell.getStringCellValue());
                    break;
                case 6:
                    bookingTransaction.setSalesOrganization(cell.getStringCellValue());
                    break;
                case 7:
                    bookingTransaction.setTeam(cell.getStringCellValue());
                    break;
                case 8:
                    bookingTransaction.setProduct(cell.getStringCellValue());
                    break;
                case 9:
                    bookingTransaction.setRenewable(Utils.isRenewable(cell.getStringCellValue()));
                    break;
                default:
                    break;
            }
            index++;
        }
        return bookingTransaction;
    }

    public static List<BookingTransaction> excelToBookingTransactions(MultipartFile multipartFile, String sheet, String range) throws IOException {
        List<BookingTransaction> bookingTransactions = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
        Sheet workSheet = workbook.getSheet(sheet);
        Iterator<Row> rows = workSheet.iterator();
        String[] dataRange = range.split(":");

        int rowNo = 0;
        while (rows.hasNext()) {
            Row currentRow = rows.next();
            if (rowNo < Integer.parseInt(dataRange[0].substring(1))) {
                rowNo++;
                continue;
            }
            if (rowNo > Integer.parseInt(dataRange[1].substring(1))) {
                break;
            }
            Iterator<Cell> rowCells = currentRow.iterator();
            bookingTransactions.add(getBookingTransaction(rowCells));
            rowNo++;
        }
        return bookingTransactions;
    }

    public static boolean hasExcelFormat(MultipartFile file) {
        return TYPE.equals(file.getContentType());
    }
}
