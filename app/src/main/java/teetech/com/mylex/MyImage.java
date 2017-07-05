package teetech.com.mylex;

import android.graphics.drawable.Drawable;

/**
 * Created by aKI on 27/01/2017.
 */

public class MyImage
{
    Drawable img;
    public MyImage(Drawable image)
    {
        img = image;
    }

    public Drawable getImage()
    {
        return img;
    }
}
