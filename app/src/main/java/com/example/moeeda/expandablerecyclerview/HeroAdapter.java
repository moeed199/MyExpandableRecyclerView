package com.example.moeeda.expandablerecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {
    private List<Hero> heroList;
    private Context context;
    private static int currentPosition = 0;

    public HeroAdapter(List<Hero> heroList, Context context) {
        this.heroList = heroList;
        this.context = context;
    }

    @Override
    public HeroViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_layout_heroes,parent,false);
        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HeroViewHolder holder, final int position) {

        Hero hero = heroList.get(position);
        holder.textViewName.setText(hero.getName());
        holder.textViewRealName.setText(hero.getRealname());
        holder.textViewTeam.setText(hero.getTeam());
        holder.textViewFirstAppearance.setText(hero.getFirstappearance());
        holder.textViewCreatedBy.setText(hero.getCreatedby());
        holder.textViewPublisher.setText(hero.getPublisher());
        holder.textViewBio.setText(hero.getBio().trim());

        Glide.with(context).load(hero.getImageurl()).into(holder.imageView);
        holder.linearLayout.setVisibility(View.GONE);

        //if the position is equals to the item position which is to be expanded
        if (currentPosition == position) {
            //creating an animation
            Animation slideDown = AnimationUtils.loadAnimation(context, R.anim.slide_down_animation);

            //toggling visibility
            holder.linearLayout.setVisibility(View.VISIBLE);

            //adding sliding effect
            holder.linearLayout.startAnimation(slideDown);

        }
        holder.textViewName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //getting the position of the item to expand it
                currentPosition = position;

                //reloding the list
                notifyDataSetChanged();
            }
        });


    }

    @Override
    public int getItemCount() {
        return heroList.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName, textViewRealName, textViewTeam, textViewFirstAppearance,
                textViewCreatedBy, textViewPublisher, textViewBio;
        ImageView imageView;
        LinearLayout linearLayout;

        HeroViewHolder(View itemView){
            super(itemView);
            textViewName = (TextView) itemView.findViewById(R.id.textViewName);
            textViewRealName = (TextView) itemView.findViewById(R.id.textViewRealName);
            textViewTeam = (TextView) itemView.findViewById(R.id.textViewTeam);
            textViewFirstAppearance = (TextView) itemView.findViewById(R.id.textViewFirstAppearance);
            textViewCreatedBy = (TextView) itemView.findViewById(R.id.textViewCreatedBy);
            textViewPublisher = (TextView) itemView.findViewById(R.id.textViewPublisher);
            textViewBio = (TextView) itemView.findViewById(R.id.textViewBio);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);

            linearLayout = (LinearLayout) itemView.findViewById(R.id.linearLayout);
        }
    }
}
