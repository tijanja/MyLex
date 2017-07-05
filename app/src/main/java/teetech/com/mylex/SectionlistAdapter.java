package teetech.com.mylex;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by aKI on 17/02/2017.
 */

public class SectionlistAdapter extends RecyclerView.Adapter{
        List<Object> itemList;

        public SectionlistAdapter(List<Object> items)
        {
            itemList = items;
        }

        public static class SectionHolder extends RecyclerView.ViewHolder
        {
            TextView section_title;
            String id;

            public SectionHolder(View itemView)
            {
                super(itemView);
                section_title = (TextView) itemView.findViewById(R.id.part_title);

            }

        }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
        {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view2 = inflater.inflate(R.layout.part_view,parent,false);

            return new SectionlistAdapter.SectionHolder(view2);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
        {
            SectionlistAdapter.SectionHolder  sectiomHolder = (SectionlistAdapter.SectionHolder) holder;
            Section section = (Section) itemList.get(position);
            sectiomHolder.section_title.setText(section.getSectionTitle());
        }

        @Override
        public int getItemCount()
        {
            return itemList.size();
        }
}
