package com.frame.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import org.apache.poi.sl.usermodel.TextParagraph;
import org.apache.poi.xslf.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.apache.poi.xwpf.model.XWPFHeaderFooterPolicy;
import org.apache.poi.xwpf.usermodel.*;
import org.openxmlformats.schemas.drawingml.x2006.main.*;
import org.openxmlformats.schemas.drawingml.x2006.spreadsheetDrawing.CTShape;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * @author: chenyuntao
 **/
public class WatermarkUtil {
    /**
     * word加水印
     */
    public static void wordWatermark(String filePath, String outFileName) {
        try {
            // 加载Word文档
            FileInputStream fileInputStream = new FileInputStream(filePath);
            XWPFDocument document = new XWPFDocument(fileInputStream);

            // 创建水印
            XWPFHeaderFooterPolicy headerFooterPolicy = document.getHeaderFooterPolicy();
            if (headerFooterPolicy == null) {
                headerFooterPolicy = document.createHeaderFooterPolicy();
            }
            XWPFHeader header = headerFooterPolicy.getDefaultHeader();
            if (header == null) {
                header = headerFooterPolicy.createHeader(XWPFHeaderFooterPolicy.DEFAULT);
            }
            XWPFParagraph watermarkParagraph = header.getParagraphArray(0);
            if (watermarkParagraph == null) {
                watermarkParagraph = header.createParagraph();
            }
            watermarkParagraph.setAlignment(ParagraphAlignment.CENTER);
            XWPFRun watermarkRun = watermarkParagraph.createRun();
            watermarkRun.setText("水印文字");
            watermarkRun.setFontSize(72);
            watermarkRun.setColor("FF0000");
            watermarkRun.setBold(true);

            // 保存修改后的Word文档
            FileOutputStream fileOutputStream = new FileOutputStream(outFileName + ".docx");
            document.write(fileOutputStream);

            // 关闭流
            fileOutputStream.close();
            fileInputStream.close();

            System.out.println("水印添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * excel加水印
     */
    public static void excelWatermark(String filePath, String outFileName) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("With Watermark");

        // 加载文件
        BufferedImage image = ImageIO.read(new File(filePath));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ImageIO.write(image, "png", byteArrayOutputStream);
        int pictureIndex = workbook.addPicture(byteArrayOutputStream.toByteArray(), XSSFWorkbook.PICTURE_TYPE_PNG);

        // 创建绘图对象
        XSSFDrawing drawing = sheet.createDrawingPatriarch();

        // 添加图片形状
        XSSFClientAnchor anchor = drawing.createAnchor(0, 0, 0, 0, 0, 0, 5, 5);
        XSSFPicture picture = drawing.createPicture(anchor, pictureIndex);

        CTBlipFillProperties blipFill = picture.getCTPicture().getBlipFill();

        //重复添加水印
        CTTileInfoProperties tile = blipFill.addNewTile();
        CTStretchInfoProperties stretch = blipFill.addNewStretch();
        stretch.addNewFillRect();

        //输出到文件
        try (FileOutputStream fileOut = new FileOutputStream(outFileName + ".xlsx")) {
            workbook.write(fileOut);
        }
        workbook.close();
    }


    /**
     * ppt加水印
     */
    public static void pptWatermark(String filePath, String outFileName) {
        try {
            // 加载PPT文档
            FileInputStream fileInputStream = new FileInputStream(filePath);
            XMLSlideShow slideShow = new XMLSlideShow(fileInputStream);

            // 创建水印
            for (XSLFSlide slide : slideShow.getSlides()) {
                XSLFTextBox watermarkTextBox = slide.createTextBox();
                watermarkTextBox.setAnchor(new java.awt.Rectangle(0, 0, 0, 0));

                XSLFTextParagraph paragraph = watermarkTextBox.addNewTextParagraph();
                paragraph.setTextAlign(TextParagraph.TextAlign.CENTER);

                XSLFTextRun textRun = paragraph.addNewTextRun();
                textRun.setText("水印文字");
                textRun.setFontSize(48.0);
                textRun.setBold(true);
                textRun.setItalic(true);
                textRun.setFontColor(java.awt.Color.GRAY);

                CTShape shape = (CTShape) watermarkTextBox.getXmlObject();
                CTTextBody body = shape.getTxBody();
                body.getBodyPr().setAnchor(STTextAnchoringType.CTR);
                body.getBodyPr().setWrap(STTextWrappingType.SQUARE);
            }

            // 保存修改后的PPT文档
            FileOutputStream fileOutputStream = new FileOutputStream(outFileName + ".pptx");
            slideShow.write(fileOutputStream);

            // 关闭流
            fileOutputStream.close();
            fileInputStream.close();

            System.out.println("水印添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * pdf加水印
     */
    public static void pdfWatermark(String filePath, String outFileName) {
        try {
            // 加载PDF文档
            PdfReader reader = new PdfReader(filePath);

            // 创建输出的PDF文档
            FileOutputStream fileOutputStream = new FileOutputStream(outFileName + ".pdf");
            PdfStamper stamper = new PdfStamper(reader, fileOutputStream);

            // 设置水印文本样式
            Font font = new Font(Font.FontFamily.HELVETICA, 48, Font.BOLD, BaseColor.GRAY);

            // 添加水印
            int pageCount = reader.getNumberOfPages();
            for (int i = 1; i <= pageCount; i++) {
                PdfContentByte content = stamper.getOverContent(i);
                ColumnText.showTextAligned(content, Element.ALIGN_CENTER, new Phrase("水印文字", font),
                        PageSize.A4.getWidth() / 2, PageSize.A4.getHeight() / 2, 45);
            }

            // 关闭流
            stamper.close();
            reader.close();
            fileOutputStream.close();

            System.out.println("水印添加成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
