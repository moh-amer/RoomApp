package com.example.fedora.roomapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.DrugHolder> {

    List<Drug> drugs;

    public DrugAdapter(List<Drug> drugs) {
        this.drugs = drugs;
    }

    @NonNull
    @Override
    public DrugHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.drug_row,viewGroup,false);
        return new DrugHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DrugHolder drugHolder, int i) {

        Drug drug = drugs.get(i);
        drugHolder.tvDrugName.setText(drug.getDrugName());
        drugHolder.tvDrugPrice.setText(String.valueOf(drug.getDrugPrice()));

    }

    @Override
    public int getItemCount() {
        return drugs.size();
    }

    public static class DrugHolder extends RecyclerView.ViewHolder{

        TextView tvDrugName, tvDrugPrice;
        public DrugHolder(@NonNull View itemView) {
            super(itemView);

            tvDrugName = itemView.findViewById(R.id.tv_drug_name);
            tvDrugPrice = itemView.findViewById(R.id.tv_drug_price);
        }
    }
}
