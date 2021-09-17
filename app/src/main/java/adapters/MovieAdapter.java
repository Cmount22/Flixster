package adapters;

import android.content.Context;
import android.content.res.Configuration;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.flixster.R;

import java.util.List;

import Models.Movie;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    TextView tvTitle;
    TextView tvOverview;
    ImageView idPoster;


    Context context;
    List<Movie> movies;
    private RecyclerView.ViewHolder holder;
    private int position;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    //Usually inflates a layout from XML and will return it in the holder
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.d( "MovieAdapter", "onCreateViewHolder");
        View movieView = LayoutInflater.from(context).inflate(R.layout.itemmovie, parent, false);
        return new ViewHolder(movieView);

    }

    @Override
    //Involved populating data into the item through the holder
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d("MovieAdapter", "OnBindViewHolder"  + position);

        //get the movie at the position
        Movie movie = movies.get(position);
        //bind the movie data into the video holder
        holder.bind(movie);
    }

    @Override
    public int getItemCount() {

        return movies.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        {
        }

        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvOverview = itemView.findViewById(R.id.tvOverview);
            idPoster = itemView.findViewById(R.id.idPoster);

        }

        public void bind(final Movie movie) {
            tvTitle.setText(movie.getTitle());
            tvOverview.setText(movie.getOverview());
            String imageUrl;

            //If phone in landscape then change image  url to be backdrop image else if in portrait keep poster image
            if (context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {

                //then imageUrl == backdrop image
                imageUrl=movie.getBackdropPath();

            } else {
                //else imageUrl ===Poster image
                imageUrl=movie.getPosterPath();
            }

                Glide.with(context).load(imageUrl).into(idPoster);

        }
    }
}
