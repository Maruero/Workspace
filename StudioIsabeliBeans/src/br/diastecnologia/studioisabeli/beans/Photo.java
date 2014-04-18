package br.diastecnologia.studioisabeli.beans;

public class Photo {

	private Integer customerID;
	private Integer photoID;
	private PhotoType type;
	private byte[] file;
	public Integer getCustomerID() {
		return customerID;
	}
	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
	}
	public Integer getPhotoID() {
		return photoID;
	}
	public void setPhotoID(Integer photoID) {
		this.photoID = photoID;
	}
	public PhotoType getType() {
		return type;
	}
	public void setType(PhotoType type) {
		this.type = type;
	}
	public byte[] getFile() {
		return file;
	}
	public void setFile(byte[] file) {
		this.file = file;
	}
	
}
