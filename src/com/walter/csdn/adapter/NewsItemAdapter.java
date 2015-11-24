package com.walter.csdn.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.walter.csdn.R;
import com.walter.csdn.bean.NewsItem;
import com.walter.csdn.csdn.DataUtil;

public class NewsItemAdapter extends BaseAdapter {

	private LayoutInflater mLayoutInflater;
	private List<NewsItem> mList;

	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;

	public NewsItemAdapter(Context context, List<NewsItem> mList) {
		this.mList = mList;
		mLayoutInflater = LayoutInflater.from(context);
		imageLoader.init(ImageLoaderConfiguration.createDefault(context));
		options = new DisplayImageOptions.Builder()
				.showStubImage(R.drawable.images)
				.showImageForEmptyUri(R.drawable.images)
				.showImageOnFail(R.drawable.images).cacheInMemory()
				.cacheOnDisc().displayer(new RoundedBitmapDisplayer(20))
				.displayer(new FadeInBitmapDisplayer(300)).build();
	}

	public void addAll(List<NewsItem> mList) {
		this.mList.addAll(mList);
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewHolder;
		if (convertView == null) {
			convertView = mLayoutInflater.inflate(R.layout.news_item_yidong,
					null);
			viewHolder = new ViewHolder();
			viewHolder.mContent = (TextView) convertView
					.findViewById(R.id.id_content);
			viewHolder.mTitle = (TextView) convertView
					.findViewById(R.id.id_title);
			viewHolder.mDate = (TextView) convertView
					.findViewById(R.id.id_date);
			viewHolder.mImg = (ImageView) convertView
					.findViewById(R.id.id_newsImg);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		NewsItem newsItem = mList.get(position);
		viewHolder.mTitle.setText(DataUtil.ToDBC(newsItem.getTitle()));
		viewHolder.mContent.setText(newsItem.getContent());
		viewHolder.mDate.setText(newsItem.getDate());
		if (newsItem.getLink() != null) {
			viewHolder.mImg.setVisibility(View.VISIBLE);
			imageLoader.displayImage(newsItem.getImgLink(), viewHolder.mImg,
					options);
		} else {
			viewHolder.mImg.setVisibility(View.GONE);
		}

		return convertView;
	}

	private final class ViewHolder {
		TextView mTitle;
		TextView mContent;
		ImageView mImg;
		TextView mDate;
	}

}
