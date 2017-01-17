/*
 * $Id: ImageMasks.java 3373 2008-05-12 16:21:24Z xlv $
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
package com.mxpdf.examples.objects.images;

import java.io.FileOutputStream;

import com.mxpdf.text.Document;
import com.mxpdf.text.Image;
import com.mxpdf.text.PageSize;
import com.mxpdf.text.Paragraph;
import com.mxpdf.text.pdf.PdfContentByte;
import com.mxpdf.text.pdf.PdfWriter;

/**
 * Applying a mask to an Image.
 */
public class ImageMasks {
    
    /**
     * Applying masks to images.
     * @param args no arguments needed
     */
    public static void main(String[] args) {
        System.out.println("masked images");
        
        Document document = new Document(PageSize.A4, 50, 50, 50, 50);
        try {
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("maskedImages.pdf"));
            
            document.open();
            Paragraph p = new Paragraph("Some text behind a masked image.");
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            document.add(p);
            PdfContentByte cb = writer.getDirectContent();
            byte maskr[] = {(byte)0x3c, (byte)0x7e, (byte)0xe7, (byte)0xc3, (byte)0xc3, (byte)0xe7, (byte)0x7e, (byte)0x3c};
            Image mask = Image.getInstance(8, 8, 1, 1, maskr);
            mask.makeMask();
            mask.setInverted(true);
            Image image = Image.getInstance("otsoe.jpg");
            image.setImageMask(mask);
            image.setAbsolutePosition(60, 550);
            // explicit masking
            cb.addImage(image);
            // stencil masking
            cb.setRGBColorFill(255, 0, 0);
            cb.addImage(mask, mask.getScaledWidth() * 8, 0, 0, mask.getScaledHeight() * 8, 100, 450);
            cb.setRGBColorFill(0, 255, 0);
            cb.addImage(mask, mask.getScaledWidth() * 8, 0, 0, mask.getScaledHeight() * 8, 100, 400);
            cb.setRGBColorFill(0, 0, 255);
            cb.addImage(mask, mask.getScaledWidth() * 8, 0, 0, mask.getScaledHeight() * 8, 100, 350);
            document.close();
        }
        catch (Exception de) {
            de.printStackTrace();
        }
    }

}