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
import com.example.test_window_application.menu.MenuFragment;
import com.example.test_window_application.utils.Utils;

public class LoginFragment extends Fragment implements View.OnClickListener, LoginAsyncTaskCallback {
    Button login;
    EditText username;
    EditText password;
    LoginTask loginTask;

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
        String user = username.getText().toString(); // формируем обьект а не отдельные поля
        String pass = password.getText().toString(); // формируем обьект а не отдельные поля
        loginTask = new LoginTask(this,
                user,
                pass);
        loginTask.execute();
    }


    @Override
    public void onPostExecute(String aString) {
        String message = "failure";
        boolean successIsTrue = Utils.getLoginSuccess(aString);
        if (successIsTrue) {
            getActivity()
                    .getSupportFragmentManager()
                    .beginTransaction()
                    .setReorderingAllowed(true)
                    .replace(R.id.fragment_container_view, new MenuFragment())
                    .commit();
            message = "success";
        }
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT);
        toast.show();
    }

}
interface LoginAsyncTaskCallback {
    void onPostExecute(String aString);
}
