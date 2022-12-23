package com.project2.serviceImpl;

import java.awt.Color;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.PaperSize;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfTable;
import com.lowagie.text.pdf.PdfWriter;
import com.project2.bindings.CitizenPlan;
import com.project2.bindings.SearchRequest;
import com.project2.repository.CitizenPlanRepository;
import com.project2.service.CitizenService;

@Service
public class CitizenServiceImpl implements CitizenService {

	@Autowired
	private CitizenPlanRepository repo;

	@Override
	public List<String> getPlanName() {

		return repo.getPlanName();
	}

	@Override
	public List<String> getPlanStatus() {

		return repo.getPlanStatus();
	}

	@Override
	public List<CitizenPlan> getCitixenPlan(SearchRequest request) {
		CitizenPlan entity = new CitizenPlan();

		if (request.getPlanName() != null && !request.equals("")) {
			entity.setPlanName(request.getPlanName());
		}
		if (request.getPlanStatus() != null && !request.equals("")) {
			entity.setPlanStatus(request.getPlanStatus());
		}
		if (request.getGender() != null && !request.equals("")) {
			entity.setGenrder(request.getGender());
		}
		Example<CitizenPlan> e = Example.of(entity);
		List<CitizenPlan> citizenlist = repo.findAll(e);
		return citizenlist;
	}

	@Override
	public void exportExcel(HttpServletResponse reponse) throws Exception {
		List<CitizenPlan> citizenplanlist = repo.findAll();
		XSSFWorkbook workbook = new XSSFWorkbook();

		XSSFSheet sheet = workbook.createSheet("Citizenplan info");
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("ID");
		row.createCell(1).setCellValue("Name");
		row.createCell(2).setCellValue("Gender");
		row.createCell(3).setCellValue("PhNo");
		row.createCell(4).setCellValue("SSN");
		row.createCell(5).setCellValue("PlanNmae");
		row.createCell(6).setCellValue("PlanStatus");

		int datRowIndex = 1;
		for (CitizenPlan citizenplan : citizenplanlist) {
			XSSFRow datarow = sheet.createRow(datRowIndex);
			datarow.createCell(0).setCellValue(citizenplan.getCid());
			datarow.createCell(1).setCellValue(citizenplan.getCname());
			datarow.createCell(2).setCellValue(citizenplan.getGenrder());
			datarow.createCell(3).setCellValue(citizenplan.getPhno());
			datarow.createCell(4).setCellValue(citizenplan.getSsn());
			datarow.createCell(5).setCellValue(citizenplan.getPlanName());
			datarow.createCell(6).setCellValue(citizenplan.getPlanStatus());
			datRowIndex++;
		}
		ServletOutputStream ops = reponse.getOutputStream();
		workbook.write(ops);
		workbook.close();
		ops.close();

	}

	@Override
	public void exportPdf(HttpServletResponse reponse) throws Exception {
		List<CitizenPlan> citizenplanlist = repo.findAll();
		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, reponse.getOutputStream());
		document.open();
		Font font = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		font.setSize(18);
		font.setColor(Color.BLUE);

		Paragraph p = new Paragraph("List Of Citixens" + font);
		p.setAlignment(Paragraph.ALIGN_CENTER);
		document.add(p);

		PdfPTable table = new PdfPTable(7);
		table.setWidthPercentage(100f);
		table.setWidths(new float[] { 1.5f, 3.5f, 3.0f, 4.0f, 4.0f, 3.5f, 3.5f });
		table.setSpacingBefore(10);

		for (CitizenPlan citizenplan : citizenplanlist) {
			table.addCell(String.valueOf(citizenplan.getCid()));
			table.addCell(citizenplan.getCname());
			table.addCell(citizenplan.getGenrder());
			table.addCell(String.valueOf(citizenplan.getPhno()));
			table.addCell(String.valueOf(citizenplan.getSsn()));
			table.addCell(citizenplan.getPlanName());
			table.addCell(citizenplan.getPlanStatus());
		}
		document.add(table);
		document.close();

	}

}
