/*
 * $Id: ContentGroups.java 3838 2009-04-07 18:34:15Z mstorer $
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
package com.mxpdf.examples.directcontent.optionalcontent;

import java.awt.Color;
import java.io.FileOutputStream;

import com.mxpdf.text.Document;
import com.mxpdf.text.Element;
import com.mxpdf.text.Font;
import com.mxpdf.text.PageSize;
import com.mxpdf.text.Phrase;
import com.mxpdf.text.pdf.ColumnText;
import com.mxpdf.text.pdf.PdfArray;
import com.mxpdf.text.pdf.PdfContentByte;
import com.mxpdf.text.pdf.PdfDictionary;
import com.mxpdf.text.pdf.PdfLayer;
import com.mxpdf.text.pdf.PdfLayerMembership;
import com.mxpdf.text.pdf.PdfName;
import com.mxpdf.text.pdf.PdfOCProperties;
import com.mxpdf.text.pdf.PdfObject;
import com.mxpdf.text.pdf.PdfString;
import com.mxpdf.text.pdf.PdfWriter;

/**
 * Demonstrates how to group optional content.
 */
public class ContentGroups {
	   
    /**
     * Demonstrates how to group optional content.
     * @param args no arguments needed
     */
    public static void main(String[] args) {
        System.out.println("Grouping optional content");
        try {
        	// step 1
            Document document = new Document(PageSize.A4, 50, 50, 50, 50);
            // step 2
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("contentgroups.pdf"));
            writer.setPdfVersion(PdfWriter.VERSION_1_5);
            writer.setViewerPreferences(PdfWriter.PageModeUseOC);
            // step 3
            document.open();
            // step 4
            PdfContentByte cb = writer.getDirectContent();
            Phrase explanation = new Phrase("Layer grouping", new Font(Font.HELVETICA, 20, Font.BOLD, Color.red));
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, explanation, 50, 650, 0);
            PdfLayer l1 = new PdfLayer("Layer 1", writer);
            PdfLayer l2 = new PdfLayer("Layer 2", writer);
            PdfLayer l3 = new PdfLayer("Layer 3", writer);
            PdfLayerMembership m1 = new PdfLayerMembership(writer);
            m1.addMember(l2);
            m1.addMember(l3);
            Phrase p1 = new Phrase("Text in layer 1");
            Phrase p2 = new Phrase("Text in layer 2 or layer 3");
            Phrase p3 = new Phrase("Text in layer 3");
            cb.beginLayer(l1);
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, p1, 50, 600, 0);
            cb.endLayer();
            cb.beginLayer(m1);
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, p2, 50, 550, 0);
            cb.endLayer();
            cb.beginLayer(l3);
            ColumnText.showTextAligned(cb, Element.ALIGN_LEFT, p3, 50, 500, 0);
            cb.endLayer();
            cb.sanityCheck();
            
            PdfOCProperties p = writer.getOCProperties();
            PdfArray order = new PdfArray();
            order.add(l1.getRef());
            PdfArray group = new PdfArray();
            group.add(new PdfString("A group of two", PdfObject.TEXT_UNICODE));
            group.add(l2.getRef());
            group.add(l3.getRef());
            order.add(group);
            PdfDictionary d = new PdfDictionary();
            d.put(PdfName.ORDER, order);
            p.put(PdfName.D, d);
            // step 5
            document.close();
        }
        catch(Exception de) {
            de.printStackTrace();
        }
    }
}