package traindate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.gym.R;
import com.example.gym.database.TrainingLab;
import com.example.gym.model.TrainExercise;

import java.util.LinkedList;
import java.util.List;

import se.emilsjolander.stickylistheaders.StickyListHeadersAdapter;

/**
 * Created by Stas on 17.07.2014.
 */
public class MyAdapter extends BaseAdapter implements StickyListHeadersAdapter {

    private List<TrainExercise> trainExercises = new LinkedList<TrainExercise>();
    private LayoutInflater inflater;
    private TrainingLab trainingLab;

    public MyAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        trainingLab = new TrainingLab(context);
        trainingLab.open();
       // trainingLab.addEx(new TrainExercise("Жим лежа","Сб",3,15));
       // trainingLab.addEx(new TrainExercise("Жим узким хватом","Сб",3,10));
       // trainingLab.addEx(new TrainExercise("Пуловер","Сб",3,14));
      //  trainingLab.addEx(new TrainExercise("Становый присед","Пт",3,18));
      //  trainingLab.addEx(new TrainExercise("Становый жим","Пт",2,100));
       // trainingLab.addEx(new TrainExercise("Приседания со штангой","Пт",1,3));
       // trainingLab.addEx(new TrainExercise("Становая тяга","Вс",5,15));
        trainExercises = trainingLab.getAllData();
        if (trainExercises==null) {
            trainExercises = new LinkedList<TrainExercise>();
            trainExercises.add(new TrainExercise("Создайте свою программу тренировок","  "));
        }
    }

    public void addEx(TrainExercise te) {
        trainingLab.addEx(te);
       trainExercises = trainingLab.getAllData();
       if (trainExercises == null){
           trainExercises = new LinkedList<TrainExercise>();
           trainExercises.add(new TrainExercise("Создайте свою программу тренировок","  "));
       }
    }

    public void deleteEx(int pos) {
        TrainExercise  trainExercise = trainExercises.get(pos);
        trainExercises.remove(pos);
        trainingLab.deleteRow(trainExercise);
    }
     public void deleteAll(){
         trainingLab.deleteAll();
         trainExercises = new LinkedList<TrainExercise>();
         trainExercises.add(new TrainExercise("Создайте свою программу тренировок","  "));
     }

    public void closeDB(){
        trainingLab.close();
    }

    @Override
    public int getCount() {
        return trainExercises.size();
    }

    @Override
    public Object getItem(int position) {
        return trainExercises.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            convertView = inflater.inflate(R.layout.list_train, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.exercise_name);
            holder.ex_quant = (TextView) convertView.findViewById(R.id.textView_quantity);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        TrainExercise trainExercise = trainExercises.get(position);
        if (trainExercise.getQuantity() != 0 && trainExercise.getQuantity_in_one() != 0 ) {
            holder.ex_quant.setText(trainExercise.getQuantity() + "x" + trainExercise.getQuantity_in_one());
        } //else if (trainExercise.getQuantity_in_one() != 0 ){
     //       holder.ex_quant.setText(trainExercise.getQuantity_in_one());
      //  } else {
       //     holder.ex_quant.setVisibility(View.INVISIBLE);
       // }

        if (trainExercise.getName().equals("Создайте свою программу тренировок")){
            holder.ex_quant.setVisibility(View.INVISIBLE);
        }
        holder.text.setText(trainExercise.getName());

        return convertView;
    }

    @Override
    public View getHeaderView(int position, View convertView, ViewGroup parent) {
        HeaderViewHolder holder;
        if (convertView == null) {
            holder = new HeaderViewHolder();
            convertView = inflater.inflate(R.layout.sticky_header, parent, false);
            holder.text = (TextView) convertView.findViewById(R.id.header_text);
            convertView.setTag(holder);
        } else {
            holder = (HeaderViewHolder) convertView.getTag();
        }
        //set header text as first char in name
        String headerText = trainExercises.get(position).getDay();//"" + countries[position].subSequence(0, 1).charAt(0);
        holder.text.setText(headerText);
        return convertView;
    }

    @Override
    public long getHeaderId(int position) {
        //return the first character of the country as ID because this is what headers are based upon
        return trainExercises.get(position).getDay().hashCode();//countries[position].subSequence(0, 1).charAt(0);
    }

    class HeaderViewHolder {
        TextView text;
    }

    class ViewHolder {
        TextView text;
        TextView ex_quant;
    }

}