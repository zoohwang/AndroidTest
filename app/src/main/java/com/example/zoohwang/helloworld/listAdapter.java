package com.example.zoohwang.helloworld;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Administrator on 2014-09-24.
 */
public class listAdapter extends BaseAdapter {

    //행 레이아웃을 ListView 객체에 합성.
    LayoutInflater inflater;
    //목록에 출력할 데이터를 받아올 배열.
    String[] categorya;
    // 이 어댑터 파일을 호출하는 Context를 받을 임시 객체.
    Context mContext;
    //행 레이아웃 번호를 받는다.
    int mListLayout;
    //로그를 출력할땜 사용 하기 위한 문자열.
    public String TAG = "listAdapter";
    //목록에 출력할 데이터 수를 정의.
    public int listCount = 0;

    //
    //생성자
    //전달받은 Context객체와 행 레이아웃의 번호를 받아 이 클래스 전역 변수에 대입하고 전달받은 데이터
    //셋을 받아오고, Context에서 LayoutInflater서비스를 호출하고 전달받은 데이터가 있을때만 배열의
    //개수를 계산해서 listCount에 대입한다.
    public listAdapter(Context tContext, int listLayout, String[] tmap) {
        mContext = tContext;
        mListLayout = listLayout;
        categorya = tmap;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(categorya != null) {
            listCount = categorya.length;
        }
    }

    // >> 목록 수와 행 아이템을 호출
    @Override
    public int getCount() {
        return listCount;
    }

    @Override
    public Object getItem(int arg0) {
        return arg0;
    }

    public long getItemId(int position) {
        return position;
    }
    // <<목록 수와 행 아이템을 호출

    //행 레이아웃에 데이터를 매핑
    //행 레이아웃을 LayoutInflater 로 부모 객체인 ListView에 합성.
    //if문ㅇ로 이객체가 없을때를 정의 한다. 이것은 목록에 행들을 출력 할 때 모든 객체의 행이
    //생성되는 것이 아니고 화면에 보이는 행들만 생성이 되고, 스크롤을 하게 되면 이 행들의 객체를
    //다시 사용하기 때문에 조건을을 작성.
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null) {
            convertView = inflater.inflate(mListLayout, parent, false);
        }

        //현재 매핑중인 행 번호를 final로 선언하고, 클릭이벤트를 정의하면서 이 행 번로를 사용
        //이렇게 만들어진 행 객체인 convertView를 리턴해주면 ListView 객체 안에 행 객체가 매핑되어
        //화면에 출력.
        final int positionInt = position;

        ((TextView) convertView.findViewById(R.id.textView1)).setText(categorya[position]);

        convertView.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                detailInfo(positionInt);
            }
        });

        return convertView;
    }

    //상세정보 출력
    public void detailInfo(int position) {
        AlertDialog.Builder detailPop = new AlertDialog.Builder(mContext);
        detailPop.setMessage(categorya[position]).setCancelable(false)
                .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Toast.makeText(mContext, "확인을 클릭했습니다."
                                ,Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("닫기",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alert = detailPop.create();
        alert.setTitle("상세정보");
        alert.setIcon(android.R.drawable.ic_search_category_default);
        alert.show();

    }


}
