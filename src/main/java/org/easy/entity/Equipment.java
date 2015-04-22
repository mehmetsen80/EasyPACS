package org.easy.entity;

import java.io.Serializable;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

import org.easy.entity.Series;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: Equipment
 *
 */
@Entity
@Table(name="tbl_equipment")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@pkTBLEquipmentID")
public class Equipment implements Serializable {

	private static final long serialVersionUID = 6245262777427705812L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name="pkTBLEquipmentID")
	private Long pkTBLEquipmentID;
	
	@Column(name="modality", length=50)
	private String modality;
	
	@Column(name="conversionType", length=50)
	private String conversionType;
	
	@Column(name="stationName", length=60)
	private String stationName;
	
	@Column(name="institutionName", length=100)
	private String institutionName;
	
	@Column(name="institutionAddress", length=150)
	private String institutionAddress;
	
	@Column(name="institutionalDepartmentName", length=50)
	private String institutionalDepartmentName;
	
	@Column(name="manufacturer", length=100)
	private String manufacturer;
	
	@Column(name="manufacturerModelName", length=100)
	private String manufacturerModelName;
	
	@Column(name="softwareVersion", length=100)
	private String softwareVersion;
	
	@Column(name="deviceSerialNumber", length=100)
	private String deviceSerialNumber;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="createdDate", updatable = false, insertable=true)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="modifiedDate", insertable = true, updatable=true)
	private Date modifiedDate;
	
	
	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="pkTBLSeriesID")
	private Series series;
	
	
	public Equipment() {
		super();
	}
	
	public Long getPkTBLEquipmentID() {
		return this.pkTBLEquipmentID;
	}
	public void setPkTBLEquipmentID(Long pkTBLEquipmentID) {
		this.pkTBLEquipmentID = pkTBLEquipmentID;
	}
	
	public String getModality() {
		return this.modality;
	}
	public void setModality(String modality) {
		this.modality = modality;
	}
	
	public String getConversionType(){
		return this.conversionType;
	}
	public void setConverstionType(String conversionType){
		this.conversionType = conversionType;
	}
	
	public String getStationName() {
		return this.stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	
	public String getInstitutionName() {
		return this.institutionName;
	}
	public void setInstitutionName(String institutionName) {
		this.institutionName = institutionName;
	}
	
	public String getInstitutionAddress() {
		return this.institutionAddress;
	}
	public void setInstitutionAddress(String institutionAddress) {
		this.institutionAddress = institutionAddress;
	}
	
	public String getInstitutionalDepartmentName() {
		return this.institutionalDepartmentName;
	}
	public void setInstitutionalDepartmentName(String institutionalDepartmentName) {
		this.institutionalDepartmentName = institutionalDepartmentName;
	}
	
	public String getManufacturer() {
		return this.manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	public String getManufacturerModelName() {
		return this.manufacturerModelName;
	}
	public void setManufacturerModelName(String manufacturerModelName) {
		this.manufacturerModelName = manufacturerModelName;
	}
	
	public String getSoftwareVersion() {
		return this.softwareVersion;
	}
	public void setSoftwareVersion(String softwareVersion) {
		this.softwareVersion = softwareVersion;
	}
	
	public String getDeviceSerialNumber() {
		return this.deviceSerialNumber;
	}
	public void setDeviceSerialNumber(String deviceSerialNumber) {
		this.deviceSerialNumber = deviceSerialNumber;
	}
	
	public Date getCreatedDate() {
		return this.createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	
	public Date getModifiedDate() {
		return this.modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	
	@PreUpdate
    @PrePersist
    public void updateTimeStamps() {    	
    	modifiedDate = new Date();
        if (createdDate==null) {
        	createdDate = new Date();
        }
    }
	
	@Override
	public String toString(){
		return String.format(
				"Equipment[pkTBLEquipmentID=%d, modality=%s, conversionType=%s, stationName=%s, institutionName=%s, institutionAddress=%s, institutionalDepartmentName=%s, manufacturer=%s, manufacturerModelName=%s, softwareVersion=%s, deviceSerialNumber=%s, createdDate=%s, modifiedDate=%s]", 
				pkTBLEquipmentID, modality, conversionType, stationName, institutionName, institutionAddress, institutionalDepartmentName, manufacturer, manufacturerModelName, softwareVersion, deviceSerialNumber, createdDate, modifiedDate);
	}
	public Series getSeries() {
	    return series;
	}
	public void setSeries(Series param) {
	    this.series = param;
	}
	
   
}
