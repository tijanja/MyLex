package teetech.com.mylex;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

/**
 * Created by aKI on 21/02/2017.
 */

public class IconToggle
{
    boolean check = false;
    private ImageView imageView;
    private Context context;
    public IconToggle(Activity context, ImageView v)
    {
        imageView = v;
        this.context = context;
    }

    public void enable(Drawable enableIcon)
    {
        imageView.setImageDrawable(enableIcon);
        check = false;
    }

    public void disable(Drawable enableIcon)
    {
        imageView.setImageDrawable(enableIcon);
        check = true;
    }

    public boolean isEnabled()
    {
        return check;
    }
}
