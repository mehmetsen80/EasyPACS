package org.easy.entity;

import java.io.Serializable;
import java.lang.Float;
import java.lang.Integer;
import java.lang.String;
import java.util.Date;
import javax.persistence.*;

import org.easy.entity.Series;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

/**
 * Entity implementation class for Entity: instance
 *
 */
@Entity
@Table(name="tbl_instance")
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@pkTBLSeriesID")
public class Instance implements Serializable {

	private static final long serialVersionUID = -4366785097270784782L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="pkTBLInstanceID")
	private Long pkTBLInstanceID;
	
	@Column(name="sopInstanceUID", length=100)
	private String sopInstanceUID;
	
	@Column(name="sopClassUID", length=100)
	private String sopClassUID;
	
	@Column(name="instanceNumber")
	private Integer instanceNumber;
	
	@Column(name="patientOrientation", length=40)
	private String patientOrientation;
	
	@Column(name="mediaStorageSopInstanceUID", length=100)
	private String mediaStorageSopInstanceUID;
	
	@Column(name="transferSyntaxUID", length=100)
	private String transferSyntaxUID;	

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="acquisitionDateTime")
	private Date acquisitionDateTime;
	
	@Column(name="imageType", length=40)
	private String imageType;
	
	@Column(name="pixelSpacing")
	private Float pixelSpacing;
	
	@Column(name="imageOrientation", length=40)
	private String imageOrientation;
	
	@Column(name="imagePosition", length=80)
	private String imagePosition;
	
	@Column(name="sliceThickness")
	private Float sliceThickness;
	
	@Column(name="sliceLocation")
	private Float sliceLocation;
	
	@Column(name="windowCenter", length=40)
	private String windowCenter;
	
	@Column(name="windowWidth", length=40)
	private String windowWidth;
	
	@Column(name="xrayTubeCurrent")
	private Integer xrayTubeCurrent;
	
	@Column(name="exposureTime")
	private Integer exposureTime;
	
	@Column(name="kvp", length=40)
	private String kvp;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="contentDateTime")
	private Date contentDateTime;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="createdDate", updatable = false, insertable=true)
	private Date createdDate;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="modifiedDate", insertable = true, updatable=true)
	private Date modifiedDate;

	@ManyToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name="pkTBLSeriesID")
	private Series series;
	

	public Instance() {
		super();
	}
	
	public Long getPkTBLInstanceID() {
		return this.pkTBLInstanceID;
	}
	public void setPkTBLInstanceID(Long pkTBLInstanceID) {
		this.pkTBLInstanceID = pkTBLInstanceID;
	}   
	   
	public String getSopInstanceUID() {
		return this.sopInstanceUID;
	}
	public void setSopInstanceUID(String sopInstanceUID) {
		this.sopInstanceUID = sopInstanceUID;
	}
	
	public String getSopClassUID() {
		return this.sopClassUID;
	}
	public void setSopClassUID(String sopClassUID) {
		this.sopClassUID = sopClassUID;
	}
	
	public Integer getInstanceNumber() {
		return this.instanceNumber;
	}
	public void setInstanceNumber(Integer instanceNumber) {
		this.instanceNumber = instanceNumber;
	}
	
	public String getPatientOrientation() {
		return this.patientOrientation;
	}
	public void setPatientOrientation(String patientOrientation) {
		this.patientOrientation = patientOrientation;
	}
	
	public String getMediaStorageSopInstanceUID() {
		return this.mediaStorageSopInstanceUID;
	}
	public void setMediaStorageSopInstanceUID(String mediaStorageSopInstanceUID) {
		this.mediaStorageSopInstanceUID = mediaStorageSopInstanceUID;
	}
	
	public String getTransferSyntaxUID() {
		return this.transferSyntaxUID;
	}
	public void setTransferSyntaxUID(String transferSyntaxUID) {
		this.transferSyntaxUID = transferSyntaxUID;
	}	
	
	public Date getAcquisitionDateTime() {
		return this.acquisitionDateTime;
	}
	public void setAcquisitionDateTime(Date acquisitionDateTime) {
		this.acquisitionDateTime = acquisitionDateTime;
	}
	
	public String getImageType() {
		return this.imageType;
	}
	public void setImageType(String imageType) {
		this.imageType = imageType;
	}
	
	public Float getPixelSpacing() {
		return this.pixelSpacing;
	}
	public void setPixelSpacing(Float pixelSpacing) {
		this.pixelSpacing = pixelSpacing;
	}
	
	public String getImageOrientation() {
		return this.imageOrientation;
	}
	public void setImageOrientation(String imageOrientation) {
		this.imageOrientation = imageOrientation;
	}
	
	public String getImagePosition() {
		return this.imagePosition;
	}
	public void setImagePosition(String imagePosition) {
		this.imagePosition = imagePosition;
	}
	
	public Float getSliceThickness() {
		return this.sliceThickness;
	}
	public void setSliceThickness(Float sliceThickness) {
		this.sliceThickness = sliceThickness;
	}
	
	public Float getSliceLocation() {
		return this.sliceLocation;
	}
	public void setSliceLocation(Float sliceLocation) {
		this.sliceLocation = sliceLocation;
	}
	
	public String getWindowsCenter() {
		return this.windowCenter;
	}
	public void setWindowCenter(String windowCenter) {
		this.windowCenter = windowCenter;
	}
	
	public String getWindowWidth() {
		return this.windowWidth;
	}
	public void setWindowWidth(String windowWidth) {
		this.windowWidth = windowWidth;
	}
	
	public Integer getXrayTubeCurrent() {
		return this.xrayTubeCurrent;
	}
	public void setXrayTubeCurrent(Integer xrayTubeCurrent) {
		this.xrayTubeCurrent = xrayTubeCurrent;
	}
	
	public Integer getExposureTime() {
		return this.exposureTime;
	}
	public void setExposureTime(Integer exposureTime) {
		this.exposureTime = exposureTime;
	}
	
	public String getKvp() {
		return this.kvp;
	}
	public void setKvp(String kvp) {
		this.kvp = kvp;
	}
	
	public Date getContentDateTime() {
		return this.contentDateTime;
	}
	public void setContentDateTime(Date contentDateTime) {
		this.contentDateTime = contentDateTime;
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
	
	public Series getSeries() {
	    return series;
	}
	public void setSeries(Series param) {
	    this.series = param;
	}
	
	
	@Override
	public String toString(){
		return String.format(
				"Instance[pkTBLInstanceID=%d, sopInstanceUID=%s, sopClassUID=%s, instanceNumber=%d, patientOrientation=%s, mediaStorageSopInstanceUID=%s, acquisitionDateTime=%s, imageType=%s, pixelSpacing=%f, imageOrientation=%s, imagePosition=%s, sliceThickness=%f, sliceLocation=%f, windowCenter=%s, windowWidth=%s, xrayTubeCurrent=%d, exposureTime=%d, kvp=%s, contentDateTime=%s, createdDate=%s, modifiedDate=%s]", 
				pkTBLInstanceID, sopInstanceUID, sopClassUID, instanceNumber, patientOrientation, mediaStorageSopInstanceUID, acquisitionDateTime, imageType, pixelSpacing, imageOrientation, imagePosition, sliceThickness, sliceLocation, windowCenter, windowWidth, xrayTubeCurrent, exposureTime, kvp, contentDateTime, createdDate, modifiedDate);
	}
   
}
