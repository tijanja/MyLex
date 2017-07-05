package teetech.com.mylex;


/**
 * Created by aKI on 08/02/2017.
 */

public class Part
{
    String partName,partId,statuteId,nextSet;
    public Part(String id)
    {
        statuteId = id;
    }

    public String getStatuteId()
    {
        return statuteId;
    }

    public String getPartName()
    {
        return partName;
    }

    public String getPartId()
    {
        return partId;
    }

    public void setPartName(String partName) {
        this.partName = partName;
    }

    public void setPartId(String partId) {
        this.partId = partId;
    }

    public String getNextSet()
    {
        return nextSet;
    }

    public void setNextSet(String s)
    {
        nextSet = s;
    }
}
