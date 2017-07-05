package teetech.com.mylex;

import java.io.Serializable;

/**
 * Created by aKI on 13/02/2017.
 */

public class Section implements Serializable
{
    private String  bookmark, sectionId, sectionTitle, sectionText,sectionAnno,caseAnno,statuteId,statuteTitle,partId,partTitle,subpartId,subpartTitle;
    private static final long serialVersionUID = 465423445;
    private int sectionNum,referenceNum;

    public Section()
    {

    }

    public int getReferenceNum() {
        return referenceNum;
    }

    public void setReferenceNum(int referenceNum) {
        this.referenceNum = referenceNum;
    }

    public int getSectionNum() {
        return sectionNum;
    }

    public void setSectionNum(int sectionNum) {
        this.sectionNum = sectionNum;
    }

    public String getBookmark() {
        return bookmark;
    }

    public void setBookmark(String bookmark) {
        this.bookmark = bookmark;
    }

    public String getSectionAnno() {
        return sectionAnno;
    }

    public void setSectionAnno(String sectionAnno) {
        this.sectionAnno = sectionAnno;
    }

    public String getCaseAnno() {
        return caseAnno;
    }

    public void setCaseAnno(String caseAnno) {
        this.caseAnno = caseAnno;
    }

    public String getSectionText() {
        return sectionText;
    }

    public void setSectionText(String sectionText) {
        this.sectionText = sectionText;
    }

    public String getSectionId() {
        return sectionId;
    }

    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    public String getSectionTitle() {
        return sectionTitle;
    }

    public void setSectionTitle(String sectionTitle) {
        this.sectionTitle = sectionTitle;
    }

    public String getStatuteId() {
        return statuteId;
    }

    public void setStatuteId(String statuteId) {
        this.statuteId = statuteId;
    }

    public String getStatuteTitle() {
        return statuteTitle;
    }

    public void setStatuteTitle(String statuteTitle) {
        this.statuteTitle = statuteTitle;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getPartTitle() {
        return partTitle;
    }

    public void setPartTitle(String partTitle) {
        this.partTitle = partTitle;
    }

    public String getSubpartId() {
        return subpartId;
    }

    public void setSubpartId(String subpartId) {
        this.subpartId = subpartId;
    }

    public String getSubpartTitle() {
        return subpartTitle;
    }

    public void setSubpartTitle(String subpartTitle) {
        this.subpartTitle = subpartTitle;
    }
}
