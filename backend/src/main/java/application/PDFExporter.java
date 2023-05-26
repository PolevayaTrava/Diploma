package application;

import application.entity.OrderedItems;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.*;
import jakarta.servlet.http.HttpServletResponse;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class PDFExporter {
    private List<OrderedItems> orderedItemsList;
    public PDFExporter(List<OrderedItems> orderedItemsList) {
        this.orderedItemsList = orderedItemsList;
    }

    private void writeTableHeader(PdfPTable table) throws IOException {
        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.GRAY);
        cell.setHorizontalAlignment(1);

        BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA_BOLD, "UTF-8", BaseFont.EMBEDDED);
        Font font = FontFactory.getFont(String.valueOf(baseFont));
        font.setSize(12);
        font.setColor(Color.BLACK);

        for (String headers : Arrays.asList("Название","Количество","Наличие")) {
            cell.setPhrase(new Phrase(headers, font));
            table.addCell(cell);
        }

    }

    private void writeTableData(PdfPTable table) {
        for (OrderedItems orderedItems : orderedItemsList) {
            table.addCell(String.valueOf(orderedItems.getItems().getItemName()));
            table.addCell(String.valueOf(orderedItems.getItems().getCount()));
            table.addCell("");
        }
    }

    public void export(HttpServletResponse response, Long id) throws DocumentException, IOException {
        Document document = new Document(PageSize.A4);
        PdfWriter.getInstance(document, response.getOutputStream());

        BaseFont baseFont = BaseFont.createFont(BaseFont.HELVETICA, "UTF-8", BaseFont.EMBEDDED);
        document.open();
        Font font = FontFactory.getFont(String.valueOf(baseFont));
        font.setColor(Color.BLACK);
        font.setSize(20);

        Paragraph p = new Paragraph("Заказ №" + id, font);
        p.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(p);

        font.setSize(11);
        Paragraph p1 = new Paragraph("Отправитель: СкладОПТ", font);
        document.add(p1);

        Paragraph p2 = new Paragraph("Заказчик: " +
                orderedItemsList.get(0).getOrders().getCustomer().getFullName(), font);
        document.add(p2);

        Paragraph p3 = new Paragraph("Адрес: " +
                orderedItemsList.get(0).getOrders().getCustomer().getAddress(), font);
        document.add(p3);

        Paragraph p4 = new Paragraph("Телефон: " +
                orderedItemsList.get(0).getOrders().getCustomer().getPhone(), font);
        document.add(p4);

        Paragraph p5 = new Paragraph("Менеджер: " +
                orderedItemsList.get(0).getOrders().getManager().getFullName(), font);
        document.add(p5);

        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {20f, 4f, 3.7f});
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table);

        document.add(table);
        document.close();
    }
}
