package com.example.instagramclone.adapters;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.instagramclone.R;
import com.example.instagramclone.models.Post;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // inflate the view layout for a single row/post
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostsAdapter.ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        // view elements from item_view layout
        private TextView tvUser;
        private TextView tvDescription;
        private ImageView ivPost;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUser = itemView.findViewById(R.id.tvUser);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            ivPost = itemView.findViewById(R.id.ivPost);
        }

        public void bind(Post post) {
            // bind data to elements in the view
            String username = post.getUser().getUsername();
            tvUser.setText(username);

            // concat username to beginning of description and make username bold
            StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD); // Span to make text bold
            String description = username + " " + post.getDescription();
            SpannableStringBuilder sb = new SpannableStringBuilder(description);
            sb.setSpan(bss, 0, username.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); // make first 4 characters Bold
            tvDescription.setText(sb);

            // set image
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivPost);
            }
        }
    }
}
