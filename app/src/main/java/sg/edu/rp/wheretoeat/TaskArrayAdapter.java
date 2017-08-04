package sg.edu.rp.wheretoeat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;



public class TaskArrayAdapter extends ArrayAdapter<Task> {

    private ArrayList<Task> taskResult;
    private Context context;

    public TaskArrayAdapter(Context context, int resource, ArrayList<Task> objects) {
        super(context, resource, objects);
        taskResult = objects;
        this.context = context;
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.row, parent, false);
            viewHolder.tvID = (TextView) convertView.findViewById(R.id.tvID);
            viewHolder.tvDesc = (TextView) convertView.findViewById(R.id.tvDesc);

            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if(taskResult != null && taskResult.size() != 0 && taskResult.get(position) != null){
            Task currentTask = taskResult.get(position);
            viewHolder.tvDesc.setText(currentTask.description);
            //viewHolder.tvID.setText(String.valueOf(currentTask.id));

        }

        return convertView;
    }

    private static class ViewHolder{
        TextView tvID;
        TextView tvDesc;

    }
}
