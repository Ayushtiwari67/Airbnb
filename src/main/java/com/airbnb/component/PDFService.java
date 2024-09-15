package com.airbnb.component;
import com.airbnb.payload.BookingDto;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.stream.Stream;


@Service
public class PDFService {

    private EmailService emailService;

    public PDFService(EmailService emailService) {
        this.emailService = emailService;
    }

    public void generatePdf(BookingDto bookingDto){
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("G://BookingPdf/"+bookingDto.getId()+"_booking_confirmation.pdf"));

            document.open();

            PdfPTable table = new PdfPTable(8);
            addTableHeader(table);
            addRows(table,bookingDto);
            document.add(table);
            document.close();
            //Another method to call email
            emailService.sendEmailWithAttachment(
                    bookingDto.getEmail(),
                    "Booking Confirmation. Your Booking id is "+bookingDto.getId(),
                    "Your booking has been confirmed",
                    new File("G://BookingPdf/"+bookingDto.getId()+"_booking_confirmation.pdf")
            );
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    private void addTableHeader(PdfPTable table) {
        Stream.of("Guest Name", "Hotel Name","Mobile", "City","Room Type","Check In Date", "Check Out Date","Total Price")
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setSpaceCharRatio(2);
                    header.setBorderWidth(1);
                    header.setPhrase(new Phrase(columnTitle));
                    table.addCell(header);
                });
    }
    private void addRows(PdfPTable table,BookingDto bookingDto) {
        table.addCell(bookingDto.getGuestsName());
        table.addCell(bookingDto.getProperty().getName());
        table.addCell(bookingDto.getMobile());
        table.addCell(bookingDto.getProperty().getCity().getName());
        table.addCell(bookingDto.getTypeOfRoom());
        table.addCell(bookingDto.getCheckInDate().toString());
        table.addCell(bookingDto.getCheckOutDate().toString());
        table.addCell(bookingDto.getTotalPrice().toString());
    }
}
