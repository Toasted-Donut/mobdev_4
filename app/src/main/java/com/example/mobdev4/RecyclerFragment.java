package com.example.mobdev4;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mobdev4.databinding.FragmentRecyclerBinding;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link RecyclerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RecyclerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private ArrayList<String> mParam1;
    private String mParam2;

    public RecyclerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RecyclerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RecyclerFragment newInstance(ArrayList<String> param1, String param2) {
        RecyclerFragment fragment = new RecyclerFragment();
        Bundle args = new Bundle();
        args.putStringArrayList(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getStringArrayList(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentRecyclerBinding binding = FragmentRecyclerBinding.inflate(inflater);
        binding.recView.setLayoutManager(new LinearLayoutManager(getActivity()));
        UserAdapter adapter = new UserAdapter(getActivity(),mParam1);
        binding.recView.setAdapter(adapter);

        return binding.getRoot();
    }

    class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder>{

        private ArrayList<String> mData;
        private LayoutInflater mInflater;

        UserAdapter(Context context, ArrayList<String> data){
            this.mData = data;
            this.mInflater = LayoutInflater.from(context);
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(getContext()).inflate(R.layout.item, parent, false);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Log.i("gg","item toasted");
                    Toast toast = Toast.makeText(getContext(),"item toasted",Toast.LENGTH_SHORT);
                    toast.show();
                }
            });
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            String user = mData.get(position);
            holder.mTextView.setText(user);
        }

        @Override
        public int getItemCount() {
            return mData.size();
        }


        class ViewHolder extends RecyclerView.ViewHolder{
            TextView mTextView;

            ViewHolder(View itemView){
                super(itemView);
                mTextView = itemView.findViewById(R.id.name);

            }
        }
        String getItem(int id) {
            return mData.get(id);
        }
    }
}