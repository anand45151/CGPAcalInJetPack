package com.example.cgpa

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cgpa.ui.theme.CgpaTheme

data class Semester(val grade: String, val credit: Int)
class MainActivity : ComponentActivity() {
    private var semesters: MutableList<Semester> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CgpaTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CGPA(semesters = semesters)
                }
            }
        }
    }
}

@Composable
fun CGPA(semesters: MutableList<Semester>) {
    var grade1 by remember {
        mutableStateOf(" ")
    }
    var credit1 by remember {
        mutableStateOf<Int?>(null)
    }
    var grade2 by remember {
        mutableStateOf(" ")
    }
    var credit2 by remember {
        mutableStateOf<Int?>(null)
    }
    var grade3 by remember {
        mutableStateOf(" ")
    }
    var credit3 by remember {
        mutableStateOf<Int?>(null)
    }
    var grade4 by remember {
        mutableStateOf(" ")
    }
    var credit4 by remember {
        mutableStateOf<Int?>(null)
    }
    var cgpa by remember {
        mutableStateOf(0.0)
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            text = "CGPA CALCULATOR\n With Anand ",
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            style = TextStyle(
                fontSize = 20.sp,
                textAlign = TextAlign.Center,
                fontFamily = FontFamily.Cursive,
                color = Color(0xFF000000)
            )
        )
        Spacer(modifier = Modifier.padding(top = 10.dp))
        subjextText(Subjext = "RDBMS")
        GradeTextFeild(grade1) { grade1 = it }
        Spacer8DP()
        CreditTextFeild(credit1) { credit1 = it }
        Spacer8DP()
        subjextText(Subjext = "DSA")
        GradeTextFeild(grade2) { grade2 = it }
        Spacer8DP()
        CreditTextFeild(credit2) { credit2 = it }
        Spacer8DP()
        subjextText(Subjext = "JAVA")
        GradeTextFeild(grade3) { grade3 = it }
        Spacer8DP()
        CreditTextFeild(credit3) { credit3 = it }
        Spacer8DP()
        subjextText(Subjext = "OS")
        GradeTextFeild(grade4) { grade4 = it }
        Spacer8DP()
        CreditTextFeild(credit4) { credit4 = it }
        Spacer8DP()

        Row() {
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Button(
                    onClick = {
                        val semester = Semester(grade1, credit1 ?: 0)
                        semesters.add(semester)
                        val totalcredit = semesters.sumOf { it.credit }
                        val totalGradePoint =
                            semesters.sumOf { calculateGradePoint(it.grade, it.credit) }
                        if (totalcredit > 0) {
                            cgpa = totalcredit / totalcredit.toDouble()
                        } else {
                            cgpa = 0.0
                        }
                        grade1 = ""
                        credit1 = null
                        grade2 = ""
                        credit2 = null
                        grade3 = ""
                        credit3 = null
                        grade4 = ""
                        credit4 = null
                    }, colors = ButtonDefaults.buttonColors(
                        Color(0xFFBEABE0)
                    ), shape = RoundedCornerShape(15.dp)
                ) {
                    Text(
                        text = "Calculate CGPA",
                        fontSize = 16.sp,
                        color = Color.Black,
                        fontFamily = FontFamily.SansSerif,
                    )
                }
                Surface(
                    modifier = Modifier
                        .width(175.dp)
                        .wrapContentHeight(),
                    color = Color(0xFF263238), shape = RoundedCornerShape(15.dp),
                ) {
                    Text(
                        modifier = Modifier.padding(start = 10.dp),
                        text = "Your All time \nCGPA :$cgpa", style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 16.sp,
                            color = Color(0xFFFFFFFF)
                        )
                    )
                }
            }
            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(start = 10.dp),
                color = Color(0xFF263238), shape = RoundedCornerShape(15.dp),
            ) {
                Column {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = "Previous Sem", style = TextStyle(
                            fontFamily = FontFamily.SansSerif,
                            fontSize = 16.sp,
                            color = Color(0xFFFFFFFF)
                        )
                    )
                    if (semesters.isNotEmpty()) {
                        for (semester in semesters) {
                            Text(
                                text = "Grade :${semester.grade} ,Credit :${semester.credit} ",
                                color = Color.White,
                                fontFamily = FontFamily.Default,
                                fontSize = 16.sp,
                                modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }

    }
}

fun calculateGradePoint(grade: String, credit: Int): Double {
    return when (grade.uppercase()) {
        "A" -> 4.0
        "B" -> 3.0
        "C" -> 2.0
        "D" -> 1.0
        else -> 0.0
    } * credit
}

@Composable
fun Spacer8DP() {
    Spacer(modifier = Modifier.padding(8.dp))
}

@Composable
fun subjextText(Subjext: String) {

    Text(
        text = Subjext,
        modifier = Modifier.fillMaxWidth(),
        style = TextStyle(
            fontSize = 16.sp,
            color = Color(0xFF000000)
        )
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GradeTextFeild(grade: String, onValueChange: (String) -> Unit) {
    TextField(
        value = grade,
        onValueChange = { text ->
            onValueChange(text)
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = { Text(text = "Enter the Grade", color = Color.White, fontSize = 12.sp) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.White,
            containerColor = Color(0xFF7E557E2)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.White),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
    )

}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreditTextFeild(credit: Int?, onValueChange: (Int?) -> Unit) {
    TextField(
        value = credit?.toString() ?:"",
        onValueChange = { text ->
            onValueChange(text.toIntOrNull())
        },
        modifier = Modifier
            .fillMaxWidth()
            .height(47.dp),
        label = { Text(text = "Enter the Credit", color = Color.White, fontSize = 12.sp) },
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = Color.White,
            containerColor = Color(0xFF7D8CCED)
        ),
        shape = RoundedCornerShape(15.dp),
        textStyle = TextStyle(fontSize = 12.sp, color = Color.Black),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}


/*@Preview(
    showBackground = true, device = "spec:width=411dp,height=891dp",
    showSystemUi = true
)
@Composable
fun CGPAPRIVIEW() {
    CgpaTheme() {
        CGPA(" ")
    }
}*/
//*/
//@Preview(
//    showBackground = true, device = "spec:width=411dp,height=891dp",
//    showSystemUi = true
//)
//@Composable
//fun GRADEVIEW() {
//    CgpaTheme() {
//        GradeTextFeild()
//    }
//}
