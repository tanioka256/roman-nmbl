/*
  Roman Nombre setter

  Copyright 2012 Kenshi Muto <kmuto@debian.org>

  Permission is hereby granted, free of charge, to any person obtaining a
  copy of this software and associated documentation files (the "Software"),
  to deal in the Software without restriction, including without limitation
  the rights to use, copy, modify, merge, publish, distribute, sublicense,
  and/or sell copies of the Software, and to permit persons to whom the
  Software is furnished to do so, subject to the following conditions:
  
  The above copyright notice and this permission notice shall be included in
  all copies or substantial portions of the Software.
  
  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.  IN NO EVENT SHALL
  SOFTWARE IN THE PUBLIC INTEREST, INC. BE LIABLE FOR ANY CLAIM, DAMAGES OR
  OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE,
  ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER
  DEALINGS IN THE SOFTWARE.

  javac -classpath /usr/share/java/itext.jar RomanNmbl.java
  java -classpath /usr/share/java/itext.jar:. RomanNmbl in.pdf out.pdf 10
 */
import java.lang.*;
import java.io.*;

/* FIXME: Please replace them with com.itextpdf if you have another iText version. */
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.*;

class RomanNmbl {
    public static void main(String args[]) {
        if (args.length != 4) {
            usage();
            return;
        }
        
        try {
            PdfReader reader;
            reader = new PdfReader(args[0]);
            int maxpage = reader.getNumberOfPages();
            
            PdfStamper stamper;
            stamper = new PdfStamper(reader, new FileOutputStream(args[1]));
            
            PdfPageLabels labels = new PdfPageLabels();

            int start_page = 1;
            if (start_page <= maxpage)
                labels.addPageLabel(start_page, PdfPageLabels.EMPTY);

            start_page += Integer.parseInt(args[2]);
            if (start_page <= maxpage)
                labels.addPageLabel(start_page, PdfPageLabels.LOWERCASE_ROMAN_NUMERALS);

            start_page += Integer.parseInt(args[3]);
            if (start_page <= maxpage)
                labels.addPageLabel(start_page, PdfPageLabels.DECIMAL_ARABIC_NUMERALS);
            
            stamper.getWriter().setPageLabels(labels);
            stamper.close();
        } catch (IOException e) {
            // FIXME: Better error handling.
            System.out.println(e);
            return;
        } catch (DocumentException e) {
            System.out.println(e);
            return;
        }
    }
    
    static void usage() {
        System.out.println("java -classpath /usr/share/java/itext5.jar:. RomanNmbl InPDFfile OutPDFfile NumPages-Unnumbered NumPages-Roman-Numerals");
    }
}
