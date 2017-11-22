package alibaba;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


public class ScrapperThread implements Runnable {
	String key;
	Thread thread;
	
	public ScrapperThread(String key) {
		
		this.key = key;
		this.thread = new Thread(this,key);
		thread.start();
		
	
	}

	@Override
	public void run() {
		

	

	
		String url = "https://www.alibaba.com/trade/search?viewType=L&n=50&indexArea=company_en&keyword="+key+"&f1=y&Country=HK";
		ArrayList<Supplier> supplierList = new ArrayList<Supplier>();
		int page = 1;
		int crawlRow = 1;
		while (true) {
			System.out.println("Inside page "+page + " of " + key );
			Document document = null;
			try {
	
				document = Jsoup.connect(url).timeout(Integer.MAX_VALUE).get();
			} catch (Exception e1) {

				e1.printStackTrace();
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
				
					e.printStackTrace();
				}
				//page--;
				continue;
			}
			Elements elements = document.getElementsByClass("title ellipsis");
			if (elements.size() > 0)
				elements.remove(0);
			System.out.println("Number of links found in page:" +page+ " of "+key +" is: "+elements.size());
			for (Element element : elements) {

				String businessType = "No Information";
				String location = "No Information";
				String mainProducts = "No Information";
				String ownerShip = "No Information";
				String totalEmployees = "No Information";
				String totalAnnualRevenue = "No Information";
				String yearEstablished = "No Information";
				String exportPercentage = "No Information";
				String exportMode = "No Information";
				String languageSpoken = "No Information";
				String employeeOfTradeDept = "No Information";
				String companyName = "No Information";
				String websiteURL = "No Information";
				String websiteOnALiURL = "No Information";
				String supplierType = "Normal";
				String contact = "No Information";
				String contactImageLink = "No Image found";
				String address = "No Information";
				Element childElement = element.child(0);
				String link = childElement.attr("href");
				
			
				String contactLink = link.split("company_profile")[0] + "contactinfo.html";

				Document linkPageDocument = null;
				Document contactPageDocument = null;
			
				try {
					linkPageDocument = Jsoup.connect(link).timeout(1000000).get();
					if(!contactLink.contains("www.alibabba.com/contactinfo.html") )
					contactPageDocument = Jsoup.connect(contactLink).timeout(1000000).get();
					else{
						continue;
					}
				} catch (Exception e) {

					e.printStackTrace();
					
						continue;
				}
			
				Element topTableElement = linkPageDocument.getElementsByClass("content-table").first();
				if (topTableElement != null) {
					Elements tableRows = topTableElement.select("tr");

					int tableRowsSize = tableRows.size();
						for (int i = 0; i < tableRowsSize; i++) {
							Element row = tableRows.get(i);
							if (row.select("th").size() == 0)
								continue;
							if (row.select("th").get(0).text().contains("Business Type")) {
								businessType = row.select("td").get(0).text();
							} else if (row.select("th").get(0).text().contains("Location")) {
								location = row.select("td").get(0).text();
							} else if (row.select("th").get(0).text().contains("Main Products")) {
								mainProducts = row.select("td").get(0).text();
							} else if (row.select("th").get(0).text().contains("Ownership")) {
								ownerShip = row.select("td").get(0).text();
								ownerShip = ownerShip.replace("View More", "");
							} else if (row.select("th").get(0).text().contains("Total Employees")) {
								totalEmployees = row.select("td").get(0).text();
							} else if (row.select("th").get(0).text().contains("Total Annual Revenue")) {
								totalAnnualRevenue = row.select("td").get(0).text();
							} else if (row.select("th").get(0).text().contains("Year Established")) {
								yearEstablished = row.select("td").get(0).text();
							}

						}
				}
			
			

				Element tradeTableContent = linkPageDocument.getElementsByClass("trade-content-table").first();
				if (tradeTableContent != null) {
				
					Elements tradeTableRows = tradeTableContent.select("tr");
						int tradeTableRowsSize = tradeTableRows.size();
							
						for (int i = 0; i < tradeTableRowsSize; i++) {
						
							Element row = tradeTableRows.get(i);
						
							if (row.select("td").size() == 0) {
								System.out.println("Blank row: " + row.text());
								continue;
							}
							if (row.select("td").get(0).text().contains("Export Percentage")) {
								exportPercentage = row.select("td").get(1).text();
							} else if (row.select("td").get(0).text().contains("Export Mode")) {
								exportMode = row.select("td").get(1).text();
							} else if (row.select("td").get(0).text().contains("Language Spoken")) {
								languageSpoken = row.select("td").get(1).text();
							} else if (row.select("td").get(0).text().contains("Trade")) {
								employeeOfTradeDept = row.select("td").get(1).text();
							}

						}
	
				}
				 
				Element contactElement = null;
				if(contactPageDocument.getElementsByAttributeValueContaining("content", "Company Contact Information").size()>0)
				 contactElement = contactPageDocument
						.getElementsByAttributeValueContaining("content", "Company Contact Information").get(0);
				else{
		//			continue;
				}

				String contactText = contactElement.attr("content");
				if (contactText.contains("contact details,")) {
					contact = contactText.substring(contactText.lastIndexOf("contact details,") + 17);
					address =  contactText.substring(0,contactText.lastIndexOf("contact details,")).replaceAll("Company Contact Information, ", "");
				}

				Element companyInfoTable = contactPageDocument.getElementsByClass("company-info-data table")
						.first();
				if (companyInfoTable != null) {
					Elements companyInfoRows = companyInfoTable.select("tr");

						int companyInfoRowsSize = companyInfoRows.size();
						for (int i = 0; i < companyInfoRowsSize; i++) {
							Element row = companyInfoRows.get(i);
							if (row.select("td").size() == 0) {
								System.out.println("Blank row: " + row.text());
								continue;
							}
							if (row.select("th").get(0).text().contains("Company Name")) {
								companyName = row.select("td").get(1).text();
							} else if (row.select("th").get(0).text().contains("Website")) {
								websiteURL = row.select("td").get(1).text();
							}

						}
					
				}
				
				if (contactPageDocument.getElementsByClass("card-icon icon-ggs").size() != 0) {
					supplierType = "Gold";
				}

				websiteOnALiURL = contactLink.substring(0, contactLink.lastIndexOf("/"));
				String imageLinkFull = contactPageDocument.getElementsByClass("shopsign-background util-clearfix")
						.get(0).attr("style");
				if (imageLinkFull.contains("(") && imageLinkFull.contains(")")) {
					contactImageLink = imageLinkFull.substring(imageLinkFull.indexOf("(") + 1,
							imageLinkFull.lastIndexOf(")"));

				} 

				System.out.println("Crawling "+key+": " + crawlRow++);
				Supplier supplier = new Supplier(businessType, location, mainProducts, ownerShip, totalEmployees,
						totalAnnualRevenue, yearEstablished, exportPercentage, exportMode, languageSpoken,
						employeeOfTradeDept, contact, companyName, websiteURL, websiteOnALiURL, supplierType,
						contactImageLink,address);
				supplierList.add(supplier);

			}

			if (document.getElementsByClass("next disable").size() > 0) {
				break;
			}
			Element element = document.getElementsByClass("next").last();

			if (element != null) {
				url = "https:" + element.attr("href");
				page++;
			} else {
				break;
			}
		}
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet(key);
		int count = 1;
		try {
			createFormat(workbook, sheet, key);
			for (Supplier supplier : supplierList) {

				writeData(workbook, sheet, supplier, key, count++);
				System.out.println("Writing "+key+": " +count);
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
	
		
	}
	public static void createFormat(XSSFWorkbook workbook, XSSFSheet sheet, String key) throws IOException {

		Row row = sheet.createRow(0);

		int columnCount = -1;

		row.createCell(++columnCount).setCellValue("Business type");
		row.createCell(++columnCount).setCellValue("Location");
		row.createCell(++columnCount).setCellValue("Main Products");

		row.createCell(++columnCount).setCellValue("Ownership");

		row.createCell(++columnCount).setCellValue("Total Employee");
		row.createCell(++columnCount).setCellValue("Total annual revenue");

		row.createCell(++columnCount).setCellValue("Year Established");

		row.createCell(++columnCount).setCellValue("Export Percentage");

		row.createCell(++columnCount).setCellValue("Export Mode");

		row.createCell(++columnCount).setCellValue("Language");

		row.createCell(++columnCount).setCellValue("Number of employee at trade");

		row.createCell(++columnCount).setCellValue("Contact");
		row.createCell(++columnCount).setCellValue("Address");
		row.createCell(++columnCount).setCellValue("Company Name");

		row.createCell(++columnCount).setCellValue("Website URL");

		row.createCell(++columnCount).setCellValue("Website on alibaba.com");

		row.createCell(++columnCount).setCellValue("Supplier Type");

		row.createCell(++columnCount).setCellValue("Contact Image Link");
		try (FileOutputStream outputStream = new FileOutputStream("output/"+key.replaceAll(" ", "") + ".xlsx")) {
			workbook.write(outputStream);

		}

	}

	public static void writeData(XSSFWorkbook workbook, XSSFSheet sheet, Supplier supplier, String key, int rowIndex)
			throws IOException {

		Row row = sheet.createRow(rowIndex);

		int columnCount = -1;

		row.createCell(++columnCount).setCellValue(supplier.getBusinessType());

		row.createCell(++columnCount).setCellValue(supplier.getLocation());
		row.createCell(++columnCount).setCellValue(supplier.getMainProduct());

		row.createCell(++columnCount).setCellValue(supplier.getOwnerShip());

		row.createCell(++columnCount).setCellValue(supplier.getTotalEmployee());

		row.createCell(++columnCount).setCellValue(supplier.getAnnualRevenue());

		row.createCell(++columnCount).setCellValue(supplier.getYearEstablished());

		row.createCell(++columnCount).setCellValue(supplier.getExportPercentage());
		row.createCell(++columnCount).setCellValue(supplier.getExportMode());

		row.createCell(++columnCount).setCellValue(supplier.getLanguage());

		row.createCell(++columnCount).setCellValue(supplier.getEmployeeTradeDept());
		row.createCell(++columnCount).setCellValue(supplier.getContact());
		row.createCell(++columnCount).setCellValue(supplier.getAddress());
		row.createCell(++columnCount).setCellValue(supplier.getCompanyName());
		row.createCell(++columnCount).setCellValue(supplier.getWebsiteURL());
		row.createCell(++columnCount).setCellValue(supplier.getWebsiteOnALiURL());
		row.createCell(++columnCount).setCellValue(supplier.getSupplierType());
		row.createCell(++columnCount).setCellValue(supplier.getContactImageLink());
		try (FileOutputStream outputStream = new FileOutputStream("output/"+key.replaceAll(" ", "") + ".xlsx")) {
			workbook.write(outputStream);

		}

	}

}
