package com.example.lawnwizard.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lawnwizard.R;
import com.example.lawnwizard.databinding.FragmentCustomerHomeBinding;
import com.example.lawnwizard.databinding.FragmentWorkerProfileBinding;
import com.example.lawnwizard.viewmodels.UserViewModel;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class WorkerProfileFragment extends Fragment {
    UserViewModel userViewModel;

    FragmentWorkerProfileBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentWorkerProfileBinding.inflate(inflater, container, false);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        NavController controller = NavHostFragment.findNavController(this);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding.backArrow.setOnClickListener((v) ->{
            controller.navigate(R.id.action_workerProfileFragment_to_workerHomeFragment);
        });

        binding.workerLogoutButton.setOnClickListener((v) -> {
            auth.signOut();
            controller.navigate(R.id.action_workerProfileFragment_to_signInFragment);
        });

        userViewModel.loadUser();

        userViewModel.getUser().observe(getViewLifecycleOwner(), (user -> {
            if (user == null) {
                return;
            }
            binding.customerProfileNameText.setText(user.getName());
            binding.customerAddressText.setText(user.getName());
            binding.customerBalanceText.setText(user.getBalance().toString());
            binding.customerEmailText.setText(Objects.requireNonNull(auth.getCurrentUser()).getEmail());
            binding.customerPhoneText.setText(user.getName());

            binding.transferFundsButton.setOnClickListener((v) -> {
                if(binding.transferFunds.getText().toString().equals("")){
                    binding.transferFunds.setError("Please enter a value to transfer");
                }else if(Integer.parseInt(binding.transferFunds.getText().toString()) < 0){
                    binding.transferFunds.setError("Please enter a positive number");
                }else{
                    user.setBalance(-Integer.parseInt(binding.transferFunds.getText().toString()));
                    userViewModel.updateUser(user);
                    binding.transferFunds.setText("");
                }
            });
        }));

        return binding.getRoot();
    }
}