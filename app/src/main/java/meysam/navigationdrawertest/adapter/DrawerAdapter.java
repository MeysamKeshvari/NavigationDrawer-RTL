package meysam.navigationdrawertest.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import meysam.navigationdrawertest.model.DrawerItem;
import meysam.navigationdrawertest.R;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerAdapter.DrawerViewHolder> {

    public final static int TYPE_HEADER = 0;
    public final static int TYPE_MENU = 1;
    private ArrayList<DrawerItem> drawerMenuList;
    private OnItemSelectedListener mListener;

    public DrawerAdapter(ArrayList<DrawerItem> drawerMenuList) {
        this.drawerMenuList = drawerMenuList;
    }

    @Override
    public DrawerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_HEADER) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_drawer_header, parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_menu_item, parent, false);
        }
        return new DrawerViewHolder(view, viewType);
    }

    @Override
    public void onBindViewHolder(DrawerViewHolder holder, int position) {
        if (position == 0) {
            holder.headerText.setText("");
        } else {
            holder.title.setText(drawerMenuList.get(position - 1).getTitle());
            holder.icon.setImageResource(drawerMenuList.get(position - 1).getIcon());
        }
    }

    @Override
    public int getItemCount() {
        return drawerMenuList.size() + 1;
    }

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return TYPE_HEADER;
        }
        return TYPE_MENU;
    }

    class DrawerViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView headerText;
        ImageView icon;

        public DrawerViewHolder(View itemView, int viewType) {
            super(itemView);
            if (viewType == 0) {
                headerText = itemView.findViewById(R.id.textViewUserName);
            } else {
                title = itemView.findViewById(R.id.title);
                icon = itemView.findViewById(R.id.icon);
            }
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mListener.onItemSelected(view, getAdapterPosition());

                }
            });
        }
    }

    public void setOnItemClickLister(OnItemSelectedListener mListener) {
        this.mListener = mListener;
    }

    public interface OnItemSelectedListener {
        void onItemSelected(View v, int position);
    }
}