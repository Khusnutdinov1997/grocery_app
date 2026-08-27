package com.example.groceryapp.presentation.registration

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.groceryapp.R
import com.example.groceryapp.ui.theme.DarkGray
import com.example.groceryapp.ui.theme.LightGray
import com.example.groceryapp.ui.theme.MainGreen
import com.example.groceryapp.ui.theme.MediumGray
import com.example.groceryapp.ui.theme.White
import com.example.groceryapp.ui.theme.poppinsFontFamily

@Composable
fun RegistrationScreen1(
    viewModel: RegistrationViewModel,
) {
    RegistrationContent1(
        imageKey = "image_registration1",
        onFirebaseClick = { /* ... */ },
        onSignUpClick = { viewModel.onNavigateToRegistration3() },
        onLoginClick = { viewModel.onNavigateToRegistration2() }
    )
}

@Composable
fun RegistrationContent1(
    imageKey: String,
    onFirebaseClick: () -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = RegistrationScreenMapper.toDrawableRes(imageKey)),
            contentDescription = null,
            modifier = Modifier
                .fillMaxHeight(0.65f),
            contentScale = ContentScale.Crop
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.40f)
                .align(Alignment.BottomCenter)
                .background(
                    color = LightGray,
                    RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Welcome",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkGray,
                fontFamily = poppinsFontFamily
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy",
                textAlign = TextAlign.Center,
                color = MediumGray,
                fontSize = 15.sp,
                fontFamily = poppinsFontFamily
            )
            Spacer(modifier = Modifier.weight(1f))
            Button(
                onClick = onFirebaseClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp),
                colors = ButtonDefaults.buttonColors(containerColor = White)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.logo),
                    contentDescription = null,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "Continue with firebase",
                    color = DarkGray,
                    fontFamily = poppinsFontFamily
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            Button(
                onClick = onSignUpClick,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MainGreen),
                shape = RoundedCornerShape(12.dp),
                elevation = ButtonDefaults.buttonElevation(defaultElevation = 2.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.user_icon),
                    contentDescription = null
                )
                Spacer(Modifier.width(12.dp))
                Text(
                    text = "Create an account",
                    fontFamily = poppinsFontFamily
                )
            }

            TextButton(
                onClick = onLoginClick
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Already have an account? ")
                        withStyle(
                            style = SpanStyle(fontWeight = FontWeight.Bold, color = DarkGray)
                        ) {
                            append("Login")
                        }
                    },
                    color = MediumGray,
                    fontFamily = poppinsFontFamily
                )
            }
        }
    }
}

@Composable
fun RegistrationScreen2(
    viewModel: RegistrationViewModel,
) {
    val uiState by viewModel.authState.collectAsState()
    val context = LocalContext.current


    RegistrationContent2(
        uiState = uiState,
        imageKey = "image_registration2",
        onBackClick = viewModel::onNavigateToRegistration1,
        onEmailChange = viewModel::onEmailChanged,
        onPasswordChange = viewModel::onPasswordChanged,
        onLoginClick = viewModel::login,
        onSignUpClick = viewModel::onNavigateToRegistration3
    )

}

@Composable
fun RegistrationContent2(
    uiState: RegistrationUiState,
    imageKey: String,
    onBackClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onLoginClick: () -> Unit,
    onSignUpClick: () -> Unit
) {

    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = RegistrationScreenMapper.toDrawableRes(imageKey)),
            contentDescription = null,
            modifier = Modifier

                .fillMaxHeight(0.60f),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .systemBarsPadding()
                .padding(top = 20.dp, start = 16.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(
                onClick = onBackClick
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.weight(0.3f))

            Text(
                text = "Welcome",
                color = White,
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .align(Alignment.BottomCenter)
                .background(
                    color = LightGray,
                    RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(24.dp)
        ) {
            Text(
                text = "Welcome back!",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkGray,
                fontFamily = poppinsFontFamily
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = "Sign in to your account",
                textAlign = TextAlign.Center,
                color = MediumGray,
                fontSize = 15.sp,
                fontFamily = poppinsFontFamily
            )
            Spacer(modifier = Modifier.height(32.dp))

            CustomInputField(
                value = uiState.email,
                onValueChange = onEmailChange,
                label = "Email Address",
                icon = Icons.Default.Email
            )

            Spacer(modifier = Modifier.height(16.dp))

            CustomInputField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                label = "Password",
                icon = Icons.Default.Lock,
                isPassword = true
            )

            if (uiState.error != null) {
                Text(
                    text = uiState.error,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    fontFamily = poppinsFontFamily
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onLoginClick,
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MainGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        modifier = Modifier.size(24.dp),
                        color = White,
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        text = "Login",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily
                    )
                }
            }

            TextButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onSignUpClick
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Don’t have an account? ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = DarkGray
                            )
                        ) { append("Sign up") }
                    },
                    color = MediumGray,
                    fontFamily = poppinsFontFamily
                )
            }
        }
    }
}

