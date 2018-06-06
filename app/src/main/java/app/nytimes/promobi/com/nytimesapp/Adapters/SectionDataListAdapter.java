package app.nytimes.promobi.com.nytimesapp.Adapters;

import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import app.nytimes.promobi.com.nytimesapp.R;
import app.nytimes.promobi.com.nytimesapp.eventsbus.Events;
import app.nytimes.promobi.com.nytimesapp.eventsbus.GlobalBus;
import app.nytimes.promobi.com.nytimesapp.jobs.VolleyRequestPoolJob;
import app.nytimes.promobi.com.nytimesapp.models.Results;
import app.nytimes.promobi.com.nytimesapp.utilities.Utils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class SectionDataListAdapter extends RecyclerView.Adapter<SectionDataListAdapter.SectionRowViewHolder> {
    private final Activity activity;
    private final Results[] resultsList;
    private ImageLoader mImageLoader;

    public SectionDataListAdapter(Activity activity, Results[] resultsList) {
        this.activity = activity;
        this.resultsList = resultsList;
    }

    @NonNull
    @Override
    public SectionRowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_items, parent, false);

        return new SectionRowViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull SectionRowViewHolder holder, int position) {
        Results results = resultsList[position];

        holder.abstitle.setText("Abstract:  "+results.getAbstract());
        holder.abstitle.setTextColor(Color.WHITE);

        holder.createddate.setText("Created Date:  "+results.getCreated_date());
        holder.createddate.setTextColor(Color.WHITE);

        holder.publisheddate.setText("Published Date:  "+results.getPublished_date());
        holder.publisheddate.setTextColor(Color.WHITE);

        holder.section.setText("Section: "+results.getSection());
        holder.section.setTextColor(Color.WHITE);

        holder.titleofartcle.setText("Title: "+results.getTitle());
        holder.titleofartcle.setTextColor(Color.WHITE);

        holder.byline.setText("By Line:  "+results.getByline());
        holder.byline.setTextColor(Color.WHITE);

        holder.updateddate.setText("Updated Date:  "+results.getUpdated_date());
        holder.updateddate.setTextColor(Color.WHITE);

        holder.itemType.setText("Article Type:  "+results.getItem_type());
        holder.itemType.setTextColor(Color.WHITE);

        // Instantiate the RequestQueue.
        mImageLoader = VolleyRequestPoolJob.getInstance(activity.getApplicationContext())
                .getImageLoader();
        if (results.getMultimedia() != null && results.getMultimedia().length > 0 && results.getMultimedia()[0] != null && results.getMultimedia()[0].getUrl() != null) {
            String url = results.getMultimedia()[0].getUrl();
            if (null != url) {
                //Image URL - This can point to any image file supported by Android
                mImageLoader.get(url, ImageLoader.getImageListener(holder.imgNyTimes,
                        R.drawable.ic_launcher_background, android.R.drawable
                                .ic_dialog_alert));
                holder.imgNyTimes.setImageUrl(url, mImageLoader);
            }
        }

    }


    @Override
    public int getItemCount() {
        return resultsList.length;
    }

    public class SectionRowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.abstitle)
        TextView abstitle;

        @BindView(R.id.createddate)
        TextView createddate;
        @BindView(R.id.publisheddate)
        TextView publisheddate;
        @BindView(R.id.section)
        TextView section;
        @BindView(R.id.titleofarticle)
        TextView titleofartcle;
        @BindView(R.id.byline)
        TextView byline;
        @BindView(R.id.updateddate)
        TextView updateddate;
        @BindView(R.id.itemtype)
        TextView itemType;
        @BindView(R.id.imgNyTimes)
        NetworkImageView imgNyTimes;

        public SectionRowViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                Events.FragmentActivityMessage event = new Events.FragmentActivityMessage(resultsList[position].getUrl());
                GlobalBus.getBus().post(event);
            }
        }

    }
}
