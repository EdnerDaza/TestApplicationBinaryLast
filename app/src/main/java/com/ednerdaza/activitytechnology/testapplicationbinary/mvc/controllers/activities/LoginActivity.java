package com.ednerdaza.activitytechnology.testapplicationbinary.mvc.controllers.activities;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.Loader;
import android.database.Cursor;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import com.ednerdaza.activitytechnology.testapplicationbinary.R;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.DaoMaster;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.DaoSession;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.dao.UsuariosEntityDao;
import com.ednerdaza.activitytechnology.testapplicationbinary.mvc.models.entities.UsuariosEntity;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mNameView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;

    //Variables relativas a Green Dao Database
    public DaoSession daoSession;
    public DaoMaster daoMaster;
    private SQLiteDatabase db;
    private UsuariosEntityDao usuariosDao;
    private List<UsuariosEntity> usuarios;
    private UsuariosEntity mUsuarioSerializable = new UsuariosEntity();

    //region LIFE CYCLE METHODS

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            //Conexión a la base de datos
            DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "basededatos-1-db", null);
            db = helper.getWritableDatabase();
            daoMaster = new DaoMaster(db);
            daoSession = daoMaster.newSession();

            //Obtenemos un objeto de tipo "GestorDao" que manipula por nosotros
            //la tabla Gestor de la Base de Datos
            usuariosDao = daoSession.getUsuariosEntityDao();

            //Obtiene listado de Gestores, equivale a: SELECT * FROM GESTOR
            usuarios = usuariosDao.loadAll();

            // Set up the login form.
            mNameView = (AutoCompleteTextView) findViewById(R.id.name);

            mPasswordView = (EditText) findViewById(R.id.password);
            mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                @Override
                public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                    if (id == R.id.login || id == EditorInfo.IME_NULL) {
                        attemptLogin();
                        return true;
                    }
                    return false;
                }
            });

            Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
            mEmailSignInButton.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    attemptLogin();
                }
            });

            mLoginFormView = findViewById(R.id.login_form);
            mProgressView = findViewById(R.id.login_progress);
            mUsuarioSerializable = null;
        }

    //endregion

    //region LoaderCallbacks METHODS
        /**
         * Shows the progress UI and hides the login form.
         */
        @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
        private void showProgress(final boolean show) {
            // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
            // for very easy animations. If available, use these APIs to fade-in
            // the progress spinner.
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2) {
                int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                        show ? 0 : 1).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                    }
                });

                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                mProgressView.animate().setDuration(shortAnimTime).alpha(
                        show ? 1 : 0).setListener(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                    }
                });
            } else {
                // The ViewPropertyAnimator APIs are not available, so simply show
                // and hide the relevant UI components.
                mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            }
        }

        @Override
        public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
            return null;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor) {
        }

        @Override
        public void onLoaderReset(Loader<Cursor> cursorLoader) {

        }
    //endregion

    //region PRIVATE METHODS
        /**
         * Attempts to sign in or register the account specified by the login form.
         * If there are form errors (invalid email, missing fields, etc.), the
         * errors are presented and no actual login attempt is made.
         */
        private void attemptLogin() {
            if (mAuthTask != null) {
                return;
            }

            // Reset errors.
            mNameView.setError(null);
            mPasswordView.setError(null);

            // Store values at the time of the login attempt.
            String name = mNameView.getText().toString();
            String password = mPasswordView.getText().toString();

            boolean cancel = false;
            View focusView = null;

            // Check for a valid name
            if (TextUtils.isEmpty(name)) {
                mNameView.setError(getString(R.string.error_field_required));
                focusView = mNameView;
                cancel = true;
            }else if (TextUtils.isEmpty(password)) {
                mPasswordView.setError(getString(R.string.error_field_required));
                focusView = mPasswordView;
                cancel = true;
            }else if (!TextUtils.isEmpty(password) && !isPasswordValid(password)) {
                mPasswordView.setError(getString(R.string.error_invalid_password));
                focusView = mPasswordView;
                cancel = true;
            }

            if (cancel) {
                // There was an error; don't attempt login and focus the first
                // form field with an error.
                focusView.requestFocus();
            } else {
                // Show a progress spinner, and kick off a background task to
                // perform the user login attempt.
                showProgress(true);
                mAuthTask = new UserLoginTask(name, password);
                mAuthTask.execute((Void) null);
            }
        }

        private boolean isPasswordValid(String password) {
            //TODO: Replace this with your own logic
            return password.length() >= 4;
        }
    //endregion

    //region PUBLIC CLASS ASYNCTASK

        /**
         * Represents an asynchronous login/registration task used to authenticate
         * the user.
         */
        public class UserLoginTask extends AsyncTask<Void, Void, Boolean[]> {

            private final String mName;
            private final String mPassword;

            UserLoginTask(String name, String password) {
                mName = name;
                mPassword = password;
            }

            @Override
            protected Boolean[] doInBackground(Void... params) {
                // TODO: attempt authentication against a network service.

                try {
                    // Simulate network access.
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    return new Boolean[]{false,false};
                }

                Boolean[] result = new Boolean[]{false,false};

                for(UsuariosEntity usuario : usuarios){
                    if(usuario.getNombre().equals(mName)){
                        result[0] = true;
                        if(usuario.getContrasena().equals(mPassword)){
                            result[1] = true;
                            mUsuarioSerializable = usuario;
                            return result;
                        }
                    }
                }

                // TODO: register the new account here.
                return result;
            }

            @Override
            protected void onPostExecute(final Boolean[] success) {
                mAuthTask = null;
                showProgress(false);

                if (success[0]) {
                    if(success[1]){
                        if(mUsuarioSerializable != null) {
                            Intent intent = new Intent(LoginActivity.this, ClientsActivity.class);
                            intent.putExtra("id", mUsuarioSerializable.getId());
                            startActivity(intent);
                            finish();
                        }
                    }else{
                        mPasswordView.setError(getString(R.string.error_incorrect_password));
                        mPasswordView.requestFocus();
                    }

                } else {
                    mNameView.setError(getString(R.string.error_incorrect_name));
                    mNameView.requestFocus();
                }
            }

            @Override
            protected void onCancelled() {
                mAuthTask = null;
                showProgress(false);
            }
        }

    //endregion

}

