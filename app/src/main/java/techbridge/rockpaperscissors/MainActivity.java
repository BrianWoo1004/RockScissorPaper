package techbridge.rockpaperscissors;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    EditText editTextMe;// editTextComputer;
    TextView textViewResult;
    Button btnPlay;
    Random rand = new Random(); //Random함수 사용하기 위해 쓰는 첫번째 줄
    Button btnSci, btnRock, btnPa;
    String me = "";
    ImageView imageViewMe;
    ImageView imageViewCom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewResult = (TextView) findViewById(R.id.textViewResult); //XML-JAVA연결

        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnSci = (Button) findViewById(R.id.btnSci);
        btnPa = (Button) findViewById(R.id.btnPa);
        btnRock = (Button) findViewById(R.id.btnRock);
        imageViewMe = (ImageView)findViewById(R.id.imageViewMe);
        imageViewCom = (ImageView)findViewById(R.id.imageViewCom);


        btnPlay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick (View v){
                if(me.equals("")) {
                    Toast.makeText(MainActivity.this, "가위, 바위, 보 중 하나를 선택해주세요!", Toast.LENGTH_SHORT).show();
                    btnPlay.setEnabled(false); //게임플레이를 하지 못하도록 비활성화
                }else{
                    String computer = randomComputer();
                    whoWon(me, computer);

                    String result = whoWon(me, computer);
                    Toast.makeText(MainActivity.this, me + " vs "+computer, Toast.LENGTH_SHORT).show();
                    showResult(result, me, computer);
                }

            }
        });

        btnSci.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewMe.setImageResource(R.drawable.left_scissors);
                me = "scissors";
                btnSci.setEnabled(false);
                btnPa.setEnabled(true);
                btnRock.setEnabled(true);
                btnPlay.setEnabled(true);
            }
        });
        btnPa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewMe.setImageResource(R.drawable.left_paper);
                me = "paper";
                /*setEnabled:버튼 활성화 함수
                false:버튼 비활성화 함수/버튼 누르지 못함
                true:활성화/버른 누를수 있다
                 */
                btnSci.setEnabled(true); //버튼 활성화 함수 ///가위 버튼이 회색으로 변한다
                btnPa.setEnabled(false); // false:비활성화 버튼 누르지 못한 상태
                btnRock.setEnabled(true); // true : 활성 버튼 누를 수 있는 상태 //바위와 보자기둘다 누를 수 있는 상태
                btnPlay.setEnabled(true);
            }
        });
        btnRock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageViewMe.setImageResource(R.drawable.left_rock);
                me = "rock";
                btnSci.setEnabled(true); //가위 버튼 다시 활성화
                btnPa.setEnabled(true); //  보자기 버트 비활성화
                btnRock.setEnabled(false);
                btnPlay.setEnabled(true);

            }
        });
    }

    public void showResult(String result,String me, String computer){
        textViewResult.setText(result);//TextView element에 결과를 나타내줌
    }

    /*public void showResult(String mssg){
        textViewResult.setText(mssg);
    }*/

    /////////////////////실습///////////////////
    /*random 함수를 이용해서 컴퓨터의 가위바위보를 무작위로 나오게 한다
    * -힌트 : 0이 나오면 가위 1이 나오면 바위, 2가 나오면 보로 세팅을 해주면
    * 숫자를 입력받아(매개변수) 가위바위보로 바꾸어 줄 수 있다.(if문, else if문 써서)*/
    public String randomComputer(){//rock, scissors,paper 로 결과를 내보냄

        int comInt = rand.nextInt(3); //comInt 0~2의 수가 생성되서 저장 중
        String comString; //결과값 문자열을 저장할 변수 comString
        //Toast.makeText(getApplicationContext(),""+comInt, Toast.LENGTH_SHORT).show();
        if(comInt == 0){ //comInt가 0이 나왔을 때
            imageViewCom.setImageResource(R.drawable.right_rock); //경로를 찾아서 이미지 파일을 연결해주는 함수
            comString= "rock";
        }else if(comInt==1){//comInt가 1이 나왔을 때
            imageViewCom.setImageResource(R.drawable.right_scissors);
            comString= "scissors";
        }else if(comInt==2){//comInt가 2가 나왔을 때
            imageViewCom.setImageResource(R.drawable.right_paper);
            comString= "paper";
        }else //comInt가 0~2사이 외의 숫자가 나왔을 때
            comString= "error";//중요
        return comString;
    }

    public String whoWon(String me, String computer){
        String result = "";
        /*if와 if else ,else문을 통해서 result에 어떤 값을 저장할지*/
        if(me.equals(computer)){ //나랑 컴퓨터랑 같은 값을 낸 경우
            result = "Draw!";
        } else { //같지 않을 때
            if(me.equals("scissors")){ //내가 가위를 냈을 때
                if(computer.equals("rock")){//내가 가위 컴퓨터가 바위
                    result = "Computer won!";
                } else {
                    if(computer.equals("paper")){
                        result = "You won!";
                    } else {
                        result = "Typo!";
                    }
                }
            } else{
                if(me.equals("rock")){ //내가 바위를 냈을 때
                    if(computer.equals("paper")){
                        result = "Computer won!";
                    } else {
                        if(computer.equals("scissors")){
                            result = "You won!";
                        } else {
                            result = "Typo!";
                        }
                    }
                } else {
                    if(me.equals("paper")){ //내가 보를 냈을 때
                        if(computer.equals("scissors")){
                            result = "Computer won!";
                        } else {
                            if(computer.equals("rock")){
                                result = "You won!";
                            } else {
                                result = "Typo!";
                            }
                        }
                    } else {
                        result = "Typo!";
                    }
                }
            }
        }
        //////////////////////////if 절의 끝//////////////////////////
        showResult(result, me, computer);
        return result;
    }
}
