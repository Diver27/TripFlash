package com.example.l.TripFlash.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.l.TripFlash.GlobalData;
import com.example.l.TripFlash.R;
import com.example.l.TripFlash.model.RoutineModel;
import com.example.l.TripFlash.presenter.RoutinePresenter;
import com.example.l.TripFlash.presenter.RoutinePresenterInterface;

import java.util.ArrayList;
import java.util.List;

public class RoutineViewActivity extends AppCompatActivity implements RoutineViewInterface {
    private GlobalData globalData;
    private RecyclerView RoutineListView;
    private RoutinePresenterInterface routinePresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_routine_view);
        globalData = (GlobalData) getApplication();
        routinePresenter=new RoutinePresenter(this);

    }


    /**
     * Recycler view for routine list
     */
    /*
    private class DestHolder extends RecyclerView.ViewHolder{
        private RoutineModel.DestSpot destSpot;
        private TextView name;
        private TextView location;

        private DestHolder(View itemView){
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
        public TransactionHolder onCreateViewHolder(ViewGroup viewGroup, int i){
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
    */
}