@Composable
fun CustomInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    icon: ImageVector,
    isPassword: Boolean = false
) {
    var passwordVisible by remember { mutableStateOf(false) }

    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        modifier = Modifier
            .fillMaxWidth(),
        singleLine = true,
        maxLines = 1,
        placeholder = {
            Text(
                label, color = MediumGray
            )
        },
        leadingIcon = {
            Icon(
                icon,
                contentDescription = null,
                tint = MediumGray
            )
        },

        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedContainerColor = White,
            unfocusedContainerColor = White,
            focusedBorderColor = MainGreen,
            unfocusedBorderColor = LightGray
        ),
        visualTransformation = if (isPassword && !passwordVisible) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
        trailingIcon = {
            if (isPassword) {
                IconButton(
                    onClick = { passwordVisible = !passwordVisible }
                ) {
                    Icon(
                        painter = painterResource(
                            id = if (passwordVisible) {
                                R.drawable.visibility_icon
                            } else {
                                R.drawable.eyes_close_icon
                            }
                        ),
                        contentDescription = null,
                        tint = MediumGray
                    )
                }
            }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = if (isPassword) {
                KeyboardType.Password
            } else {
                KeyboardType.Text
            },
            imeAction = ImeAction.Next
        )
    )
}

@Composable
fun RegistrationScreen3(
    viewModel: RegistrationViewModel
) {
    val uiState by viewModel.authState.collectAsState()
    val context = LocalContext.current

    RegistrationContent3(
        uiState = uiState,
        imageKey = "image_registration3",
        onBackClick = viewModel::onNavigateToRegistration2,
        onEmailChange = viewModel::onEmailChanged,
        onPasswordChange = viewModel::onPasswordChanged,
        onPhoneChange = viewModel::onPhoneChanged,
        onSignUpClick = viewModel::signUp,
        onLoginClick = viewModel::onNavigateToRegistration2
    )
}

@Composable
fun RegistrationContent3(
    uiState: RegistrationUiState,
    imageKey: String,
    onBackClick: () -> Unit,
    onEmailChange: (String) -> Unit,
    onPasswordChange: (String) -> Unit,
    onPhoneChange: (String) -> Unit,
    onSignUpClick: () -> Unit,
    onLoginClick: () -> Unit
) {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = RegistrationScreenMapper.toDrawableRes(imageKey)),
            contentDescription = null,
            modifier = Modifier.fillMaxHeight(0.60f),
            contentScale = ContentScale.Crop
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .systemBarsPadding()
                .padding(top = 20.dp, start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onBackClick) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null, tint = White)
            }
            Spacer(modifier = Modifier.weight(0.3f))
            Text(
                text = "Sign Up",
                color = White,
                fontSize = 20.sp,
                fontWeight = FontWeight.SemiBold,
                fontFamily = poppinsFontFamily
            )
            Spacer(modifier = Modifier.weight(1f))
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.53f)
                .align(Alignment.BottomCenter)
                .background(
                    color = LightGray,
                    shape = RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)
                )
                .padding(24.dp)
        ) {
            Text(
                text = "Create account",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = DarkGray,
                fontFamily = poppinsFontFamily
            )
            Text(
                text = "Quickly create account",
                color = MediumGray,
                fontSize = 15.sp,
                fontFamily = poppinsFontFamily
            )

            Spacer(modifier = Modifier.height(24.dp))

            CustomInputField(
                value = uiState.email,
                onValueChange = onEmailChange,
                label = "Email Address",
                icon = Icons.Default.Email
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomInputField(
                value = uiState.phoneNumber,
                onValueChange = onPhoneChange,
                label = "Phone Number",
                icon = Icons.Filled.Phone
            )

            Spacer(modifier = Modifier.height(12.dp))

            CustomInputField(
                value = uiState.password,
                onValueChange = onPasswordChange,
                label = "Password",
                icon = Icons.Default.Lock,
                isPassword = true
            )

            if (uiState.error != null) {
                Text(
                    text = uiState.error,
                    color = Color.Red,
                    fontSize = 14.sp,
                    modifier = Modifier.padding(top = 8.dp),
                    fontFamily = poppinsFontFamily
                )
            }

            Spacer(modifier = Modifier.height(24.dp))

            Button(
                onClick = onSignUpClick,
                enabled = !uiState.isLoading,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MainGreen),
                shape = RoundedCornerShape(12.dp)
            ) {
                if (uiState.isLoading) {
                    CircularProgressIndicator(
                        color = White,
                        modifier = Modifier.size(24.dp),
                        strokeWidth = 2.dp
                    )
                } else {
                    Text(
                        "Sign Up",
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = poppinsFontFamily
                    )
                }
            }

            TextButton(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                onClick = onLoginClick
            ) {
                Text(
                    text = buildAnnotatedString {
                        append("Already have an account? ")
                        withStyle(
                            style = SpanStyle(
                                fontWeight = FontWeight.Bold,
                                color = DarkGray
                            )
                        ) {
                            append("Login")
                        }
                    },
                    color = MediumGray,
                    fontFamily = poppinsFontFamily
                )
            }
        }
    }
}

@Preview(
    showBackground = true,
    showSystemUi = true
)
@Composable
fun RegisterScreenPreview1() {
    RegistrationContent1(
        imageKey = "image_registration1",
        onFirebaseClick = {},
        onSignUpClick = {},
        onLoginClick = {}
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegisterScreenPreview2() {
    RegistrationContent2(
        uiState = RegistrationUiState(),
        onBackClick = {},
        onEmailChange = {},
        onPasswordChange = {},
        onLoginClick = {},
        onSignUpClick = {},
        imageKey = "image_registration2"
    )
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun RegistrationScreenPreview3() {
    RegistrationContent3(
        uiState = RegistrationUiState(),
        imageKey = "image_registration3",
        onBackClick = {},
        onLoginClick = {},
        onSignUpClick = {},
        onPasswordChange = {},
        onEmailChange = {},
        onPhoneChange = {}
    )
}
