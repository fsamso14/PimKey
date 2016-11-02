package com.example.pimkey.item;

public class Item {
	public Item(){}
	public Item(String title, String comments, String dates, String domain,
			String keywords, String note, int type, String project,
			String name, String company, String service, String mobilePhone,
			String eMail, String officeTel, String officeAddress,
			String homeTel, String homeAddress, String website, String spouse,
			String author, String toRead, String interest,
			String contentQuality, String websiteQuality, String language,
			String location, String description, String authorType,
			String authorName, String authorLocation, String publicType,String transcript) {
		this.title = title;
		this.comments = comments;
		this.dates = dates;
		this.domain = domain;
		this.keywords = keywords;
		this.note = note;
		this.type = type;
		this.project = project;
		this.transcript=transcript;
		if(type==typeContact){
			this.name = name;
			this.company = company;
			this.service = service;
			this.mobilePhone = mobilePhone;
			this.eMail = eMail;
			this.officeTel = officeTel;
			this.officeAddress = officeAddress;
			this.homeTel = homeTel;
			this.homeAddress = homeAddress;
			this.website = website;
			this.language=language;
			this.spouse = spouse;
		}
		if(type==typeDoc){
			this.author = author;
			this.toRead = toRead;
			this.interest = interest;
			this.contentQuality = contentQuality;
			this.websiteQuality = websiteQuality;
			this.language = language;
			this.location = location;
		}
		if(type==typeTextnote){
			this.author=author;
			this.interest=interest;
		}
		if(type==typeWebsite){
			this.description = description;
			this.authorType = authorType;
			this.authorName = authorName;
			this.authorLocation = authorLocation;
			this.publicType = publicType;
			this.website = website;
			this.contentQuality = contentQuality;
			this.websiteQuality = websiteQuality;
			this.language = language;
		}
		
	}
	private String transcript;
	private String title;
	private String comments;
	private String dates;
	private String domain;
	private String keywords;
	private String note;
	private int id;
	private int type;
	private String project;
	
	private String name;
	private String company;
	private String service;
	private String mobilePhone;
	private String eMail;
	private String officeTel;
	private String officeAddress;
	private String homeTel;
	private String homeAddress;
	private String website;
	private String spouse;
	
	private String author;
	private String toRead;
	private String interest;
	private String contentQuality;
	private String websiteQuality;
	private String language;
	private String location;
	
	private String description;
	private String authorType;
	private String authorName;
	private String authorLocation;
	private String publicType;
	
	public static final int typeDoc=1;
	public static final int typeContact=2;
	public static final int typeWebsite=3;
	public static final int typeTextnote=4;
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public String getDates() {
		return dates;
	}
	public void setDates(String dates) {
		this.dates = dates;
	}
	public String getDomain() {
		return domain;
	}
	public void setDomain(String domain) {
		this.domain = domain;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getproject() {
		return project;
	}
	public void setproject(String project) {
		this.project = project;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getMobilePhone() {
		return mobilePhone;
	}
	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}
	public String geteMail() {
		return eMail;
	}
	public void seteMail(String eMail) {
		this.eMail = eMail;
	}
	public String getOfficeTel() {
		return officeTel;
	}
	public void setOfficeTel(String officeTel) {
		this.officeTel = officeTel;
	}
	public String getOfficeAddress() {
		return officeAddress;
	}
	public void setOfficeAddress(String officeAddress) {
		this.officeAddress = officeAddress;
	}
	public String getHomeTel() {
		return homeTel;
	}
	public void setHomeTel(String homeTel) {
		this.homeTel = homeTel;
	}
	public String getHomeAddress() {
		return homeAddress;
	}
	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getSpouse() {
		return spouse;
	}
	public void setSpouse(String spouse) {
		this.spouse = spouse;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getToRead() {
		return toRead;
	}
	public void setToRead(String toRead) {
		this.toRead = toRead;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getContentQuality() {
		return contentQuality;
	}
	public void setContentQuality(String contentQuality) {
		this.contentQuality = contentQuality;
	}
	public String getWebsiteQuality() {
		return websiteQuality;
	}
	public void setWebsiteQuality(String websiteQuality) {
		this.websiteQuality = websiteQuality;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAuthorType() {
		return authorType;
	}
	public void setAuthorType(String authorType) {
		this.authorType = authorType;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public String getAuthorLocation() {
		return authorLocation;
	}
	public void setAuthorLocation(String authorLocation) {
		this.authorLocation = authorLocation;
	}
	public String getPublicType() {
		return publicType;
	}
	public void setPublicType(String publicType) {
		this.publicType = publicType;
	}
	public void setTranscript(String transcript){
		this.transcript=transcript;
	}
	public String getTranscript(){
		return this.transcript;
	}
}
