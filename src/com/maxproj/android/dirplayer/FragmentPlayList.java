package com.maxproj.android.dirplayer;



import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;


public class FragmentPlayList  extends Fragment {

    MyArrayAdapter listAdapter = null;
    ListView listView = null;
    View fragmentView;
    final static String DTAG = "DirPlayer";
    Button b1, b2, b3, b4, b5;

    public interface FragmentPlayListInterface{
        void onFragmentPlayListClicked(int i);
        void onFragmentPlayListButton1();
        void onFragmentPlayListButton2();
        void onFragmentPlayListButton3();
        void onFragmentPlayListButton4();

    }
    private FragmentPlayListInterface FragmentPlayListInterface = null;

    /* 
    public FragmentListview(int tab){
        Log.d(DTAG,"fragment " + tab + " is initialized");
        this.tab = tab;
    }
    */
    public static FragmentPlayList newInstance() {
    	FragmentPlayList fragment = new FragmentPlayList();        
    	Log.d(DTAG,"FragmentPlayList newInstance()");
        return fragment;
    }

    public void setListviewAdapter(MyArrayAdapter a){
        listAdapter = a;

        if (listView != null){
            listView.setAdapter(listAdapter);
            Log.d(DTAG,"FragmentPlayList setListviewAdapter(): adapter is set!");
        }else{
            Log.d(DTAG,"FragmentPlayList setListviewAdapter(): listView is null pointer!");
        }
    }
    public View getItemView(int position){

        if (position > listView.getCount())
            return null;
        else
            return listView.getChildAt(position);

    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);
        Log.d(DTAG,"FragmentPlayList onCreateView() is called!");
        fragmentView =  inflater.inflate(R.layout.fragment_playlist, container, false);
        return fragmentView;
    }
    @Override
    public void onActivityCreated(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        Log.d(DTAG,"FragmentPlayList onActivityCreated() is called!");
        b1 = (Button)fragmentView.findViewById(R.id.pl_b1);
        b1.setOnClickListener(new View.OnClickListener() { // 全选
            @Override
            public void onClick(View view) {
            	FragmentPlayListInterface.onFragmentPlayListButton1();
            }
        });
        b2 = (Button)fragmentView.findViewById(R.id.pl_b2);
        b2.setOnClickListener(new View.OnClickListener() { // 清除
            @Override
            public void onClick(View view) {
            	FragmentPlayListInterface.onFragmentPlayListButton2();
            }
        });
        b3 = (Button)fragmentView.findViewById(R.id.pl_b3);
        b3.setOnClickListener(new View.OnClickListener() { // 反选
            @Override
            public void onClick(View view) {
            	FragmentPlayListInterface.onFragmentPlayListButton3();
            }
        });
        b4 = (Button)fragmentView.findViewById(R.id.pl_b4);
        b4.setOnClickListener(new View.OnClickListener() { // 删除
            @Override
            public void onClick(View view) {
            	FragmentPlayListInterface.onFragmentPlayListButton4();
            }
        });


        listView = (ListView)fragmentView.findViewById(R.id.fragment_playlist);


        listView.setOnItemClickListener(new ItemClicklistener());
        Log.d(DTAG,"FragmentPlayList listView.setOnItemClickListener(new ItemClicklistener())!");

        if (listAdapter != null){
            listView.setAdapter(listAdapter);
            Log.d(DTAG,"FragmentPlayList (maa != null) and adapter is set!");
        }else{
            Log.d(DTAG,"FragmentPlayList maa is null pointer!");
        }
    }
    private class ItemClicklistener implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        	FragmentPlayListInterface.onFragmentPlayListClicked(i);

            Log.d(DTAG,"FragmentPlayList ItemClicklistener is called!");
        }
    }
    @Override
    public void onAttach(Activity activity){
        super.onAttach(activity);
        Log.d(DTAG,"FragmentPlayList onAttach() is called!");
        try{
        	Log.d(DTAG,"FragmentPlayList check if activity implement interface....");
        	FragmentPlayListInterface = (FragmentPlayListInterface)activity;
            Log.d(DTAG,"activity implemented interface!");
        }catch(ClassCastException e){
            Log.d(DTAG,"FragmentPlayList onAttach() throw new ClassCastException!");
            throw new ClassCastException(activity.toString()+ " must implement "
                    +FragmentPlayListInterface.toString());
        }
        Log.d(DTAG,"FragmentPlayList onAttach() is ended!");
    }

}
