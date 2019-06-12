package ua.deti.exprover.utils;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import ua.deti.exprover.NewROVActivity;
import ua.deti.exprover.R;
import ua.deti.exprover.ViewROVActivity;
import ua.deti.exprover.models.ROV;

public class ROVAdapter extends RecyclerView.Adapter<ROVAdapter.ViewHolder> {

    private Context context;
    private List<ROV> rovList;

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public ImageView img;

        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.rov_name);
            img = (ImageView) view.findViewById(R.id.rov_img);
        }
    }

    public ROVAdapter(Context context, List<ROV> rovList) {
        this.context = context;
        this.rovList = rovList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView;
        if (viewType == R.layout._rov_item) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout._rov_item, parent, false);
        } else {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout._add_rov_item, parent, false);

        }
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // only populate rov items (exclude add new rov button)
        if (position == rovList.size()) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent addIntent = new Intent(context, NewROVActivity.class);
                    context.startActivity(addIntent);
                }
            });
        } else {
            final ROV rov = rovList.get(position);
            holder.name.setText(rov.getName());
            if (rov.getImgRes() != -1) {
                holder.img.setImageResource(rov.getImgRes());
            } else {
                Log.d("ROVAdapter", rov.getImgFile().getPath());
                holder.img.setImageDrawable(Drawable.createFromPath(rov.getImgFile().getPath()));
            }
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent viewIntent = new Intent(context, ViewROVActivity.class);
                    viewIntent.putExtra("ua.deti.exprover.ROV_NAME",
                            ((TextView)view.findViewById(R.id.rov_name)).getText());
                    viewIntent.putExtra("ua.deti.exprover.ROV_IP", rov.getIP());

                    context.startActivity(viewIntent);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return rovList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        return (position == rovList.size()) ? R.layout._add_rov_item : R.layout._rov_item;
    }
}
