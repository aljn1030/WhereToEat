package sg.edu.rp.wheretoeat;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class MenuListAdapter extends ArrayAdapter<String> {

    private List<String> menu;

    public MenuListAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.menu = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.menu_item, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.tvMenuItem = (TextView) convertView.findViewById(R.id.tv_item);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.tvMenuItem.setText(menu.get(position));

        return convertView;
    }

    private static class ViewHolder{
        private TextView tvMenuItem;
    }
}
