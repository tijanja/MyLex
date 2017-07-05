package teetech.com.mylex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aKI on 07/02/2017.
 */

public class PartListAdapter extends RecyclerView.Adapter
{
    List<Object> itemList;

    public PartListAdapter(List<Object> items)
    {
        itemList = items;
    }

    public static class PartHolder extends RecyclerView.ViewHolder
    {
        TextView part_title;
        String id;

        public PartHolder(View itemView)
        {
            super(itemView);
            part_title = (TextView) itemView.findViewById(R.id.part_title);

        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view2 = inflater.inflate(R.layout.part_view,parent,false);

        return new PartListAdapter.PartHolder(view2);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        PartListAdapter.PartHolder  partHolder = (PartListAdapter.PartHolder) holder;
        Part part = (Part) itemList.get(position);
        partHolder.part_title.setText(part.partName);
    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }
}
