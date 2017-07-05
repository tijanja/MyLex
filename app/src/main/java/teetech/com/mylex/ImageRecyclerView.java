package teetech.com.mylex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * Created by aKI on 27/01/2017.
 */

public class ImageRecyclerView extends RecyclerView.Adapter
{
    List<Object> itemList;
    private OnItemClickedListener listener;

    public interface OnItemClickedListener
    {
        public void onItemClicked(MyImage myImage);
    }
    public ImageRecyclerView(List<Object> list, OnItemClickedListener l)
    {
        itemList = list;
        listener = l;
    }

    public static class EmptyBoxHolder extends RecyclerView.ViewHolder
    {

        ImageView imageView;
        public EmptyBoxHolder(View itemView)
        {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.empty_box);

        }
    }

    public static class ImageHolder extends RecyclerView.ViewHolder
    {
        ImageView imageView;
        View itemView;

        public ImageHolder(View itemView)
        {
            super(itemView);
            this.itemView = itemView;
        }

        public void bind(Object obj,final OnItemClickedListener clicked)
        {
            final MyImage myImage = (MyImage) obj;
            imageView.setImageDrawable(myImage.getImage());
            itemView.setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    clicked.onItemClicked(myImage);
                }
            });
        }

    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (viewType)
        {
            case 0:
                View view = inflater.inflate(R.layout.empty_layout,parent,false);
                viewHolder = new EmptyBoxHolder(view);

                break;

            case 1:
                View view2 = inflater.inflate(R.layout.image_horizontal_layout,parent,false);
                viewHolder = new ImageHolder(view2);
                break;
        }



        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        switch (holder.getItemViewType())
        {
            case 0:
                EmptyBoxHolder emptyBoxHolder = (EmptyBoxHolder) holder;

                break;

            case 1:
                ImageHolder imgHolder = (ImageHolder) holder;
               // imgHolder.bind(itemList.get(position),listener);
                break;
        }

    }

    @Override
    public int getItemCount()
    {
        return itemList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        if(itemList.get(position) instanceof String)
        {
            return 0;
        }
        else if(itemList.get(position) instanceof MyImage)
        {
            return 1;
        }
        return -1;
    }
}
