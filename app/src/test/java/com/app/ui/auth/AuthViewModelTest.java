package com.app.ui.auth;

import com.app.daggerkotlin.BaseApplication;
import com.app.models.User;
import com.app.network.auth.AuthApi;
import com.app.util.InstantExecutorExtension;
import com.app.util.LiveDataTestUtil;
import com.app.util.NetworkResource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

@ExtendWith(InstantExecutorExtension.class)
public class AuthViewModelTest
{
    private AuthViewModel authViewModel;

    @Mock private BaseApplication mContext;
    @Mock private AuthApi authApi;

    @BeforeEach
    public void setUp()
    {
        MockitoAnnotations.initMocks(this);

        authViewModel = new AuthViewModel(mContext);
    }

    @Test
    public void authenticateWithId_returnUser() throws Exception
    {
        LiveDataTestUtil<NetworkResource<User>> liveDataTestUtil = new LiveDataTestUtil<>();

        //NetworkResource<User> returnedValue = liveDataTestUtil.getValue(authViewModel.getAuthUserData(7));
    }
}
