/*
 * $Id: MyFirstTable.java 3373 2008-05-12 16:21:24Z xlv $
 *
 * This code is part of the 'iText Tutorial'.
 * You can find the complete tutorial at the following address:

 *
 * This code is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 *
 * itext-questions@lists.sourceforge.net
 */
package com.mxpdf.examples.objects.tables.alternatives;

import java.io.FileOutputStream;
import java.io.IOException;

import com.mxpdf.text.Document;
import com.mxpdf.text.DocumentException;
import com.mxpdf.text.Paragraph;
import com.mxpdf.text.Table;
import com.mxpdf.text.pdf.PdfWriter;

/**
 * A very simple Table example.
 */
public class MyFirstTable {

	/**
	 * A very simple Table example.
	 * 
	 * @param args
	 *            no arguments needed
	 */
	public static void main(String[] args) {
		System.out.println("My first table");
		// step 1: creation of a document-object
		Document document = new Document();
		try {
			// step 2:
			// we create a writer that listens to the document
			// and directs a PDF-stream to a file
			PdfWriter.getInstance(document,
					new FileOutputStream("MyFirstTable.pdf"));
			// step 3: we open the document
			document.open();
			// step 4: we create a table and add it to the document
			Table table = new Table(2, 2); // 2 rows, 2 columns
			table.addCell("0.0");
			table.addCell("0.1");
			table.addCell("1.0");
			table.addCell("1.1");
			document.add(table);
			document.add(new Paragraph("converted to PdfPTable:"));
			table.setConvert2pdfptable(true);
			document.add(table);
		} catch (DocumentException de) {
			System.err.println(de.getMessage());
		} catch (IOException ioe) {
			System.err.println(ioe.getMessage());
		}
		// step 5: we close the document
		document.close();
	}
}