package com.sparecode.vipul.onlynow.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.sparecode.vipul.onlynow.R;
import com.sparecode.vipul.onlynow.activity.BaseActivity;
import com.sparecode.vipul.onlynow.model.ClientShopWrapper;
import com.sparecode.vipul.onlynow.model.ClientUpdateshopWrapper;
import com.sparecode.vipul.onlynow.widgets.LatoButton;
import com.sparecode.vipul.onlynow.widgets.LatoEditText;
import com.sparecode.vipul.onlynow.widgets.LatoTextView;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class ClientShopFragment extends BaseFragment implements ClientShopBackend.ClientShopResultProvider, ClientUpdateshopBackend.ClientUpdateShopResultProvider {


    @Bind(R.id.coupon_image)
    ImageView couponImage;
    @Bind(R.id.next_image)
    ImageView nextImage;
    @Bind(R.id.coupon_name)
    LatoTextView couponName;
    @Bind(R.id.coupon_type)
    LatoTextView couponType;
    @Bind(R.id.coupon_place)
    LatoTextView couponPlace;
    @Bind(R.id.editnumber)
    LatoEditText editnumber;
    @Bind(R.id.editurl)
    LatoEditText editurl;
    @Bind(R.id.editshopdetail)
    LatoEditText editshopdetail;
    @Bind(R.id.editaddress)
    LatoEditText editaddress;
    @Bind(R.id.savedetail)
    LatoButton savedetail;
    @Bind(R.id.nodata)
    LatoTextView nodata;
    @Bind(R.id.linearshop)
    LinearLayout linearshop;
    private View view;
    private String shop_id;

    public ClientShopFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_client_shop, container, false);
        ButterKnife.bind(this, view);

        String id = getUserId();
        System.out.println("----->user" + id);

        ClientShopBackend clientShopBackend = new ClientShopBackend(getActivity(), getUserId(), this);

        editnumber.setEnabled(false);
        editurl.setEnabled(false);
        editaddress.setEnabled(false);
        editshopdetail.setEnabled(false);

        ((BaseActivity) getActivity()).getImgEdit().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editnumber.setEnabled(true);
                editnumber.requestFocus();
                editurl.setEnabled(true);
                editaddress.setEnabled(true);
                editshopdetail.setEnabled(true);
            }
        });

        return view;
    }


    @Override
    public void setToolbarForFragment() {
        ((BaseActivity) getActivity()).getAppbarLayout().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTabLayoutclient().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getTextViewToolBarTitle().setText(getString(R.string.Shop));
        ((BaseActivity) getActivity()).getImgEdit().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setVisibility(View.VISIBLE);
        ((BaseActivity) getActivity()).getImgAdd().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getFab().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getTextNext().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarBack().setVisibility(View.GONE);
        ((BaseActivity) getActivity()).getImgToolBarCancel().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((BaseActivity) getActivity()).openClientCouponPage();
            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onSuccessfullLogin(ClientShopWrapper clientShopWrapper) {
        if (couponName!=null){
            couponName.setText(clientShopWrapper.getData().getTitle());
        }
        if (couponType!=null){
            couponType.setText(clientShopWrapper.getData().getCatName());
        }
        if (couponPlace!=null){
            couponPlace.setText(clientShopWrapper.getData().getCity());
        }if (editnumber!=null){
            editnumber.setText(clientShopWrapper.getData().getPhone());
        }if (editurl!=null){
            editurl.setText(clientShopWrapper.getData().getWeb());
        }if (editshopdetail!=null){
            editshopdetail.setText(clientShopWrapper.getData().getDetails());
        }if (editaddress!=null){
            editaddress.setText(clientShopWrapper.getData().getArea());
        }if (couponImage!=null){
            if (clientShopWrapper.getData().getImageURL() != null &&
                    (!clientShopWrapper.getData().getImageURL().equals("")))
                Picasso.with(getActivity())
                        .load(clientShopWrapper.getData().getImageURL())
                        .placeholder(R.drawable.placeholder)
                        .error(R.drawable.placeholder)
                        .into(couponImage);
        }











        shop_id = clientShopWrapper.getData().getShopId();
    }

    @Override
    public void onSuccessfullLogin(ClientUpdateshopWrapper clientUpdateshopWrapper) {
        nodata.setVisibility(View.GONE);
        linearshop.setVisibility(View.VISIBLE);
        Snackbar.make(view, clientUpdateshopWrapper.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure(String msg) {
        linearshop.setVisibility(View.GONE);
        nodata.setVisibility(View.VISIBLE);
        Snackbar.make(view, msg, Snackbar.LENGTH_SHORT).show();
    }

    @OnClick(R.id.savedetail)
    public void onClick() {
        Toast.makeText(getActivity(), shop_id, Toast.LENGTH_LONG).show();
        ClientUpdateshopBackend clientUpdateshopBackend = new ClientUpdateshopBackend(getActivity(), shop_id, editnumber.getText().toString().trim(), editurl.getText().toString().trim(),
                editshopdetail.getText().toString().trim(), editaddress.getText().toString().trim(), this);
    }
}
