package com.example.ds.fid_me;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class HomeActivity extends AppCompatActivity {

    Intent intent;
    private static final String TAG = "HomeActivity";
    SQLiteHelper dbHelper;

    ArrayList<String> result_name_list;
    ArrayList<String> result_address_list;
    ArrayList<String> result_category_list;
    String name = "";
    TextView random;
    TextView category;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // 랜덤으로 음식점 이름 불러오자
        result_name_list=new ArrayList<>();
        result_address_list = new ArrayList<>();
        result_category_list = new ArrayList<>();

        new Thread(new Runnable() {

            @Override
            public void run() {

                String keyword = "도봉구 맛집";

                String clientId = "Bxn8VZxrR7tA6L6oV9Fa";//애플리케이션 클라이언트 아이디값";
                String clientSecret = "Uf8Ldu5n8p";//애플리케이션 클라이언트 시크릿값";
                final StringBuffer buffer = new StringBuffer();


                try {

                    result_name_list.clear();

                    String apiURL = "https://openapi.naver.com/v1/search/local.xml?query=" + keyword +"&display=50";

                    URL url = new URL(apiURL);
                    HttpURLConnection con = (HttpURLConnection) url.openConnection();
                    con.setRequestMethod("GET");
                    con.setRequestProperty("X-Naver-Client-Id", clientId);
                    con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

                    InputStream is= con.getInputStream();

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser xpp = factory.newPullParser();
                    xpp.setInput(new InputStreamReader(is, "UTF-8"));
                    String tag;

                    xpp.next();
                    int eventType = xpp.getEventType();

                    while (eventType != XmlPullParser.END_DOCUMENT) {
                        switch (eventType) {
                            case XmlPullParser.START_DOCUMENT:
                                buffer.append("검색 시작\n\n");
                                break;


                            case XmlPullParser.START_TAG:

                                tag = xpp.getName();    //테그 이름 얻어오기

                                if (tag.equals("item")) ;// 첫번째 검색결과
                                else if (tag.equals("title")) {

                                    buffer.append("업소명 : ");
                                    xpp.next();
                                    buffer.append(xpp.getText()); //title 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                    buffer.append("\n");          //줄바꿈 문자 추가
                                    name = xpp.getText();
                                    result_name_list.add(name);


                                } else if (tag.equals("category")) {

                                    buffer.append("업종 : ");
                                    xpp.next();
                                    buffer.append(xpp.getText()); //category 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                    buffer.append("\n");          //줄바꿈 문자 추가
                                    result_category_list.add(xpp.getText());

                                } else if (tag.equals("description")) {
                                    buffer.append("세부설명 :");
                                    xpp.next();
                                    buffer.append(xpp.getText()); //description 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                    buffer.append("\n");          //줄바꿈 문자 추가

                                } else if (tag.equals("telephone")) {

                                    buffer.append("연락처 :");
                                    xpp.next();
                                    buffer.append(xpp.getText()); //telephone 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                    buffer.append("\n");          //줄바꿈 문자 추가

                                } else if (tag.equals("address")) {

                                    buffer.append("주소 :");
                                    xpp.next();
                                    buffer.append(xpp.getText()); //address 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                    buffer.append("\n");          //줄바꿈 문자 추가
                                    result_address_list.add(xpp.getText());

                                }

                                break;

                            case XmlPullParser.TEXT:
                                break;

                            case XmlPullParser.END_TAG:
                                tag = xpp.getName();    //테그 이름 얻어오기
                                if (tag.equals("item")) buffer.append("\n"); // 첫번째 검색결과종료..줄바꿈
                                break;

                        }
                        eventType = xpp.next();
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                buffer.append("검색 종료\n");
                System.out.println(buffer.toString());

                double randomValue = Math.random();
                final int ran = (int)(randomValue * result_name_list.size()) -1;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        StringBuffer buffer = new StringBuffer();

                        random = (TextView) findViewById(R.id.Textrandom);
                        category = (TextView) findViewById(R.id.textrandom2);
                        random.setText(result_name_list.get(ran));
                        category.setText(result_category_list.get(ran));


                    }

                });

                Button button = (Button)findViewById(R.id.btnGo);
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(getBaseContext(), RecommandMapActivity.class);

                        String name = result_name_list.get(ran);
                        String address = result_address_list.get(ran);

                        intent.putExtra("name", name);
                        intent.putExtra( "address", address);
                        intent.putExtra("from","home");

                        startActivity(intent);

                    }
                });

            }

        }).start();

    }

    public void onRestClicked(View view) {
        // 상세 검색
        //Intent intent = new Intent(getApplicationContext(), )
    }

    public void onBtnRefresh(View view) {
        intent = new Intent(getApplicationContext(),HomeActivity.class);
        startActivity(intent);

    }

    public void onBtnMap(View view) {
        intent = new Intent(getApplicationContext(),MapActivity.class);
        intent.putExtra("from","home");
        startActivity(intent);
    }

    public void onBtnBM(View view) {
        intent = new Intent(getApplicationContext(),BookMarkActivity.class);
        startActivity(intent);
    }

    public void onBtnMemo(View view) {
        intent = new Intent(getApplicationContext(),MemoActivity.class);
        startActivity(intent);
    }

    public void onBtnHistory(View view) {
        intent = new Intent(getApplicationContext(),HistoryActivity.class);
        startActivity(intent);
    }

    public void onBtnRecom(View view) {
        intent = new Intent(getApplicationContext(),FindActivity.class);
        startActivity(intent);
    }

    public void onBtnChat(View view) {
        intent = new Intent(getApplicationContext(),StartActivity.class);
        startActivity(intent);
    }
}
