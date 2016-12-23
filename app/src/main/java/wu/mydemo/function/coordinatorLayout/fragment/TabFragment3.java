package wu.mydemo.function.coordinatorLayout.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import wu.mydemo.R;
import wu.mydemo.main.adapter.MainRecycleAdapter;

/**
 * 功能： descriable
 * 作者： Administrator
 * 日期： 2016/12/23 16:00
 * 邮箱： descriable
 */
public class TabFragment3 extends Fragment {

    public static TabFragment3 instance = null;
    @Bind(R.id.recycle_view)
    RecyclerView recycleView;

    private MainRecycleAdapter recycleAdapter;
    private List<String> stringList = new ArrayList<>();


    public static TabFragment3 getInstance() {
        if (instance == null) {
            instance = new TabFragment3();
        }
        return instance;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tab_coordinator, container, false);
        ButterKnife.bind(this, view);
        initRecycleView();
        return view;
    }



    private void initRecycleView(){
        stringList.clear();
        Collections.addAll(stringList, getResources().getStringArray(R.array.itme_function));
//        stringList = Arrays.asList(getResources().getStringArray(R.array.itme_function));
        recycleAdapter = new MainRecycleAdapter(getActivity(),stringList);
        recycleView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        recycleView.setAdapter(recycleAdapter);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
