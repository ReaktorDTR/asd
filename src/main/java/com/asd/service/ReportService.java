package com.asd.service;

import com.asd.mapper.session.RequestMapper;
import com.asd.model.session.Request;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
@RequiresPermissions("reports")
public class ReportService {

	private static final String SHEET_NAME = "Report";

	@Autowired
	private RequestMapper requestMapper;

	@Autowired
	private EmailService emailService;

	/**
	 * Builds request report and send to email as attachment.
	 *
	 * @param email email recipient
	 * @param limit limit
	 */
	public void getRequestReport(String email, Integer limit) throws IOException {
		List<Request> requests = requestMapper.getRequests(limit);
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(SHEET_NAME);
		createRequestReportHeaders(sheet);
		int columnsCount = populateRequestReport(requests, sheet);
		resizeColumns(sheet, columnsCount);
		emailService.sendEmailMessage(email,
				"Request report",
				"Report created and appended to mail.",
				"RequestReport.xlsx",
				getInputStreamSource(workbook).orElseThrow(IOException::new));
	}

	/**
	 * Creates headers for request report.
	 *
	 * @param sheet poi sheet
	 */
	private static void createRequestReportHeaders(Sheet sheet) {
		Row row = sheet.createRow(0);
		String[] headers = {"URL", "Method", "Params", "Session ID", "Date Opened", "Date Closed", "User Name", "Group Name", "Country",
				"Language", "Location", "Latitude", "Longitude"};

		for (int column = 0; column < headers.length; column++) {
			Cell cell = row.createCell(column);
			cell.setCellValue(headers[column]);
		}
	}

	/**
	 * Populates sheet with data about users request.
	 *
	 * @param requests list of user requests
	 * @param sheet    poi sheet
	 * @return number of populated columns
	 */
	private static int populateRequestReport(List<Request> requests, Sheet sheet) {
		int rowNum = 1;
		int colNum = 0;
		for (Request request : requests) {
			Row row = sheet.createRow(rowNum++);
			colNum = 0;
			row.createCell(colNum++).setCellValue(request.getUrl());
			row.createCell(colNum++).setCellValue(request.getMethod());
			row.createCell(colNum++).setCellValue(request.getParams());
			row.createCell(colNum++).setCellValue(request.getSession().getId());
			row.createCell(colNum++).setCellValue(request.getSession().getDateOpened().toString());
			row.createCell(colNum++).setCellValue(request.getSession().getDateOpened().toString());
			row.createCell(colNum++).setCellValue(request.getSession().getUser().getUserName());
			row.createCell(colNum++).setCellValue(request.getSession().getUser().getGroup().getGroupName());
			row.createCell(colNum++).setCellValue(request.getSession().getUser().getLocation().getCountry().getCountryName());
			row.createCell(colNum++).setCellValue(request.getSession().getUser().getLocation().getCountry().getLanguage());
			row.createCell(colNum++).setCellValue(request.getSession().getUser().getLocation().getLocationName());
			row.createCell(colNum++).setCellValue(request.getSession().getUser().getLocation().getLatitude());
			row.createCell(colNum).setCellValue(request.getSession().getUser().getLocation().getLongitude());
		}
		return colNum;
	}

	/**
	 * Resize columns.
	 *
	 * @param sheet        poi sheet
	 * @param columnsCount number of columns on sheet
	 */
	private static void resizeColumns(Sheet sheet, int columnsCount) {
		for (int index = 0; index < columnsCount; index++) {
			sheet.autoSizeColumn(index);
		}
	}

	/**
	 * Converts poi workbook into input stream.
	 *
	 * @param workbook poi workbook
	 * @return source input stream
	 */
	private static Optional<InputStreamSource> getInputStreamSource(XSSFWorkbook workbook) {
		byte[] bytes = null;
		try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {
			workbook.write(bos);
			bytes = bos.toByteArray();
		} catch (IOException e) {
			log.error("Can't convert workbook into input stream", e);
		}
		Optional<InputStreamSource> resource = Optional.empty();
		if (Objects.nonNull(bytes)) {
			resource = Optional.of(new ByteArrayResource(bytes));
		}
		return resource;
	}
}