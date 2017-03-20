package com.sparecode.vipul.onlynow.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.model.CouponDetailWrapper;
import com.sparecode.vipul.onlynow.model.MyListFavoriteWrapper;
import com.sparecode.vipul.onlynow.view.OnClickListener;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by vipul on 29/12/16.
 */

public class CouponViewedAdapter extends RecyclerView.Adapter<CouponViewedAdapter.MyViewHolder> {


    Context mContext;
    private CouponDetailWrapper couponDetailWrapper;
    MyListFavoriteWrapper myListFavoriteWrapper;
    OnClickListener onClickListener;

    public CouponViewedAdapter(Context mContext, MyListFavoriteWrapper myListFavoriteWrapper, OnClickListener onClickListener) {
        this.mContext = mContext;
        this.myListFavoriteWrapper = myListFavoriteWrapper;
        this.onClickListener = onClickListener;
    }

    public CouponViewedAdapter(Context mContext) {
        this.mContext = mContext;
    }

//    private List<History> historyList;
//
//    public GridAdapter(List<History> historyList) {
//        this.historyList = historyList;
//    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        //public TextView text_name,text_place,text_time,text_payments;;
        @Bind(R.id.text_date)
        LatoTextView textDate;
        @Bind(R.id.coupon_image)
        ImageView couponImage;
        @Bind(R.id.coupon_desc)
        LatoTextView couponDesc;
        @Bind(R.id.imageView4)
        ImageView imageView4;
        @Bind(R.id.coupon_shopname)
        LatoTextView couponShopname;
        @Bind(R.id.coupon_area)
        LatoTextView couponArea;
        @Bind(R.id.linearLayout5)
        LinearLayout linearLayout5;
        @Bind(R.id.ratingbar)
        RatingBar ratingbar;
        @Bind(R.id.linearrating)
        LinearLayout linearrating;
        @Bind(R.id.txtCouponTimer)
        LatoTextView txtCouponTimer;
        @Bind(R.id.txtCouponValidTill)
        LatoTextView txtCouponValidTill;
        @Bind(R.id.cardCoupon)
        CardView cardCoupon;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
//            text_name = (TextView) view.findViewById(R.id.text_name);
//            text_place = (TextView) view.findViewById(R.id.text_place);
//            text_time = (TextView) view.findViewById(R.id.text_time);
//            text_payments = (TextView) view.findViewById(R.id.text_payments);
        }
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.design_list_recyclerviews, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

//        History history = historyList.get(position);
//        holder.text_name.setText(history.getName());
//        holder.text_place.setText(history.getPlace());
//        holder.text_time.setText(history.getTime());
//        holder.text_payments.setText(history.getPayments());
        /*holder.textView12.setText(myListSavedDataList.get(position).getDate());
        holder.txtCouponTimer.setText(getLeftTimeDuration(myListSavedDataList.get(position).getDate(), myListSavedDataList.get(position).getEndDate()));
        holder.txtCouponValidTill.setText(couponDetailWrapper.getData().getDate());*/
        holder.textDate.setText(myListFavoriteWrapper.getData().get(position).getTitle());
        holder.couponShopname.setText(myListFavoriteWrapper.getData().get(position).getFname()+ " "+ myListFavoriteWrapper.getData().get(position).getLname());
        holder.couponArea.setText(myListFavoriteWrapper.getData().get(position).getArea());
        holder.couponDesc.setVisibility(View.GONE);
        holder.txtCouponTimer.setVisibility(View.GONE);
        holder.txtCouponValidTill.setVisibility(View.GONE);
        String rating = myListFavoriteWrapper.getData().get(position).getRating();
        System.out.println("----->rating"+rating);
        if (rating == "")
        {
            holder.ratingbar.setRating(0);
        }
        else
        {
            holder.ratingbar.setRating(Float.parseFloat(rating));
        }
        if (!myListFavoriteWrapper.getData().get(position).getImageURL().trim().isEmpty()) {
            Picasso.with(mContext).load(myListFavoriteWrapper.getData().get(position).getImageURL()).resize(720, 200).into(holder.couponImage);
        }
    }

    @Override
    public int getItemCount() {
        return myListFavoriteWrapper.getData().size();
    }
//       return historyList.size();
//    }

    private String getLeftTimeDuration(String startDate, String endDate) {


//HH converts hour in 24 hours format (0-23), day calculation
        SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

        Date d1 = null;
        Date d2 = null;

        try {
            d1 = format.parse(startDate);
            d2 = format.parse(endDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }


//in milliseconds
        long diff = d2.getTime() - d1.getTime();

        long diffSeconds = diff / 1000 % 60;
        long diffMinutes = diff / (60 * 1000) % 60;
        long diffHours = diff / (60 * 60 * 1000) % 24;
        long diffDays = diff / (24 * 60 * 60 * 1000);

        System.out.print(diffDays + " days, ");
        System.out.print(diffHours + " hours, ");
        System.out.print(diffMinutes + " minutes, ");
        System.out.print(diffSeconds + " seconds.");

        String retValue = "" + diffDays + "days" + diffHours + "hours" + diffMinutes + "mins";

        return retValue.replace("-", "");
    }
}
