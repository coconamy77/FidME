package com.example.ds.fid_me;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class FindActivity extends AppCompatActivity implements View.OnClickListener {

    ListView listView;
    ArrayList<ArrayList> findFd1List;
    ArrayList<ArrayList> findFd2List;
    ArrayList<ArrayList> findFd3List;


    private String[] findFd1={"찌개","덮밥/볶음밥", "면","국/탕","간식", "해장","기타"};
    private String[][] findFd2 = {{"해산물","그 외"},{"한식","그 외"},{"따뜻한 국물","시원한 국물","국물 없음"},
            {"매콤","그 외"},{"밥 류","그 외"},{"담백","매콤"},{"소,돼지","닭","그 외"}};
    private String[][][] findFd3 = {{{"동태찌개","참치김치찌개","오징어찌개"},{"된장찌개","김치찌개","부대찌개"}},
            {{"제육덮밥","비빔밥","김치볶음밥"},{"카레","오므라이스","돈부리"}},
            {{"갈국수","라멘","짬뽕","국수"},{"냉모밀","냉면","분짜","콩국수"},{"팟타이","짜장면","잡채","파스타"}},
            {{"육개장","닭개장","매운탕"},{"순대국","갈비탕","삼계탕"}},
            {{"김밥","도시락","밥버거"},{"떡볶이","토스트","샌드위치","핫도그","샐러드"}},
            {{"순대국","콩나물국밥","국밥"},{"뼈해장국","내장탕","선지해장국","라면","짬뽕"}},
            {{"불고기","수육","족발","스테이크","삼겹살","탕수육"},{"닭볶음탕","찜닭","치킨","닭갈비"},{"초밥","햄버거","쌈밥","월남쌈"}}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find);


    }

    // 사용자가 최종적으로 선택한 버튼(음식이름)의 값을 받아와서, RecommandActivity로 넘겨준다.
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, RecommandActivity.class);
        intent.putExtra("foodName","");
        startActivity(intent);
        finish();
    }
}
