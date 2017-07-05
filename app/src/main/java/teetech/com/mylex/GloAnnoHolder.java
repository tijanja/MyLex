package teetech.com.mylex;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by aKI on 10/02/2017.
 */

public class GloAnnoHolder  extends Fragment
{
    private String gloAnno;
    private  int page;

    public static GloAnnoHolder newInstance(int p, String gloAn) {
        GloAnnoHolder fragmentFirst = new GloAnnoHolder();
        Bundle args = new Bundle();
        args.putInt("someInt", p);
        args.putString("gloAnno", gloAn);
        fragmentFirst.setArguments(args);
        return fragmentFirst;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        gloAnno = getArguments().getString("gloAnno");
        page = getArguments().getInt("someInt", 0);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.glo_anno_display, container, false);
        TextView glo_anno = (TextView) view.findViewById(R.id.glo_anno_text);
        glo_anno.setText(Html.fromHtml(gloAnno));

        return view;
    }
}
