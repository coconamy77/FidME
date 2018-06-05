package com.example.ds.fid_me;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;


public class RecommandActivity extends AppCompatActivity {

    EditText edit;
    TextView text;
    Button btn;
    ListView listView;

    ArrayList<String> result_name_list;
    ArrayList<String> result_address_list;
    ArrayList<String> result_all_list;

    String name = "";
    String address = "";
    String mapx = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommand);

      //  edit = (EditText) findViewById(R.id.edit);
        text = (TextView) findViewById(R.id.textView);
        btn = (Button) findViewById(R.id.button);

        listView = (ListView)findViewById(R.id.recommandListView);

        result_name_list=new ArrayList<>();
        result_address_list = new ArrayList<>();
        result_all_list = new ArrayList<>();

        showList();

    }

    private void showList() {


        new Thread(new Runnable() {

            @Override
            public void run() {

                // String keyword = edit.getText().toString();
                Intent intent = getIntent();
                String keyword = intent.getStringExtra("foodName");

                String clientId = "Bxn8VZxrR7tA6L6oV9Fa";//애플리케이션 클라이언트 아이디값";
                String clientSecret = "Uf8Ldu5n8p";//애플리케이션 클라이언트 시크릿값";
                final StringBuffer buffer = new StringBuffer();


                try {

                    result_name_list.clear();
                    result_address_list.clear();
                    result_all_list.clear();

                    String apiURL = "https://openapi.naver.com/v1/search/local.xml?query=" + keyword +"&display=20";

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

                                    address = xpp.getText();
                                    result_address_list.add(address);
                                    result_all_list.add(name + ':' +'\n'+ address);

                                }

                                else if (tag.equals("mapx")) {

                                    buffer.append("지도 위치 X :");
                                    xpp.next();
                                    buffer.append(xpp.getText()); //mapx 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                    buffer.append("  ,  ");//줄바꿈 문자 추가
                                    mapx = xpp.getText();


                                } else if (tag.equals("mapy")) {
                                    buffer.append("지도 위치 Y :");
                                    xpp.next();
                                    buffer.append(xpp.getText()); //mapy 요소의 TEXT 읽어와서 문자열버퍼에 추가
                                    buffer.append("\n");          //줄바꿈 문자 추가


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




                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(),R.layout.recommand_listitem, R.id.textname, result_all_list);
                        listView.setAdapter(adapter);

                        // 항목 클릭하면?
                        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                                Intent intent = new Intent(getBaseContext(), RecommandMapActivity.class);


                                String name = result_name_list.get(i+1);
                                String address = result_address_list.get(i);

                                intent.putExtra("name", name);
                                intent.putExtra( "address", address);

                                startActivity(intent);


                            }
                        });
                    }
                });


            }
        }).start();

    }

    // 다시하기 버튼 누르면?
    public void mOnclick(View view) {
        Intent intent = new Intent(this, FindActivity.class);
        startActivity(intent);
        finish();

    }
}



