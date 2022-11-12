package com.example.notesactivity

import android.app.Application
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notesactivity.models.Notes
import com.example.notesactivity.ui.theme.NotesActivityTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesActivityTheme {



                    MainView(application)

                


                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    Greeting("Android")
//                }
            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainView(application: Application) {
    var isVisible by remember{
        mutableStateOf(false)
    }
    SideEffect {
        print("value isVisible is $isVisible")
    }

    Column(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if(isVisible){

            AnimatedVisibility(visible = true) {


                ContentData(application)

            }


        }else{
            Button(
                modifier = Modifier,
                onClick = {  isVisible=!isVisible}) {
                Text(text = "Get Started")
            }
        }
        
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ContentData(application: Application) {

    val vm by remember { mutableStateOf(MainActivityViewModel(application)) }

    LaunchedEffect(key1 = true){

        //vm.getApiCall()
    }

    var name by remember{
        mutableStateOf("")
    }
    val nameText by derivedStateOf {
        "Note name $name"
    }
    val scope = rememberCoroutineScope()
    Column(modifier = Modifier
        .fillMaxSize()
        .padding(20.dp)) {

        OutlinedTextField(
            modifier = Modifier.fillMaxWidth(),
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.ic_launcher_background),
                    contentDescription = "",
                    tint = colorResource(R.color.teal_200),
                    modifier = Modifier.size(20.dp)
                )
            },
            value = name,
            onValueChange = {
                        name=it
            },
            label = { Text("Note Name") }
        )
        Button(
            modifier = Modifier.fillMaxWidth(),
            onClick = {
                scope.launch {
                    vm.addNotes(Notes(note_name = name))
                    Toast.makeText(application.applicationContext,"Added to room database",Toast.LENGTH_LONG).show()
                }

            }) {
            Text(text = "Add Details")
        }
        if(name.isNotEmpty()){
            AnimatedContent(targetState = nameText,
            transitionSpec = {
            fadeIn() with fadeOut()

        }
            ) {
                    targetState -> Text(
                modifier = Modifier.fillMaxWidth(),
                text = targetState)
            }

        }


    }
}


@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    NotesActivityTheme {
        Greeting("Android")
    }
}