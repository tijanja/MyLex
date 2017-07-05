package teetech.com.mylex;

import java.io.Serializable;

/**
 * Created by aKI on 13/02/2017.
 */

public class Subpart implements Serializable
{
    private static final long serialVersionUID = 465343345;
    private String subpartTitle,subpartId,partId,statuteId;


    public String getSubpartTitle() {
        return subpartTitle;
    }

    public String getStatuteId() {
        return statuteId;
    }

    public void setStatuteId(String statuteId) {
        this.statuteId = statuteId;
    }

    public void setSubpartTitle(String subpartTitle) {
        this.subpartTitle = subpartTitle;
    }

    public String getSubpartId() {
        return subpartId;
    }

    public void setSubpartId(String subpartId) {
        this.subpartId = subpartId;
    }

    public String getPartId() {
        return partId;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }
}
