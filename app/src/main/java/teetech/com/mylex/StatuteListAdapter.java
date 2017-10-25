package teetech.com.mylex;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by aKI on 26/01/2017.
 */

public class StatuteListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{

    private List<Object> statuteList,objList,advertList,objectList;
    Context context;
    Child statuteRowAdapter;

    MainActivity mainActivity;

    public static class StatuteListHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public RecyclerView statuteRecyclerView;
        public StatuteListHolder(View v)
        {
            super(v);
            statuteRecyclerView =(RecyclerView) v.findViewById(R.id.statute_recycler_horizontal);

        }

    }

    public static class ChildStatuteListHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public RecyclerView statuteRecyclerView;

        public ChildStatuteListHolder(View v)
        {
            super(v);
            statuteRecyclerView =(RecyclerView) v.findViewById(R.id.statute_recycler_horizontal);

        }

    }

    public static class BusinessStatuteListHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public RecyclerView statuteRecyclerView;
        public BusinessStatuteListHolder(View v)
        {
            super(v);
            statuteRecyclerView =(RecyclerView) v.findViewById(R.id.statute_recycler_horizontal);

        }

    }

    public static class CriminalLawStatuteListHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public RecyclerView statuteRecyclerView;
        public CriminalLawStatuteListHolder(View v)
        {
            super(v);
            statuteRecyclerView =(RecyclerView) v.findViewById(R.id.statute_recycler_horizontal);

        }

    }

    public static class ProcurementStatuteListHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public RecyclerView statuteRecyclerView;
        public ProcurementStatuteListHolder(View v)
        {
            super(v);
            statuteRecyclerView =(RecyclerView) v.findViewById(R.id.statute_recycler_horizontal);

        }

    }

    public static class StateStatuteListHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public RecyclerView statuteRecyclerView;
        public StateStatuteListHolder(View v)
        {
            super(v);
            statuteRecyclerView =(RecyclerView) v.findViewById(R.id.statute_recycler_horizontal);

        }

    }

    public static class ConstitutionStatuteListHolder extends RecyclerView.ViewHolder
    {
        // each data item is just a string in this case
        public RecyclerView statuteRecyclerView;
        public ConstitutionStatuteListHolder(View v)
        {
            super(v);
            statuteRecyclerView =(RecyclerView) v.findViewById(R.id.statute_recycler_horizontal);

        }

    }



    public static class ImageListBoxHolder extends RecyclerView.ViewHolder
    {
        RecyclerView recyclerView;
        public ImageListBoxHolder(View itemView) {
            super(itemView);

            recyclerView = (RecyclerView) itemView.findViewById(R.id.recycler_horizontal);
        }
    }


    public StatuteListAdapter(List<Object> s,Context c,List<Object> obj,MainActivity activity)
    {
        statuteList = s;
        advertList = obj;
        context = c;
        objectList = new ArrayList<>();
        mainActivity = activity;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        RecyclerView.ViewHolder viewHolder = null;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        switch (viewType)
        {
            case 0:
//                View imageView = inflater.inflate(R.layout.image_layout,parent,false);
//                viewHolder = new ImageListBoxHolder(imageView);
                break;

            case 1:
                View childStatusView = inflater.inflate(R.layout.statute_row,parent,false);
                viewHolder = new ChildStatuteListHolder(childStatusView);
                break;

            case 2:
                View BusinessView = inflater.inflate(R.layout.statute_row,parent,false);
                viewHolder = new BusinessStatuteListHolder(BusinessView);
                break;

            case 3:
                View CriminalLawView = inflater.inflate(R.layout.statute_row,parent,false);
                viewHolder = new CriminalLawStatuteListHolder(CriminalLawView);
                break;

            case 4:
                View ProcurementView = inflater.inflate(R.layout.statute_row,parent,false);
                viewHolder = new ProcurementStatuteListHolder(ProcurementView);
                break;

            case 5:
                View stateView = inflater.inflate(R.layout.statute_row,parent,false);
                viewHolder = new StateStatuteListHolder(stateView);
                break;

            case 6:
                View constitutionView = inflater.inflate(R.layout.statute_row,parent,false);
                viewHolder = new ConstitutionStatuteListHolder(constitutionView);
                break;

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position)
    {
        int listPosition = viewHolder.getItemViewType();
        switch (listPosition)
        {
            case 0:

                ImageListBoxHolder imageListBoxHolder = (ImageListBoxHolder) viewHolder;
                ImageRecyclerView imageAdapter = new ImageRecyclerView(advertList, new ImageRecyclerView.OnItemClickedListener() {
                    @Override
                    public void onItemClicked(MyImage myImage)
                    {
                        Intent intent = new Intent(context,SectionListActivity.class);
                        mainActivity.startActivity(intent);
                    }
                });
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(imageListBoxHolder.recyclerView.getContext(),LinearLayoutManager.HORIZONTAL, false);
                imageListBoxHolder.recyclerView.setLayoutManager(layoutManager);
                imageListBoxHolder.recyclerView.setAdapter(imageAdapter);

                break;

            case 1:
                Category cat = (Category)statuteList.get(listPosition);

                final List<Object> itemList  = cat.getStatutes();
                statuteRowAdapter = new Child(context,itemList);

                ChildStatuteListHolder childHolder = (ChildStatuteListHolder) viewHolder;
                RecyclerView.LayoutManager statutelayoutManager = new LinearLayoutManager(childHolder.statuteRecyclerView.getContext(),LinearLayoutManager.HORIZONTAL, false);
                childHolder.statuteRecyclerView.setLayoutManager(statutelayoutManager);
                childHolder.statuteRecyclerView.setAdapter(statuteRowAdapter);
                childHolder.statuteRecyclerView.addOnItemTouchListener(new OnStatuteClickListener(context, childHolder.statuteRecyclerView, new OnClickStatute() {

                    @Override
                    public void onClick(View view, int position)
                    {try
                        {
                            Statute statute = (Statute)itemList.get(position);
                            if(statute.getNextSet().equals("part"))
                            {
                                Intent intent = new Intent(context,StatuteDisplayActivity.class);
                                intent.putExtra("title",statute.getTitle());
                                intent.putExtra("statuteId",statute.getStatuteId());
                                intent.putExtra("glo-anno",statute.getGloAnno());


                                mainActivity.startActivity(intent);
                            }
                            else
                            {
                                Intent intent = new Intent(context,SectionListActivity.class);
                                intent.putExtra("title",statute.getTitle());
                                intent.putExtra("statuteId",statute.getStatuteId());
                                intent.putExtra("glo-anno",statute.getGloAnno());
                                intent.putExtra("sectionFrom","statute");

                                Toast.makeText(context,statute.getNextSet(),Toast.LENGTH_LONG).show();
                                mainActivity.startActivity(intent);
                            }


                            //Snackbar.make(view,statute.getTitle(),800).show();
                            //Toast.makeText(context,statute.getStatuteId(),Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            Log.e("Error-1",e.getMessage());
                        }

                    }

                    @Override
                    public void onLongClick(View view, int position)
                    {

                    }
                }));

                statuteRowAdapter.notifyDataSetChanged();
                break;

            case 2:
                BusinessStatuteListHolder businessHolder = (BusinessStatuteListHolder) viewHolder;
                Business businessRowAdapter = new Business(context);
                RecyclerView.LayoutManager statutelayoutManager2 = new LinearLayoutManager(businessHolder.statuteRecyclerView.getContext(),LinearLayoutManager.HORIZONTAL, false);
                businessHolder.statuteRecyclerView.setLayoutManager(statutelayoutManager2);
                businessHolder.statuteRecyclerView.setAdapter(businessRowAdapter);
                businessHolder.statuteRecyclerView.addOnItemTouchListener(new OnStatuteClickListener(context,businessHolder.statuteRecyclerView,new OnClickStatute()
                {
                    @Override
                    public void onClick(View view, int position)
                    {
                        /*try
                        {
                            Statute statute = (Statute)itemList.get(position);
                            if(statute.getNextSet().equals("part"))
                            {
                                Intent intent = new Intent(context,ScrollingActivity.class);
                                intent.putExtra("title",statute.getTitle());
                                intent.putExtra("statuteId",statute.getStatuteId());
                                intent.putExtra("glo-anno",statute.getGloAnno());


                                mainActivity.startActivity(intent);
                            }
                            else
                            {
                                Intent intent = new Intent(context,SectionListActivity.class);
                                intent.putExtra("title",statute.getTitle());
                                intent.putExtra("statuteId",statute.getStatuteId());
                                intent.putExtra("glo-anno",statute.getGloAnno());
                                intent.putExtra("sectionFrom","statute");

                                Toast.makeText(context,statute.getNextSet(),Toast.LENGTH_LONG).show();
                                mainActivity.startActivity(intent);
                            }


                            //Snackbar.make(view,statute.getTitle(),800).show();
                            //Toast.makeText(context,statute.getStatuteId(),Toast.LENGTH_LONG).show();
                        }
                        catch (Exception e)
                        {
                            Log.e("Error-1",e.getMessage());
                        }*/

                    }

                    @Override
                    public void onLongClick(View view, int position)
                    {

                    }
                }));
                break;

            case 4:

                ProcurementStatuteListHolder procurementStatuteListHolderHolder = (ProcurementStatuteListHolder) viewHolder;
                Procurement procurementRowAdapter = new Procurement(context);
                RecyclerView.LayoutManager statutelayoutManager3 = new LinearLayoutManager(procurementStatuteListHolderHolder.statuteRecyclerView.getContext(),LinearLayoutManager.VERTICAL, false);
                procurementStatuteListHolderHolder.statuteRecyclerView.setLayoutManager(statutelayoutManager3);
                procurementStatuteListHolderHolder.statuteRecyclerView.setAdapter(procurementRowAdapter);

                break;

            case 5:

                StateStatuteListHolder stateStatuteListHolder = (StateStatuteListHolder) viewHolder;
                State stateRowAdapter = new State(context);
                RecyclerView.LayoutManager statutelayoutManager4 = new LinearLayoutManager(stateStatuteListHolder.statuteRecyclerView.getContext(),LinearLayoutManager.VERTICAL, false);
                stateStatuteListHolder.statuteRecyclerView.setLayoutManager(statutelayoutManager4);
                stateStatuteListHolder.statuteRecyclerView.setAdapter(stateRowAdapter);

                break;

            case 3:

                CriminalLawStatuteListHolder criminalLawStatuteListHolder = (CriminalLawStatuteListHolder) viewHolder;
                CriminalLaw criminalLawRowAdapter = new CriminalLaw(context);
                RecyclerView.LayoutManager statutelayoutManager5 = new LinearLayoutManager(criminalLawStatuteListHolder.statuteRecyclerView.getContext(),LinearLayoutManager.VERTICAL, false);
                criminalLawStatuteListHolder.statuteRecyclerView.setLayoutManager(statutelayoutManager5);
                criminalLawStatuteListHolder.statuteRecyclerView.setAdapter(criminalLawRowAdapter);

                break;

            case 6:

                ConstitutionStatuteListHolder constitutionStatuteListHolder = (ConstitutionStatuteListHolder) viewHolder;
                Constitution constitutionRowAdapter = new Constitution(context);
                RecyclerView.LayoutManager statutelayoutManager6 = new LinearLayoutManager(constitutionStatuteListHolder.statuteRecyclerView.getContext(),LinearLayoutManager.VERTICAL, false);
                constitutionStatuteListHolder.statuteRecyclerView.setLayoutManager(statutelayoutManager6);
                constitutionStatuteListHolder.statuteRecyclerView.setAdapter(constitutionRowAdapter);

                break;
        }


    }

    @Override
    public int getItemCount()
    {
        return statuteList.size();
    }

    @Override
    public int getItemViewType(int position)
    {
        Category category = (Category)statuteList.get(position);

//        if(category.getCategoryName().equals("image"))
//        {
//            return 0;
//        }
        if(category.getCategoryName().equals("Child"))
        {
            return 1;
        }
        else if(category.getCategoryName().equals("Business"))
        {
            return 2;
        }
        else if(category.getCategoryName().equals("Criminal Law"))
        {
            return 3;
        }
        else if(category.getCategoryName().equals("Procurement"))
        {
            return 4;
        }
        else if(category.getCategoryName().equals("State"))
        {
            return 5;
        }
        else if(category.getCategoryName().equals("Constitution"))
        {
            return 6;
        }
        return -1;
    }

    public void invalidate__()
    {
        statuteRowAdapter.notifyDataSetChanged();
    }
}
