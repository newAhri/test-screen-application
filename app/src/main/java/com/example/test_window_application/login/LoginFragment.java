package com.example.test_window_application.login;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.test_window_application.R;
import com.example.test_window_application.menu.SportsFragment;

public class LoginFragment extends Fragment implements View.OnClickListener, LoginAsyncTaskCallback {
    Button login;
    EditText username;
    EditText password;
    LoginRequestTask loginTask;

    public LoginFragment() {
        super(R.layout.login_screen);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        login = view.findViewById(R.id.login_button);
        username = view.findViewById(R.id.username);
        password = view.findViewById(R.id.password);

        login.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        LoginHeaders loginHeaders = new LoginHeaders(username.getText().toString(),
                password.getText().toString());

        loginTask = new LoginRequestTask(this,
                loginHeaders);
        loginTask.execute();
    }


    @Override
    public void onPostExecute(LoginResponse loginResponse) {
        String message = "Credentials invalid";
        if (loginResponse.getSuccess()) {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, new SportsFragment())
                    .commit();
            message = "Nice to see you back!";
        }
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

}
interface LoginAsyncTaskCallback {
    void onPostExecute(LoginResponse loginResponse);
}
