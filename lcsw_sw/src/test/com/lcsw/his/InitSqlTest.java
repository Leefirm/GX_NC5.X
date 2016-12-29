package com.lcsw.his;

import nc.vo.jcom.lang.StringUtil;
import nc.vo.obm.util.PoiUtils;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

public class InitSqlTest {
	public static void main(String[] args) {
		try {
			printSql("c:\\绿城水务sqlser对照表.xls", 3, 1, 2);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void printSql(String path, int sheetIndex, int ncColumn,
			int hisColumn) throws Exception {
		HSSFWorkbook readXlsFile = PoiUtils.getWorkbook(path);
		HSSFSheet sheet = readXlsFile.getSheetAt(sheetIndex);
		int rows = sheet.getPhysicalNumberOfRows();

		System.out.print("select ");

		for (int i = 0; i < rows; i++) {
			HSSFRow row = sheet.getRow(i);

			if(row == null) {
				continue;
			}
			// 检查sqlserver字段
			HSSFCell hiscell = row.getCell((short) hisColumn);
			if (hiscell == null) {
				continue;
			}
			HSSFRichTextString his = hiscell.getRichStringCellValue();
			if (his == null || StringUtil.isEmptyWithTrim(his.toString())) {
				continue;
			}

			// 目前nc字段
			HSSFCell nccell = row.getCell((short) ncColumn);
			if (nccell == null) {
//				System.out.println(" nc字段不可能是空cell： row = " + (i + 1));
//				break;
				continue;
			}
			HSSFRichTextString nc = nccell.getRichStringCellValue();
			if (nc == null || StringUtil.isEmptyWithTrim(nc.toString())) {
//				System.out.println(" nc字段不可能是空： row = " + (i + 1));
//				break;
				continue;
			}
			//only NC
		//	System.out.print(" " + nc.toString() + ", ");
		//	System.out.println();
			System.out.print(his.toString() + " " + nc.toString() + ", ");
		}
		System.out.print(" 1 notused");
	}
}
