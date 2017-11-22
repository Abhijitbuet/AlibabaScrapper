package alibaba;

public class Supplier {
	private String businessType;
	private String  location;
     private String mainProduct;
     private String ownerShip;
     private String totalEmployee;
     private String annualRevenue;
     private String yearEstablished;
     private String exportPercentage;
     private String exportMode;
     private String language;
     private String employeeTradeDept;
     private String contact;
     private String companyName;
     private String websiteURL;
     private String websiteOnALiURL;
     private String supplierType;
     private String contactImageLink;
     private String address;

	public Supplier(String businessType, String location, String mainProduct, String ownerShip, String totalEmployee,
			String annualRevenue, String yearEstablished, String exportPercentage, String exportMode, String language,
			String employeeTradeDept, String contact, String companyName, String websiteURL, String websiteOnALiURL,String type, String contactImageLink, String address) {
	
		this.businessType = businessType;
		this.location = location;
		this.mainProduct = mainProduct;
		this.ownerShip = ownerShip;
		this.totalEmployee = totalEmployee;
		this.annualRevenue = annualRevenue;
		this.yearEstablished = yearEstablished;
		this.exportPercentage = exportPercentage;
		this.exportMode = exportMode;
		this.language = language;
		this.employeeTradeDept = employeeTradeDept;
		this.contact = contact;
		this.companyName = companyName;
		this.websiteURL = websiteURL;
		this.websiteOnALiURL = websiteOnALiURL;
		this.supplierType = type;
		this.contactImageLink = contactImageLink;
		this.address = address;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactImageLink() {
		return contactImageLink;
	}

	public void setContactImageLink(String contactImageLink) {
		this.contactImageLink = contactImageLink;
	}

	public String getSupplierType() {
		return supplierType;
	}

	public void setSupplierType(String supplierType) {
		this.supplierType = supplierType;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getWebsiteURL() {
		return websiteURL;
	}

	public void setWebsiteURL(String websiteURL) {
		this.websiteURL = websiteURL;
	}

	public String getWebsiteOnALiURL() {
		return websiteOnALiURL;
	}

	public void setWebsiteOnALiURL(String websiteOnALiURL) {
		this.websiteOnALiURL = websiteOnALiURL;
	}

	public String getBusinessType() {
		return businessType;
	}
	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getMainProduct() {
		return mainProduct;
	}
	public void setMainProduct(String mainProduct) {
		this.mainProduct = mainProduct;
	}
	public String getOwnerShip() {
		return ownerShip;
	}
	public void setOwnerShip(String ownerShip) {
		this.ownerShip = ownerShip;
	}
	public String getTotalEmployee() {
		return totalEmployee;
	}
	public void setTotalEmployee(String totalEmployee) {
		this.totalEmployee = totalEmployee;
	}
	public String getAnnualRevenue() {
		return annualRevenue;
	}
	public void setAnnualRevenue(String annualRevenue) {
		this.annualRevenue = annualRevenue;
	}
	public String getYearEstablished() {
		return yearEstablished;
	}
	public void setYearEstablished(String yearEstablished) {
		this.yearEstablished = yearEstablished;
	}
	public String getExportPercentage() {
		return exportPercentage;
	}
	public void setExportPercentage(String exportPercentage) {
		this.exportPercentage = exportPercentage;
	}
	public String getExportMode() {
		return exportMode;
	}
	public void setExportMode(String exportMode) {
		this.exportMode = exportMode;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getEmployeeTradeDept() {
		return employeeTradeDept;
	}
	public void setEmployeeTradeDept(String employeeTradeDept) {
		this.employeeTradeDept = employeeTradeDept;
	}
	@Override
	public String toString() {
		return "Supplier [businessType=" + businessType + ", location=" + location + ", mainProduct=" + mainProduct
				+ ", ownerShip=" + ownerShip + ", totalEmployee=" + totalEmployee + ", annualRevenue=" + annualRevenue
				+ ", yearEstablished=" + yearEstablished + ", exportPercentage=" + exportPercentage + ", exportMode="
				+ exportMode + ", language=" + language + ", employeeTradeDept=" + employeeTradeDept + "]";
	}
	

}
