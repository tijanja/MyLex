package teetech.com.mylex;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.StyleSpan;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by aKI on 19/02/2017.
 */

public class SectionTextFragment extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener
{

    private ImageView boolmark;
    private IconToggle iconToggle;
    private Section section;
    private ViewPager viewPager;
    private ImageView forwardCaret,backwardCaret;
    private TextView sectionTexts;

    public static SectionTextFragment newInstance(int sectionNumber,Section section)
    {
        SectionTextFragment fragment = new SectionTextFragment();
        Bundle args = new Bundle();
        args.putSerializable("sectionObj",section);

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {

        section = (Section)getArguments().getSerializable("sectionObj");

        String[] sectionTitleArray = section.getSectionTitle().split("\\.");


        viewPager=(ViewPager) getActivity().findViewById(R.id.section_text_pagers);
        viewPager.addOnPageChangeListener(this);
        forwardCaret = (ImageView) getActivity().findViewById(R.id.right_caret);
        backwardCaret = (ImageView) getActivity().findViewById(R.id.left_caret);

        section.setSectionNum(Integer.parseInt(sectionTitleArray[0]));
        section.setReferenceNum(viewPager.getCurrentItem());


        forwardCaret.setOnClickListener(this);
        backwardCaret.setOnClickListener(this);
        //Toast.makeText(SectionTextFragment.this.getActivity(),sectionTitleArray[0],Toast.LENGTH_SHORT).show();

        View view = inflater.inflate(R.layout.section_text_fragment,container,false);
        sectionTexts = (TextView)view.findViewById(R.id.section_text);
        TextView sec_anno_title = (TextView)view.findViewById(R.id.sec_anno_title);
        CardView cardView = (CardView)view.findViewById(R.id.sec_anno_card);

        TextView case_anno_title = (TextView)view.findViewById(R.id.case_anno_title);
        TextView caseAnno = (TextView)view.findViewById(R.id.case_anno);

        boolmark = (ImageView) view.findViewById(R.id.bookmark_icon);
        iconToggle = new IconToggle(this.getActivity(),boolmark);

        TextView sectionTitle = (TextView)view.findViewById(R.id.sectionTitle);
        TextView secAnno = (TextView)view.findViewById(R.id.sec_anno);
        sectionTitle.setText(section.getSectionTitle());

        if(section.getSectionAnno().length()>5)
        {

            secAnno.setText(Html.fromHtml(section.getSectionAnno()));
            sec_anno_title.setText("SECTION ANNOTATION");

            new PatternEditableBuilder().addPattern(Pattern.compile("section\\s+[0-9]{1,3}",Pattern.CASE_INSENSITIVE), Color.BLUE).addPattern(Pattern.compile("\\d{4}\\s+No\\.\\s*\\d{1,4}", Pattern.CASE_INSENSITIVE), Color.BLUE, new PatternEditableBuilder.SpannableClickedListener() {
                @Override
                public void onSpanClicked(String text)
                {
                    //Toast.makeText(SectionTextFragment.this.getActivity(),text,Toast.LENGTH_SHORT).show();
                }
            }).addPattern(Pattern.compile("sections\\s+[0-9]{1,3}",Pattern.CASE_INSENSITIVE), Color.BLUE).into(secAnno);

           // new PatternEditableBuilder();

        }
        else
        {
            cardView.setVisibility(View.GONE);
            secAnno.setVisibility(View.GONE);
            sec_anno_title.setVisibility(View.GONE);

        }

        if( section.getCaseAnno().length()>5)
        {
            String caseAnnoSt = section.getCaseAnno();
            Pattern pattern = Pattern.compile("/\\[[0-9]{4}\\] NELR CA\\([0-9]{1,4}\\)/i");
            Matcher m = pattern.matcher(caseAnnoSt);

            caseAnno.setText(Html.fromHtml(m.replaceFirst("TUNJI")));
            case_anno_title.setText("CASE ANNOTATION");

        }
        else
        {
            case_anno_title.setVisibility(View.GONE);
            caseAnno.setVisibility(View.GONE);
        }
        //String text = section.getSectionText();

        final ActionMode.Callback callback = new ActionMode.Callback() {
            @Override
            public boolean onCreateActionMode(ActionMode mode, Menu menu)
            {

                MenuInflater inflater = SectionTextFragment.this.getActivity().getMenuInflater();
                inflater.inflate(R.menu.menu_main, menu);
                menu.removeItem(android.R.id.selectAll);
                return true;
            }

            @Override
            public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
                return false;
            }

            @Override
            public boolean onActionItemClicked(ActionMode mode, MenuItem item)
            {
                CharacterStyle cs;
                int start = sectionTexts.getSelectionStart();
                int end = sectionTexts.getSelectionEnd();
                SpannableStringBuilder ssb = new SpannableStringBuilder(sectionTexts.getText());
                switch (item.getItemId())
                {
                    case R.id.action_settings:
                        cs = new StyleSpan(Typeface.BOLD_ITALIC);
                        ssb.setSpan(cs, start, end, 1);
                        sectionTexts.setText(ssb);
                        mode.finish();
                        return true;
                    default:
                        return true;
                }

            }

            @Override
            public void onDestroyActionMode(ActionMode mode)
            {

            }
        };
        //final SpannableString text = new SpannableString(Html.fromHtml(section.getSectionText()));
        //text.setSpan(new RelativeSizeSpan(1.5f), text.length() - "section".length(), text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //text.setSpan(new ForegroundColorSpan(Color.RED), 3, text.length() - 3, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //this.registerForContextMenu(sectionTexts);
        sectionTexts.setTextIsSelectable(true);
        sectionTexts.setCustomSelectionActionModeCallback(callback);

        sectionTexts.setText(Html.fromHtml(section.getSectionText()));
        new PatternEditableBuilder().addPattern(Pattern.compile("section\\s*\\d{1,3}",Pattern.CASE_INSENSITIVE), Color.BLUE, new PatternEditableBuilder.SpannableClickedListener() {
            @Override
            public void onSpanClicked(String text)
            {
                String[] strings = text.split("\\s");
                int toSectionNum = Integer.parseInt(strings[1]);



                if(section.getSectionNum()>toSectionNum)
                {
                    section.setReferenceNum(viewPager.getCurrentItem());
                    forwardCaret.setVisibility(View.VISIBLE);
                    backwardCaret.setVisibility(View.GONE);

                    Toast.makeText(SectionTextFragment.this.getActivity(),"Move to Section "+toSectionNum,Toast.LENGTH_SHORT).show();
                }
                else
                {
                    backwardCaret.setVisibility(View.VISIBLE);
                    forwardCaret.setVisibility(View.GONE);

                    Toast.makeText(SectionTextFragment.this.getActivity(),"Move to Section "+toSectionNum,Toast.LENGTH_SHORT).show();
                }

                forwardCaret.invalidate();
                backwardCaret.invalidate();
                viewPager.setCurrentItem(toSectionNum-1);

            }
        }).into(sectionTexts);
        boolmark.setOnClickListener(this);


        return view;
    }


