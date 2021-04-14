package carDate.inv;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;

import carDate.inv.InvController.InvCust;

public class UserPDFExporter {

	private InvCust invCust;
	
	public UserPDFExporter(InvCust invCust) {
		this.invCust = invCust;
	}
	private void writeTableHeader(PdfPTable table) {

	}
	
	private void writeTableData(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);
        //Font font = FontFactory.getFont(FontFactory.HELVETICA);
        
        cell.setPhrase(new Phrase("Total Time Charge at Daily Rate: " + invCust.getInv().getRated()));
        cell.setBorderWidth(0);
        table.addCell(cell);

        cell.setPhrase(new Phrase("Sub Total: " 
				+ String.format("%.2f", invCust.getInv().getInvPaymt().getAmount() )));
        cell.setIndent(10);
        cell.setBorderWidth(0);
        table.addCell(cell);
         
        cell.setPhrase(new Phrase(""));
        cell.setBorderWidth(0);
        table.addCell(cell);
         
        cell.setPhrase(new Phrase( "Total Due: $" 
        		+ String.format("%.2f", invCust.getInv().getInvPaymt().getAmount() )));
        table.addCell(cell);  
	}
	
	/*
	 * ref: https://www.programcreek.com/java-api-examples/?api=com.lowagie.text.pdf.PdfWriter
	 * add img - see example 18 
	 * */
	public void export(HttpServletResponse response) throws DocumentException, IOException {
		Document document = new Document(PageSize .A4);
		PdfWriter.getInstance(document, response.getOutputStream());
		
		document.open();
		
		Paragraph pT = new Paragraph("CarZoom Vehicle Leasing", new Font(Font.ITALIC, 10));
		document.add(pT);
		
		Paragraph p1 = new Paragraph("INVOICE", new Font(Font.COURIER, 18));
		p1.setAlignment(Paragraph.ALIGN_CENTER);
		p1.setSpacingBefore(25);
		document.add(p1);
		
		Paragraph p2 = new Paragraph("Invoice No.: " + invCust.getInv().getInvNo());
		p2.setAlignment(Paragraph.ALIGN_RIGHT);
		p2.setSpacingBefore(50);
		document.add(p2);
		
		Paragraph p3 = new Paragraph("Date: " + invCust.getInv().getDated());
		document.add(p3);
		p3 = new Paragraph("Customer's ID: " + invCust.getInv().getCustId()
				+ "\n" + invCust.getCust().getCustName()
				+ "\n" + invCust.getCust().getAddr1()
				+ "\n" + invCust.getCust().getAddr2()
				+ "\n" + invCust.getCust().getCity() );
		p3.setSpacingBefore(18);
		document.add(p3);
		
		Paragraph p4 = new Paragraph("Reservation Summary", new Font(Font.COURIER, 16));
		p4.setSpacingBefore(25);
		document.add(p4);
		Paragraph p5 = new Paragraph("Start and End Time: " + invCust.getInv().getDesc1());
		document.add(p5);
		p5 = new Paragraph(invCust.getInv().getDesc2());
		document.add(p5);
		p5 = new Paragraph(invCust.getInv().getDesc3());
		document.add(p5);

		Paragraph p6 = new Paragraph("Invoice Details", new Font(Font.COURIER, 16));
		p6.setSpacingBefore(18);
		document.add(p6);
		p6 = new Paragraph("Reservation Charges No.: " + invCust.getInv().getHireId());
		document.add(p6);
		
		PdfPTable table = new PdfPTable(2);
        table.setWidthPercentage(100f);
        table.setWidths(new float[] {12.0f, 4.0f}); // total 17.5
        table.setSpacingBefore(18);
		writeTableData(table);
		document.add(table);
		
		Paragraph p7 = new Paragraph("Payment", new Font(Font.COURIER, 16));
		p7.setSpacingBefore(25);
		document.add(p7);
		p7 = new Paragraph("Payment Date: " + invCust.getInv().getPaidDate());
		document.add(p7);

        Font font = FontFactory.getFont(FontFactory.TIMES_ITALIC);
        font.setSize(10);
		Paragraph pBottom = new Paragraph("Thank you for using CarZoom!", font);	
		pBottom.setSpacingBefore(25);
		document.add(pBottom);

		document.close();

	}
}
