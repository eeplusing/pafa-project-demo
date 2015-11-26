package com.ring.front.util;

import java.io.FileOutputStream;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

/**
 * @PDF生产工具
 * @author Bill
 * @createtime : 2015年3月30日
 */
public class PdfUtil {
	protected static Log logger = LogFactory.getLog(PdfUtil.class);
	//PDF文档存放地址
	private final static String pdf_document_dir = PropertiesConfigUtil.getProperty("pdf.document.dir");
	//image文件存放地址
	private final static String image_file_dir = PropertiesConfigUtil.getProperty("image.file.dir");
	// 表头
	public static final String[] tableHeader = { "项目名称\nProject Name", "天洋广场", "项目编号\nProject No.", "", "区域\nArea", "建筑外立面", "日期\nDate", "2012/12/22" };
	// 数据表字段数
	private static final int colNumber = 8;
	// 表格的设置
	private static final float spacing = 0.5f;
	// 表格的设置
	private static final float padding = 5;
	
	/**
	 * @param pdfId
	 */
	public static void buildPdf(String pdfId) {
		logger.info("开始创建PDF文档，文档存放位置：" + pdf_document_dir);
		logger.info("image文件存放位置：" + image_file_dir);
		// 创建文Pdf文挡50, 50, 50,50左右上下距离
		Document document = new Document(PageSize.A4);
		try {
			// 使用PDFWriter进行写文件操作
			PdfWriter.getInstance(document, new FileOutputStream(pdf_document_dir+pdfId+".pdf"));
			document.open();
			// 中文字体
			BaseFont bfChinese = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
			Font fontChinese = new Font(bfChinese, 7, Font.BOLD);
			Font fontChinese8 = new Font(bfChinese, 8, Font.BOLD);
			/**一，创建第一文本行*/
			Paragraph pf = new Paragraph("LIGHTING SPECIFICATION 灯具规范\n\n", fontChinese);
			pf.setAlignment(Element.ALIGN_LEFT);
			pf.setSpacingAfter(10f);
			/**二，创建第一张表*/
			// 创建有colNumber(8)列的表格
			PdfPTable datatable1 = new PdfPTable(colNumber);
			// 定义表格的宽度
			int[] cellsWidth = { 5, 4, 4, 4, 5, 5, 5, 5 };
			datatable1.setWidths(cellsWidth);
			// 表格的宽度百分比
			datatable1.setWidthPercentage(100);
			datatable1.getDefaultCell().setPadding(padding);
			datatable1.getDefaultCell().setBorderWidth(spacing);
			// 设置表格的底色
			datatable1.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
			datatable1.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			// 添加表头元素
			for (int i = 0; i < colNumber; i++) {
				datatable1.addCell(new Paragraph(tableHeader[i], fontChinese8));
			}
			
			/**三，创建第二张表*/
			PdfPTable datatable2 = new PdfPTable(6);
			// 定义表格的宽度
			int[] cellsWidth2 = { 5, 6, 6, 6, 6, 6 };
			datatable2.setWidths(cellsWidth2);
			// 表格的宽度百分比
			datatable2.setWidthPercentage(100);
			datatable2.getDefaultCell().setPadding(padding);
			datatable2.getDefaultCell().setBorderWidth(spacing);
			// 设置表格的底色
			datatable2.getDefaultCell().setBackgroundColor(BaseColor.WHITE);
			datatable2.getDefaultCell().setHorizontalAlignment(Element.ALIGN_LEFT);
			// 添加表头元素
			PdfPCell cell = new PdfPCell(new Paragraph("灯具说明 Fixture Illustration", fontChinese8));
			cell.setPadding(2*padding);
			cell.setBackgroundColor(BaseColor.GRAY);
			cell.setColspan(6);
			cell.setHorizontalAlignment(Element.ALIGN_CENTER);
			datatable2.addCell(cell);
			datatable2.completeRow();
			// 添加子元素
			datatable2.addCell(new Paragraph("灯具编号\nCode", fontChinese8));
			PdfPCell cell11 = new PdfPCell(new Paragraph("FL7", fontChinese8));
			cell11.setColspan(2);
			datatable2.addCell(cell11);
			datatable2.addCell(new Paragraph("灯具名称\nDescription", fontChinese8));
			PdfPCell cell12 = new PdfPCell(new Paragraph("嵌入式下照筒灯", fontChinese8));
			cell12.setColspan(2);
			datatable2.addCell(cell12);
			datatable2.completeRow();
			datatable2.addCell(new Paragraph("光源\nLight Source", fontChinese8));
			PdfPCell cell21 = new PdfPCell(new Paragraph("MH 35W/3000K/FL", fontChinese8));
			cell21.setColspan(2);
			datatable2.addCell(cell21);
			datatable2.addCell(new Paragraph("品牌型号\nBrand Name/Ref. No.", fontChinese8));
			PdfPCell cell22 = new PdfPCell(new Paragraph("AD 1002004.000", fontChinese8));
			cell22.setColspan(2);
			datatable2.addCell(cell22);
			datatable2.completeRow();
			datatable2.addCell(new Paragraph("灯体\nHousing", fontChinese8));
			PdfPCell cell31 = new PdfPCell(new Paragraph("高压铸铝合金灯体", fontChinese8));
			cell31.setColspan(2);
			datatable2.addCell(cell31);
			datatable2.addCell(new Paragraph("反射器\nReflector", fontChinese8));
			PdfPCell cell32 = new PdfPCell(new Paragraph("阳极铝反射器", fontChinese8));
			cell32.setColspan(2);
			datatable2.addCell(cell32);
			datatable2.completeRow();
			datatable2.addCell(new Paragraph("饰面\nFinish", fontChinese8));
			PdfPCell cell41 = new PdfPCell(new Paragraph("不锈钢面盖", fontChinese8));
			cell41.setColspan(2);
			datatable2.addCell(cell41);
			datatable2.addCell(new Paragraph("配件\nAccessories", fontChinese8));
			PdfPCell cell42 = new PdfPCell(new Paragraph("嵌入式安装", fontChinese8));
			cell42.setColspan(2);
			datatable2.addCell(cell42);
			datatable2.completeRow();
			datatable2.addCell(new Paragraph("防护等级\nIP", fontChinese8));
			PdfPCell cell51 = new PdfPCell(new Paragraph("IP 65", fontChinese8));
			cell51.setColspan(2);
			datatable2.addCell(cell51);
			datatable2.addCell(new Paragraph("变压器\nTransformer", fontChinese8));
			PdfPCell cell52 = new PdfPCell(new Paragraph("8206007.000", fontChinese8));
			cell52.setColspan(2);
			datatable2.addCell(cell52);
			datatable2.completeRow();
			datatable2.addCell(new Paragraph("尺寸\nDimensions", fontChinese8));
			datatable2.addCell(new Paragraph("开孔Cut Out Ø\n160mm Ø", fontChinese8));
			datatable2.addCell(new Paragraph("宽度Width\nmm", fontChinese8));
			datatable2.addCell(new Paragraph("高度Height\n206mm", fontChinese8));
			datatable2.addCell(new Paragraph("直径Diameter Ø\n180mm Ø", fontChinese8));
			datatable2.addCell(new Paragraph("长度Length\nmm", fontChinese8));
			datatable2.completeRow();
			datatable2.addCell(new Paragraph("厂商\nSupplier", fontChinese8));
			PdfPCell cell61 = new PdfPCell(new Paragraph("广州奥迪通用照明有限公司", fontChinese8));
			cell61.setColspan(2);
			datatable2.addCell(cell61);
			datatable2.addCell(new Paragraph("联系电话 TEL\n传真  FAX", fontChinese8));
			PdfPCell cell62 = new PdfPCell(new Paragraph("+86‐010‐87311356", fontChinese8));
			cell62.setColspan(2);
			datatable2.addCell(cell62);
			datatable2.completeRow();
			datatable2.addCell(new Paragraph("联系人\nContact", fontChinese8));
			PdfPCell cell71 = new PdfPCell(new Paragraph("钟先生 +86 18675732183", fontChinese8));
			cell71.setColspan(2);
			datatable2.addCell(cell71);
			datatable2.addCell(new Paragraph("地址\nAddress", fontChinese8));
			PdfPCell cell72 = new PdfPCell(new Paragraph("北京市丰台区南三环中路", fontChinese8));
			cell72.setColspan(2);
			datatable2.addCell(cell72);
			datatable2.completeRow();
			datatable2.addCell(new Paragraph("备注\nNote", fontChinese8));
			PdfPCell cell81 = new PdfPCell(new Paragraph("", fontChinese8));
			cell81.setColspan(5);
			datatable2.addCell(cell81);
			datatable2.completeRow();
			
			/**四，创建第二文本行*/
			Paragraph pf2 = new Paragraph("\nLighting Related Details\n\n", fontChinese8);
			pf2.setAlignment(Element.ALIGN_LEFT);
			pf2.setSpacingAfter(10f);
			
			/**五，创建第一张图片*/
			PdfPTable imageTable = new PdfPTable(2) ;
			int	hws1[] = {35, 65,}; 
			imageTable.setWidths(hws1); 
			imageTable.setWidthPercentage(90);
			imageTable.setHorizontalAlignment(Element.ALIGN_MIDDLE);
			imageTable.getDefaultCell().setPaddingBottom(20);
			Image jpeg1 = Image.getInstance(image_file_dir+"l_1.jpg");jpeg1.scalePercent(50);jpeg1.setAlignment(Image.LEFT);
			Image jpeg2 = Image.getInstance(image_file_dir+"l_2.jpg");jpeg2.scalePercent(50);jpeg2.setAlignment(Image.LEFT);
			Image jpeg3 = Image.getInstance(image_file_dir+"l_3.jpg");jpeg3.scalePercent(50);jpeg3.setAlignment(Image.LEFT);
			PdfPCell cell1 = new PdfPCell(jpeg1);
			cell1.disableBorderSide(1);
			cell1.disableBorderSide(2);
			cell1.disableBorderSide(4);
			cell1.disableBorderSide(8);
			imageTable.addCell(cell1) ;
			PdfPCell cell2 = new PdfPCell(jpeg2);
			cell2.disableBorderSide(1);
			cell2.disableBorderSide(2);
			cell2.disableBorderSide(4);
			cell2.disableBorderSide(8);
			imageTable.addCell(cell2) ;
			imageTable.completeRow();
			PdfPCell cell000 = new PdfPCell(new Paragraph("\n\n"));
			cell000.setColspan(2);
			cell000.disableBorderSide(1);
			cell000.disableBorderSide(2);
			cell000.disableBorderSide(4);
			cell000.disableBorderSide(8);
			imageTable.addCell(cell000) ;
			imageTable.completeRow();
			PdfPCell cell3 = new PdfPCell(jpeg3);
			cell3.disableBorderSide(1);
			cell3.disableBorderSide(2);
			cell3.disableBorderSide(4);
			cell3.disableBorderSide(8);
			imageTable.addCell(cell3) ;
			PdfPCell cell4 = new PdfPCell();
			cell4.disableBorderSide(1);
			cell4.disableBorderSide(2);
			cell4.disableBorderSide(4);
			cell4.disableBorderSide(8);
			imageTable.addCell(cell4) ;
			imageTable.completeRow();
			document.add(pf);
			document.add(datatable1);
			document.add(new Paragraph("\n"));
			document.add(datatable2);
			document.add(pf2);
			document.add(imageTable);
			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		buildPdf("lighting");
	}
}