    @Override
    public void onClick(View view)
    {

        switch (view.getId())
        {
            case R.id.bookmark_icon:

                if(iconToggle.isEnabled())
                {
                    iconToggle.enable(getResources().getDrawable(R.drawable.ic_bookmark_border_green_24dp));
                    Toast.makeText(SectionTextFragment.this.getActivity(),"Section UnBookmark",Toast.LENGTH_LONG).show();

                }
                else
                {
                    iconToggle.disable(getResources().getDrawable(R.drawable.ic_bookmark_black_24dp));
                    Toast.makeText(SectionTextFragment.this.getActivity(),"Section Bookmarked",Toast.LENGTH_LONG).show();

                }

                break;


        }

    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels)
    {

    }

    @Override
    public void onPageSelected(int position)
    {
        //Toast.makeText(SectionTextFragment.this.getActivity(),"selected",Toast.LENGTH_LONG).show();
       /* if( section.getReferenceNum()==position)
        {
            forwardCaret.setVisibility(View.GONE);
            backwardCaret.setVisibility(View.GONE);
            forwardCaret.invalidate();
            backwardCaret.invalidate();

        }*/
    }

    @Override
    public void onPageScrollStateChanged(int state)
    {

    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = this.getActivity().getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_settings:
                Toast.makeText(this.getActivity(),"it works;",Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
}
