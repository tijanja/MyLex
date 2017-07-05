package teetech.com.mylex;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class Child extends RecyclerView.Adapter
{
        List<Object> itemList;
        Context context;
            Child child;

public Child(Context c, List<Object> item)
{
        context = c;
        itemList = item;
        child = this;
}

public static class StatuteHolder extends RecyclerView.ViewHolder
{
    ImageView imageView;
    TextView statute_title;
    LinearLayout status_parent_box;

    public StatuteHolder(View itemView)
    {
        super(itemView);

        statute_title = (TextView) itemView.findViewById(R.id.statute_title);
        status_parent_box = (LinearLayout) itemView.findViewById(R.id.status_parent_box);
    }

}


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view2 = inflater.inflate(R.layout.image_horizontal_layout,parent,false);

        return new StatuteRowAdapter.StatuteHolder(view2);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {

        StatuteRowAdapter.StatuteHolder imgHolder = (StatuteRowAdapter.StatuteHolder) holder;
        Statute myImage = (Statute) itemList.get(position);

        if(position == itemList.size()-1)
        {
            imgHolder.status_parent_box.setPadding(0,0,20,0);
        }

       // imgHolder.imageView.setImageDrawable(myImage.getImage());
        imgHolder.statute_title.setText(Html.fromHtml(myImage.getTitle()));
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }
}

