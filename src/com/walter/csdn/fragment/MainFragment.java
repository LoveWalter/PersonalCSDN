package com.walter.csdn.fragment;

import java.util.ArrayList;
import java.util.List;

import me.maxwin.view.IXListViewLoadMore;
import me.maxwin.view.IXListViewRefreshListener;
import me.maxwin.view.XListView;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.walter.csdn.R;
import com.walter.csdn.adapter.NewsItemAdapter;
import com.walter.csdn.bean.CommonException;
import com.walter.csdn.bean.NewsItem;
import com.walter.csdn.biz.NewsItemBiz;
import com.walter.csdn.csdn.Constaint;

public class MainFragment extends Fragment implements
		IXListViewRefreshListener, IXListViewLoadMore {

	private int newsType = Constaint.NEWS_TYPE_YEJIE;

	private int currentPage = 1;
	private NewsItemBiz mNewsItemBiz;

	private XListView mXListView;
	// private NewsItemAdapter mAdapter;
	private List<NewsItem> mDatas = new ArrayList<NewsItem>();
	private NewsItemAdapter mAdapter;

	public MainFragment(int newsType) {
		this.newsType = newsType;
		mNewsItemBiz = new NewsItemBiz();
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		mAdapter = new NewsItemAdapter(getActivity(), mDatas);
		mXListView = (XListView) getView().findViewById(R.id.id_xlistView);
		mXListView.setAdapter(mAdapter);
		mXListView.setPullRefreshEnable(this);
		mXListView.setPullLoadEnable(this);
		mXListView.startRefresh();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.tab_item_fragment_main, null);
		return view;

	}

	@Override
	public void onRefresh() {
		new LoadDataTask().execute();
	}

	@Override
	public void onLoadMore() {

	}

	class LoadDataTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			try {
				List<NewsItem> newsItems = mNewsItemBiz.getNewsItems(newsType,
						currentPage);
				mDatas = newsItems;
			} catch (CommonException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(Void result) {
			mAdapter.addAll(mDatas);
			mAdapter.notifyDataSetChanged();
			mXListView.stopRefresh();
		}

	}
}
