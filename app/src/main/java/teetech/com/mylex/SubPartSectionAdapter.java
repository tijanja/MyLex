package teetech.com.mylex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aKI on 13/02/2017.
 */

public class SubPartSectionAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<Object> partList;

    public SubPartSectionAdapter(List<Object> item)
    {
       partList = item;
    }

    public static class PartListHolder extends RecyclerView.ViewHolder
    {
        private TextView subpartName;
        public PartListHolder(View itemView)
        {
            super(itemView);
            subpartName = (TextView) itemView.findViewById(R.id.subpart_title);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View partview = inflater.inflate(R.layout.subpart_item,parent,false);
        return new PartListHolder(partview);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        Subpart subpart = (Subpart) partList.get(position);
        PartListHolder  partholder = (PartListHolder)holder;
        partholder.subpartName.setText(subpart.getSubpartTitle());

    }

    @Override
    public int getItemCount()
    {
        return partList.size();
    }
}
