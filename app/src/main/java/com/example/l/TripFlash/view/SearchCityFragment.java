package com.example.l.TripFlash.view;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.l.TripFlash.GlobalData;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.presenter.SearchCityPresenter;
import com.example.l.TripFlash.presenter.SearchCityPresenterInterface;


public class SearchCityFragment extends Fragment implements SearchCityViewInterface {

    private SearchCityPresenterInterface searchCityPresenter;
    private GlobalData globalData;
    private TextView searchBar;
    private Button searchButton;

    public static SearchCityFragment newInstance() {
        SearchCityFragment f = new SearchCityFragment();
        Bundle args = new Bundle();
        f.setArguments(args);
        return f;
    }


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.search_city_layout, container, false);
        globalData = (GlobalData) getActivity().getApplication();

        searchCityPresenter = new SearchCityPresenter(this);

        searchBar=rootView.findViewById(R.id.search_bar);
        searchButton=rootView.findViewById(R.id.search_button);

        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchCityPresenter.getCityInterest(searchBar.getText().toString());
            }
        });
        return rootView;
    }


    /*
    void update(){
        if(globalData.getPrimaryUser().getUserKey()==""){
            Toast.makeText(getActivity(), "Please set your Ethereum private key!",
                    Toast.LENGTH_SHORT).show();
            presenterRefresh=true;
            swipeRefreshLayout.setRefreshing(false);
            return;
        }
        if(presenterRefresh){
            //searchCityPresenter.setPrivateKey(globalData.getPrimaryUser().getUserKey());
        }
        //searchCityPresenter.getBalance(this.getActivity());
        //searchCityPresenter.getTransactionList(this.getActivity());
        swipeRefreshLayout.setRefreshing(false);
        setUpQRCodeBottomSheetDialog();
        setUpSendMoneyBottomSheet();
        swipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void showBalance(BigDecimal balance) {
        //View rootView=LayoutInflater.from(getActivity()).inflate(R.layout.search_city_layout,null);

        ethView.setText(balance.toString() + " ETH");
        //dollarView.setText("$"+dollarBalance.toString()+" USD");
    }

    @Override
    public void showTransactionList(List<WalletModel.Transaction> transactionList) {
        transactionAdapter=new TransactionAdapter(transactionList);
        transactionListRecyclerView.setAdapter(transactionAdapter);
    }

    @Override
    public void requestMoney() {
        if(globalData.getPrimaryUser().getUserKey()==""){
            Toast.makeText(getActivity(), "Please set your Ethereum private key!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        newQRCodeBottomSheetDialog.show();
    }

    @Override
    public void sendMoney() {
        if(globalData.getPrimaryUser().getUserKey()==""){
            Toast.makeText(getActivity(), "Please set your Ethereum private key!",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        new IntentIntegrator(this.getActivity())
                .setOrientationLocked(false)
                .setCaptureActivity(CodeScanActivity.class) // 设置自定义的activity是CustomActivity
                .initiateScan(); // 初始化扫描
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult intentResult = IntentIntegrator.parseActivityResult(requestCode,resultCode,data);
        if(intentResult != null) {
            if(intentResult.getContents() == null) {
                Log.i("DT","二维码识别失败");
                return;
            } else {
                String ScanResult = intentResult.getContents();
                Log.i("DT",ScanResult);
                toAddress=ScanResult;
                sendMoneyBottomSheet.show();
            }
        } else {
            super.onActivityResult(requestCode,resultCode,data);
        }
        sendMoneyBottomSheet.show();
    }



    /**
     * Recycler view for transaction list
     */
    /*
    private class TransactionHolder extends RecyclerView.ViewHolder{
        private WalletModel.Transaction mTransaction;
        private TextView timeStampView;
        private TextView senderView;
        private TextView receiverView;
        private TextView valueView;
        private TextView statusView;

        private TransactionHolder(View itemView){
            super(itemView);
            timeStampView=itemView.findViewById(R.id.timestamp);
            senderView=itemView.findViewById(R.id.sender);
            receiverView=itemView.findViewById(R.id.receiver);
            valueView=itemView.findViewById(R.id.value);
            statusView=itemView.findViewById(R.id.status);
        }

        private void bindTx(WalletModel.Transaction tx){
            mTransaction=tx;
            timeStampView.setText("Time: "+mTransaction.getTimeStamp());
            senderView.setText("Sender: "+mTransaction.getSenderAddress());
            receiverView.setText("Receiver: "+mTransaction.getReceiverAddress());
            valueView.setText("Value: "+mTransaction.getValue());
            statusView.setText("Status: "+mTransaction.getValue());
        }
    }

    private class TransactionAdapter extends RecyclerView.Adapter<TransactionHolder>{
        private List<WalletModel.Transaction> transactionList;

        TransactionAdapter(List<WalletModel.Transaction> transactionList){
            this.transactionList=transactionList;
        }

        @Override
        public TransactionHolder onCreateViewHolder(ViewGroup viewGroup,int i){
            LayoutInflater layoutInflater=LayoutInflater.from(getActivity());
            View view=layoutInflater.inflate(R.layout.transaction_item,viewGroup,false);
            return new TransactionHolder(view);
        }

        @Override
        public void onBindViewHolder(TransactionHolder transactionHolder,int i){
            WalletModel.Transaction transaction=transactionList.get(i);
            transactionHolder.bindTx(transaction);
        }

        @Override
        public int getItemCount(){
            return transactionList.size();
        }
    }

    private List<WalletModel.Transaction> initDefaultTransactionList(){
        List<WalletModel.Transaction> defaultList=new ArrayList<>();
        WalletModel.Transaction transaction=new WalletModel.Transaction(true);
        for(int i=0;i<5;i++){
            defaultList.add(transaction);
        }
        return defaultList;
    }


    /**
     * BottomSheet for QR code
     */
    /*
    private void setUpQRCodeBottomSheetDialog() {
        newQRCodeBottomSheetDialog = new BottomSheetDialog(getActivity());
        View bottomSheetView = LayoutInflater.from(getActivity()).inflate(R.layout.qr_code_layout, null);
        newQRCodeBottomSheetDialog.setContentView(bottomSheetView);
        newQRCodeBottomSheetDialog.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.white));
        newQRCodeBottomSheetDialog.setCancelable(true);
        newQRCodeBottomSheetDialog.setCanceledOnTouchOutside(true);

        ImageView qrImage=bottomSheetView.findViewById(R.id.qr_code);
        //qrImage.setImageBitmap(searchCityPresenter.requestMoney());
    }


    private void setUpSendMoneyBottomSheet(){
        sendMoneyBottomSheet=new BottomSheetDialog(getActivity());
        View bottomSheetView=LayoutInflater.from(getActivity()).inflate(R.layout.send_money_layout,null);
        sendMoneyBottomSheet.setContentView(bottomSheetView);
        sendMoneyBottomSheet.getDelegate().findViewById(android.support.design.R.id.design_bottom_sheet).setBackgroundColor(ContextCompat.getColor(getActivity(),android.R.color.white));
        sendMoneyBottomSheet.setCancelable(true);
        sendMoneyBottomSheet.setCanceledOnTouchOutside(true);

        EditText enterNumber=bottomSheetView.findViewById(R.id.enter_number);
        Button confirm=bottomSheetView.findViewById(R.id.confirm_sending);

        confirm.setOnClickListener(v -> {
           // searchCityPresenter.sendMoney(toAddress,Float.parseFloat(enterNumber.getText().toString()));
            sendMoneyBottomSheet.cancel();
        });
    }
    */
}