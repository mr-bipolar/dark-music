package com.maxlab.darkmusic;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.maxlab.darkmusic.databinding.FragmentRequestBinding;


public class RequestFragment extends Fragment {

    public RequestFragment() {
        // Required empty public constructor
    }

    FragmentRequestBinding binding;
    FirebaseFirestore database;
    ProgressDialog dialog;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentRequestBinding.inflate(inflater, container, false);
        database = FirebaseFirestore.getInstance();
        dialog = new ProgressDialog(getContext());
        dialog.setMessage("Loading");
        binding.submitBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               dialog.show();
                 String username = binding.userName.getText().toString();
                 String songinfo = binding.songInfo.getText().toString();

                SongRequest request = new SongRequest(username, songinfo);
                if (songinfo.equals("")){
                    dialog.dismiss();
                    binding.songInfo.setError("Empty");
                    Toast.makeText(getContext(), "Please Enter Song Info", Toast.LENGTH_SHORT).show();
                }else {
                    database.collection("songrequest").add(request).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            dialog.dismiss();
                            Toast.makeText(getContext(), "Request Send Successfully", Toast.LENGTH_SHORT).show();
                            binding.userName.setText("");
                            binding.songInfo.setText("");
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            dialog.dismiss();
                            Toast.makeText(getContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });

        return binding.getRoot();
    }
}